// ServerRARP.java

import java.net.*;

class ServerRARP {
    public static void main(String[] args) throws Exception {
        DatagramSocket s = new DatagramSocket(1309);
        System.out.println("RARP Server started on port 1309...");
        
        String[] ips = {"165.165.80.80", "165.165.79.1"};
        String[] macs = {"6A:08:AA:C2", "8A:BC:E3:FA"};
        
        while (true) {
            byte[] buf = new byte[1024];
            DatagramPacket p = new DatagramPacket(buf, buf.length);
            s.receive(p);
            
            String mac = new String(p.getData()).trim();
            System.out.println("Request for MAC: " + mac);
            
            String result = "Not Found";
            for (int i = 0; i < macs.length; i++) {
                if (mac.equals(macs[i])) {
                    result = ips[i];
                    break;
                }
            }
            
            buf = result.getBytes();
            DatagramPacket reply = new DatagramPacket(buf, buf.length, p.getAddress(), p.getPort());
            s.send(reply);
        }
    }
}