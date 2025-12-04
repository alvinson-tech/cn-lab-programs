import java.io.*; 
import java.net.*;
import java.util.Scanner;

public class ChatServer {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        ServerSocket server = new ServerSocket(7001);
        Socket client = server.accept();
        
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        
        while (true) {
            // Receive message from client
            String message = in.readLine();
            System.out.println("Client: " + message);
            
            // Exit if client says "bye"
            if (message.equals("bye")) break;
            
            // Send reply to client
            System.out.print("Server: ");
            String reply = sc.nextLine();
            out.println(reply);
            
            // Exit if server says "bye"
            if (reply.equals("bye")) break;
        }
        
        client.close();
        server.close();
        sc.close();
    }
}