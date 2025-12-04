import java.io.*;
import java.net.*;
import java.util.HashMap;

public class DNSServer {
    public static void main(String[] args) throws Exception {
        // DNS lookup table
        HashMap<String, String> dns = new HashMap<>();
        dns.put("yahoo.com", "68.180.206.184");
        dns.put("gmail.com", "209.85.148.19");
        dns.put("cricinfo.com", "80.168.92.140");
        dns.put("facebook.com", "69.63.189.16");
        
        // Start server
        ServerSocket server = new ServerSocket(5000);
        System.out.println("DNS Server running on port 5000...\n");
        
        while (true) {
            // Wait for client connection
            Socket client = server.accept();
            System.out.println("Client connected!");
            
            // Setup input/output streams
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            
            // Read hostname from client
            String hostname = in.readLine();
            System.out.println("Request for: " + hostname);
            
            // Lookup IP and send back
            String ip = dns.getOrDefault(hostname, "Host Not Found");
            out.println(ip);
            System.out.println("Sent: " + ip + "\n");
            
            client.close();
        }
    }
}