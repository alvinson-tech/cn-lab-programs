//4b

import java.io.BufferedReader;
import java.net.ServerSocket;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Socket socket = new Socket("localhost", 9500);
        
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        while(true){
            System.out.println("Client: ");
            String message = sc.nextLine();
            out.println(message);

            if(message.equals("bye")) break;

            String reply = in.readLine();
            System.out.println("Server: " + reply);

            if(reply.equals("bye")) break;
        }

        socket.close();
        sc.close();
    }
}

import java.io.BufferedReader;
import java.util.Scanner;

public class ChatServer {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        ServerSocket server = new ServerSocket(9500);
        Socket client = server.accept();
        
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);

        while(true){
            String message = in.readLine();
            System.out.println("Client: " + message);
            if(message.equals("bye")) break;

            System.out.println("Server: ");
            String reply = sc.nextLine();
            out.println(reply);
            if(message.equals("bye")) break;
        }
        client.close();
        server.close();
    }
}