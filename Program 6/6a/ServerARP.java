//ServerARP.java

import java.io.*;
import java.net.*;

class ServerARP {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(5604);
        System.out.println("ARP Server started on port 5604...");
        
        Socket s = ss.accept();
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        
        String[] ips = {"165.165.80.80", "165.165.79.1"};
        String[] macs = {"6A:08:AA:C2", "8A:BC:E3:FA"};
        
        while (true) {
            String ip = din.readLine();
            if (ip == null) break;
            
            String result = "Not Found";
            for (int i = 0; i < ips.length; i++) {
                if (ip.equals(ips[i])) {
                    result = macs[i];
                    break;
                }
            }
            
            dout.writeBytes(result + '\n');
        }
        
        s.close();
        ss.close();
    }
}