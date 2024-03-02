package exc;

import utils.KuroEncrypterTool;

import java.util.Scanner;

public class Offline {
    public void executeOfflineOptions() {
        Scanner offlineOptionsScanner = new Scanner(System.in);
        System.out.println("+---------------------------------------------------------+");
        System.out.println("| Select [1] to Encrypt Text                              |");
        System.out.println("| Select [2] to Decrypt Text                              |");
        System.out.println("+---------------------------------------------------------+");
        String offlineSelection = offlineOptionsScanner.nextLine();

        if (offlineSelection.equals(String.valueOf(1))) {
            Scanner textInput = new Scanner(System.in);
            System.out.println("Insert the text you want to encrypt: ");
            String encryptedText = textInput.nextLine();

            KuroEncrypterTool kuroEncrypterTool = new KuroEncrypterTool();
            System.out.format(kuroEncrypterTool.saveTextToHex(encryptedText));

            System.out.println(); // Add a space after the last print
        }

        if (offlineSelection.equals(String.valueOf(2))) {
            Scanner textInput = new Scanner(System.in);
            System.out.println("Insert the text you want to decrypt: ");
            String encryptedText = textInput.nextLine();

            KuroEncrypterTool kuroEncrypterTool = new KuroEncrypterTool();
            System.out.format(kuroEncrypterTool.loadHexToString(encryptedText.replace("b", "0")));

            System.out.println(); // Add a space after the last print
        }
    }
}

