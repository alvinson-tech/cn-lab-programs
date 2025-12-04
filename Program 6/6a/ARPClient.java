import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ARPClient {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Socket socket = new Socket("localhost", 5000);
        
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        
        System.out.print("Enter IP address: ");
        String ip = sc.nextLine();
        out.println(ip);
        
        String mac = in.readLine();
        System.out.println("MAC Address: " + mac);
        
        socket.close();
        sc.close();
    }
}