import java.io.*;
import java.net.*;
import java.util.Scanner;

public class DNSClient {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        
        // Connect to server
        Socket socket = new Socket("localhost", 5000);
        
        // Setup input/output streams
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        
        // Send hostname
        System.out.print("Enter hostname: ");
        String hostname = sc.nextLine();
        out.println(hostname);
        
        // Receive IP address
        String ip = in.readLine();
        System.out.println("IP Address: " + ip);
        
        socket.close();
        sc.close();
    }
}