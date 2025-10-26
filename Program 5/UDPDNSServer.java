//UDPDNSServer.java

import java.io.*; 
import java.net.*;

public class UDPDNSServer {
    public static void main(String[] args) throws IOException {
        String[] hosts = {"yahoo.com", "gmail.com", "cricinfo.com", "facebook.com"};
        String[] ips = {"68.180.206.184", "209.85.148.19", "80.168.92.140", "69.63.189.16"};
        
        System.out.println("DNS Server started on port 1362...");
        
        while (true) {
            DatagramSocket s = new DatagramSocket(1362);
            byte[] buf = new byte[1024];
            
            DatagramPacket p = new DatagramPacket(buf, buf.length);
            s.receive(p);
            
            String host = new String(p.getData()).trim();
            System.out.println("Request for host: " + host);
            
            String response = "Host Not Found";
            for (int i = 0; i < hosts.length; i++) { 
                if (hosts[i].equals(host)) {
                    response = ips[i];
                    break;
                }
            }
            
            buf = response.getBytes();
            DatagramPacket reply = new DatagramPacket(buf, buf.length, p.getAddress(), p.getPort());
            s.send(reply);
            s.close();
        }
    }
}