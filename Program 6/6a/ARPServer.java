import java.io.*;
import java.net.*;

public class ARPServer {
    public static void main(String[] args) throws Exception {
        String[] ips = {"165.165.80.80", "165.165.79.1", "192.168.1.1"};
        String[] macs = {"6A:08:AA:C2", "8A:BC:E3:FA", "AA:BB:CC:DD:EE:FF"};
        
        ServerSocket server = new ServerSocket(5000);
        System.out.println("ARP Server started on port 5000...\n");
        
        while (true) {
            Socket client = server.accept();
            
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            
            String ip = in.readLine();
            System.out.println("Request for IP: " + ip);
            
            String result = "Not Found";
            for (int i = 0; i < ips.length; i++) {
                if (ip.equals(ips[i])) {
                    result = macs[i];
                    break;
                }
            }
            
            out.println(result);
            System.out.println("Response: " + result + "\n");
            
            client.close();
        }
    }
}