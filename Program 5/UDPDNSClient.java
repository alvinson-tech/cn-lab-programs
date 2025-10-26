//UDPDNSClient.java

import java.io.*; 
import java.net.*;

public class UDPDNSClient {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket s = new DatagramSocket();
        InetAddress ip = InetAddress.getLocalHost();
        
        System.out.print("Enter the hostname: ");
        String host = br.readLine();
        
        byte[] buf = host.getBytes();
        DatagramPacket p = new DatagramPacket(buf, buf.length, ip, 1362);
        s.send(p);
        
        buf = new byte[1024];
        p = new DatagramPacket(buf, buf.length);
        s.receive(p);
        
        String result = new String(p.getData()).trim();
        System.out.println("IP Address: " + result);
        
        s.close();
    }
}