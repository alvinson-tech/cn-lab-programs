import java.net.*;

public class DNSServer {
    public static void main(String[] args) throws Exception {
        // DNS lookup table
        String[] hosts = {"yahoo.com", "gmail.com", "cricinfo.com", "facebook.com"};
        String[] ips = {"68.180.206.184", "209.85.148.19", "80.168.92.140", "69.63.189.16"};
        
        // Create UDP socket on port 8000
        DatagramSocket socket = new DatagramSocket(8000);
        
        // Keep server running to handle multiple requests
        while (true) {
            // Receive hostname from client
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            
            String hostname = new String(receivePacket.getData()).trim();
            
            // Search for hostname in the table
            String result = "Host Not Found";
            for (int i = 0; i < hosts.length; i++) {
                if (hostname.equals(hosts[i])) {
                    result = ips[i];  // Found matching IP
                    break;
                }
            }
            
            // Send IP address back to client
            byte[] sendData = result.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(
                sendData, sendData.length,
                receivePacket.getAddress(), receivePacket.getPort()
            );
            socket.send(sendPacket);
        }
    }
}