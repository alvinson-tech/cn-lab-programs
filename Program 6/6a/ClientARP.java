//ClientARP.java

import java.io.*;
import java.net.*;

class ClientARP {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Socket s = new Socket("127.0.0.1", 5604);
        
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        
        System.out.print("Enter the IP address: ");
        String ip = br.readLine();
        dout.writeBytes(ip + '\n');
        
        String mac = din.readLine();
        System.out.println("MAC Address: " + mac);
        
        s.close();
    }
}