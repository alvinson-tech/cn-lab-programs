import java.io.*;
import java.net.*;
import java.util.Scanner;

public class RARPClient {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Socket socket = new Socket("localhost", 5001);
        
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        
        System.out.print("Enter MAC address: ");
        String mac = sc.nextLine();
        out.println(mac);
        
        String ip = in.readLine();
        System.out.println("IP Address: " + ip);
        
        socket.close();
        sc.close();
    }
}