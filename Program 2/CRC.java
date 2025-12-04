// WRITE A PROGRAM FOR ERROR DETECTING CODE USING CRC-CCITT (16- BITS)
import java.util.Scanner;

public class CRC {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Sender's Side:");
        System.out.print("Enter message bits: ");
        String message = sc.nextLine();
        System.out.print("Enter generator: ");
        String generator = sc.nextLine();

        String paddedMessage = message + "0".repeat(generator.length() - 1);
        String checksum = calculateCRC(paddedMessage, generator);
        String codeword = message + checksum;

        System.out.println("The checksum code is: " + codeword);

        System.out.println("\n\nReceiver's Side:");
        System.out.print("Enter checksum code: ");
        String receivedCode = sc.nextLine();
        System.out.print("Enter generator: ");
        generator = sc.nextLine();

        String remainder = calculateCRC(receivedCode, generator);
        String expectedRemainder = "0".repeat(generator.length() - 1);

        if (remainder.equals(expectedRemainder)) {
            System.out.println("Data stream is valid");
        } else {
            System.out.println("Data stream is invalid. CRC error occurred.");
        }
        sc.close();
    }

    static String calculateCRC(String data, String generator) {
        char[] bits = data.toCharArray();
        
        for (int i = 0; i <= bits.length - generator.length(); i++) {
            if (bits[i] == '1') {
                for (int j = 0; j < generator.length(); j++) {
                    if (bits[i + j] == generator.charAt(j)) {
                        bits[i + j] = '0';
                    } else {
                        bits[i + j] = '1';
                    }
                }
            }
        }
        
        return new String(bits).substring(bits.length - generator.length() + 1);
    }
}