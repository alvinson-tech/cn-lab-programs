import java.io.*;
import java.net.*; 
import java.util.Scanner;

public class ARPClient {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        Socket socket = new Socket("localhost", 6000);
        
        // Setup input and output streams
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        
        // Get IP address from user
        System.out.print("Enter IP: ");
        String ip = sc.nextLine();
        
        // Send IP to server
        out.println(ip);
        
        // Receive MAC address from server
        String mac = in.readLine();
        System.out.println("MAC: " + mac);
        
        // Close connections
        socket.close();
        sc.close();
    }
}