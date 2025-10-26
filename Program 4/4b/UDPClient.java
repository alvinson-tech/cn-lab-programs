import java.io.*; 
import java.net.*;
import java.util.*;

public class UDPClient { 
    public static void main(String[] args) throws IOException {
        DatagramSocket s = new DatagramSocket();
        Scanner sc = new Scanner(System.in);
        InetAddress ip = InetAddress.getByName("localhost");
        
        while (true) {
            System.out.print("Client: ");
            String msg = sc.nextLine();
            byte[] buf = msg.getBytes();
            s.send(new DatagramPacket(buf, buf.length, ip, 9902));
            if (msg.equals("bye")) break;
            
            buf = new byte[1024];
            DatagramPacket p = new DatagramPacket(buf, buf.length);
            s.receive(p);
            System.out.println("Server: " + new String(p.getData()).trim());
        }
        s.close();
    }
}