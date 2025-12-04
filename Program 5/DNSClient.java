import java.net.*;
import java.util.Scanner;

public class DNSClient {
    public static void main(String[] args) throws Exception {
        // Create scanner for user input
        Scanner sc = new Scanner(System.in);
        
        // Create UDP socket
        DatagramSocket socket = new DatagramSocket();
        
        // Get hostname from user
        System.out.print("Enter hostname: ");
        String hostname = sc.nextLine();
        
        // Send hostname to server
        byte[] sendData = hostname.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(
            sendData, sendData.length,
            InetAddress.getLocalHost(), 8000
        );
        socket.send(sendPacket);
        
        // Receive IP address from server
        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        socket.receive(receivePacket);
        
        String ip = new String(receivePacket.getData()).trim();
        System.out.println("IP Address: " + ip);
        
        // Close connections
        socket.close();
        sc.close();
    }
}