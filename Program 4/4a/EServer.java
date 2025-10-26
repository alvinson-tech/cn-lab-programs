//EServer.java
import java.io.*;
import java.net.*;

public class EServer{
    public static void main(String[] args) {
        try {
            ServerSocket s = new ServerSocket(9000);
            System.out.println("Server started at port 9000");
            Socket c = s.accept();
            System.out.println("Client is connected!");

            DataInputStream is = new DataInputStream(c.getInputStream()); 
            PrintStream ps = new PrintStream(c.getOutputStream());

            String line;
            while((line = is.readLine()) != null){
                ps.println(line);
            }
        } catch (IOException e) {
            System.out.println(e);
        }   
    }
}