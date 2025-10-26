import java.util.Scanner;

public class LeakyBucket {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int bucketCap = 4, rate = 3, bucketRem = 0;
        
        System.out.print("Enter the number of packets: ");
        int n = sc.nextInt();
        
        int[] packets = new int[n + 1];
        System.out.println("Enter the packets:");
        for (int i = 1; i <= n; i++) {
            packets[i] = sc.nextInt();
        }
        
        System.out.println("\nClock\tPacketSize\tAccept\t\tSent\tRemaining");
        
        for (int i = 1; i <= n; i++) {
            int recv = packets[i];
            String status = String.valueOf(recv);

            if (recv > 0 && bucketRem + recv > bucketCap) {
                status = "dropped";
            } else {
                bucketRem += recv;
            }
            
            int sent = Math.min(bucketRem, rate);
            bucketRem -= sent;
            
            System.out.println(i + "\t" + packets[i] + "\t\t" + status + "\t\t" + sent + "\t" + bucketRem);
        }
        
        sc.close();
    }
}