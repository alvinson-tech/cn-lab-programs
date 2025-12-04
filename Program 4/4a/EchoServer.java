import java.io.*;
import java.net.*;

public class EchoServer {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(9500);
        Socket client = server.accept();
        
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        
        String message;
        while ((message = in.readLine()) != null) {
            // Exit if client sends "bye"
            if (message.equals("bye")) break;
            // Echo the message back to client
            out.println(message);
        }
        client.close();
        server.close();
    }
}