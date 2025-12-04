import java.io.*;
import java.net.*;

public class RARPServer {
    public static void main(String[] args) throws Exception {
        String[] macs = {"6A:08:AA:C2", "8A:BC:E3:FA", "AA:BB:CC:DD:EE:FF"};
        String[] ips = {"165.165.80.80", "165.165.79.1", "192.168.1.1"};
        
        ServerSocket server = new ServerSocket(5001);
        System.out.println("RARP Server started on port 5001...\n");
        
        while (true) {
            Socket client = server.accept();
            
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            
            String mac = in.readLine();
            System.out.println("Request for MAC: " + mac);
            
            String result = "Not Found";
            for (int i = 0; i < macs.length; i++) {
                if (mac.equals(macs[i])) {
                    result = ips[i];
                    break;
                }
            }
            
            out.println(result);
            System.out.println("Response: " + result + "\n");
            
            client.close();
        }
    }
}