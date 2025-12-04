import java.io.*;
import java.net.*;

public class RARPServer {
    public static void main(String[] args) throws Exception {
        // RARP table: MAC addresses and their corresponding IP addresses
        String[] macs = {"6A:08:AA:C2", "8A:BC:E3:FA", "AA:BB:CC:DD:EE:FF"};
        String[] ips = {"165.165.80.80", "165.165.79.1", "192.168.1.1"};
        
        // Start server on port 5001
        ServerSocket server = new ServerSocket(6001);
        
        // Keep server running to handle multiple clients
        while (true) {
            // Wait for client connection
            Socket client = server.accept();
            
            // Setup input and output streams
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            
            // Read MAC address from client
            String mac = in.readLine();
            
            // Search for MAC in the table
            String result = "Not Found";
            for (int i = 0; i < macs.length; i++) {
                if (mac.equals(macs[i])) {
                    result = ips[i];  // Found matching IP
                    break;
                }
            }
            
            // Send result back to client
            out.println(result);
            
            // Close client connection
            client.close();
        }
    }
}