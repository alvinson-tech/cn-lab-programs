//FileServer.java

import java.io.*; 
import java.net.*; 

public class FileServer {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(8080);
        System.out.println("Server started on port 8080...");
        Socket s = ss.accept();
        
        File f = new File("test.txt");
        FileInputStream fis = new FileInputStream(f);
        OutputStream os = s.getOutputStream();
        
        byte[] buf = new byte[1024];
        int n;
        while ((n = fis.read(buf)) != -1) { 
            os.write(buf, 0, n);
        }
        
        fis.close();
        s.close();
        ss.close();
        System.out.println("File sent!");
    }
}