import java.io.*; 
import java.net.*;
import java.util.*;

public class UDPServer { 
    public static void main(String[] args) throws IOException {
        DatagramSocket s = new DatagramSocket(9902);
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            byte[] buf = new byte[1024];
            DatagramPacket p = new DatagramPacket(buf, buf.length);
            s.receive(p);
            
            String msg = new String(p.getData()).trim();
            System.out.println("Client: " + msg);
            if (msg.equals("bye")) break;
            
            System.out.print("Server: ");
            msg = sc.nextLine();
            buf = msg.getBytes();
            s.send(new DatagramPacket(buf, buf.length, p.getAddress(), p.getPort()));
            if (msg.equals("bye")) break;
        }
        s.close();
    }
}