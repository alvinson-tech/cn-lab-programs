//FileClient.java

import java.io.*;
import java.net.*;

public class FileClient {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 8080);
        
        FileOutputStream fos = new FileOutputStream("received.txt");
        InputStream is = s.getInputStream();
        
        byte[] buf = new byte[1024];
        int n;
        while ((n = is.read(buf)) != -1) {
            fos.write(buf, 0, n);
        }
        
        fos.close();
        s.close();
        System.out.println("File received!");
    }
}