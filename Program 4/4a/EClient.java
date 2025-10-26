//EClient.java
import java.io.*; 
import java.net.*;

public class EClient {
    public static void main(String args[]) {
        try {
            Socket c = new Socket("localhost", 9000);
            
            PrintStream os = new PrintStream(c.getOutputStream());
            DataInputStream is = new DataInputStream(System.in);
            DataInputStream is1 = new DataInputStream(c.getInputStream());
            
            String line;
            while (true) {
                System.out.print("Client: ");
                line = is.readLine();
                os.println(line);
                System.out.println("Server: " + is1.readLine()); 
            }
        } catch (IOException e) {
            System.out.println("Connection error: " + e);
        }
    }
}