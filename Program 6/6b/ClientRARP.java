// ClientRARP.java

import java.io.*;
import java.net.*;

class ClientRARP {
    public static void main(String[] args) throws Exception {
        DatagramSocket s = new DatagramSocket();
        InetAddress ip = InetAddress.getByName("127.0.0.1");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.print("Enter the MAC address: ");
        String mac = br.readLine();
        
        byte[] buf = mac.getBytes();
        DatagramPacket p = new DatagramPacket(buf, buf.length, ip, 1309);
        s.send(p);
        
        buf = new byte[1024];
        p = new DatagramPacket(buf, buf.length);
        s.receive(p);
        
        String result = new String(p.getData()).trim();
        System.out.println("IP Address: " + result);
        
        s.close();
    }
}