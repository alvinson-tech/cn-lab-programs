import java.io.*; 
import java.net.*;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Socket socket = new Socket("localhost", 7001);
        
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        
        while (true) {
            // Send message to server
            System.out.print("Client: ");
            String message = sc.nextLine();
            out.println(message);
            
            // Exit if user types "bye"
            if (message.equals("bye")) break;
            
            // Receive message from server
            String reply = in.readLine();
            System.out.println("Server: " + reply);
            
            // Exit if server says "bye"
            if (reply.equals("bye")) break;
        }
        socket.close();
        sc.close();
    }
}