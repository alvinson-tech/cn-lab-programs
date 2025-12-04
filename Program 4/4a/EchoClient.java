import java.io.*;
import java.net.*;
import java.util.Scanner;

public class EchoClient {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Socket socket = new Socket("localhost", 9500);
        
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        
        while (true) {
            // Get message from user
            System.out.print("Client: ");
            String message = sc.nextLine();
            
            // Send message to server
            out.println(message);
            
            // Exit if user types "bye"
            if (message.equals("bye")) break;
            
            // Receive echo from server
            String echo = in.readLine();
            System.out.println("Server: " + echo);
        }
        socket.close();
        sc.close();
    }
}