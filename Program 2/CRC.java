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
    int gLen = generator.length();
    
        for (int i = 0; i <= bits.length - gLen; i++) {
            if (bits[i] == '1') {
                for (int j = 0; j < gLen; j++) {
                    bits[i + j] = (bits[i + j] == generator.charAt(j)) ? '0' : '1';
                }
            }
        }
        return new String(bits, bits.length - gLen + 1, gLen - 1);
    }
}