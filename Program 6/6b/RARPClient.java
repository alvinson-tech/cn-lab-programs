import java.io.*;
import java.net.*;
import java.util.Scanner;

public class RARPClient {
    public static void main(String[] args) throws Exception {
        // Create scanner for user input
        Scanner sc = new Scanner(System.in);
        
        // Connect to RARP server on localhost at port 5001
        Socket socket = new Socket("localhost", 6001);
        
        // Setup input and output streams
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        
        // Get MAC address from user
        System.out.print("Enter MAC: ");
        String mac = sc.nextLine();
        
        // Send MAC to server
        out.println(mac);
        
        // Receive IP address from server
        String ip = in.readLine();
        System.out.println("IP: " + ip);
        
        // Close connections
        socket.close();
        sc.close();
    }
}