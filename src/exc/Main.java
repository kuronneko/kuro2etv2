package exc;

import dao.Filek2etDAO;
import dto.Filek2et;
import utils.ApiConnector;
import utils.KuroEncrypterTool;

import java.io.Console;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static String authToken;

    public static void main(String[] args) throws Exception {
        System.out.println("             __ __                       __                \n"
                + "            / //_/_ _________  ___  ___ / /_____           \n"
                + "           / ,< / // / __/ _ \\/ _ \\/ -_)  '_/ _ \\          \n"
                + "          /_/|_|\\_,_/_/  \\___/_//_/\\__/_/\\_\\\\___/          \n"
                + "                                                           \n"
                + "   ____                       __         ______          __\n"
                + "  / __/__  __________ _____  / /____ ___/_  __/__  ___  / /\n"
                + " / _// _ \\/ __/ __/ // / _ \\/ __/ -_) __// / / _ \\/ _ \\/ / \n"
                + "/___/_//_/\\__/_/  \\_, / .__/\\__/\\__/_/  /_/  \\___/\\___/_/  \n"
                + "                 /___/_/                                   \n"
                + "");


            Scanner mainOptionsScanner = new Scanner(System.in);
            System.out.println("+---------------------------------------------------------+");
            System.out.println("| Select 1 Offline mode                                   |");
            System.out.println("| Select 2 Login                                          |");
            System.out.println("+---------------------------------------------------------+");
            String mainSelection = mainOptionsScanner.nextLine();

        if (mainSelection.equals(String.valueOf(1))) {

            Scanner offlineOptionsScanner = new Scanner(System.in);
            System.out.println("+---------------------------------------------------------+");
            System.out.println("| Select 1 to Encrypt Text                                 |");
            System.out.println("| Select 2 to Decrypt Text                                 |");
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

        if (mainSelection.equals(String.valueOf(2))) {

            Console console = System.console();
            String password = "";
            String email = "";

            if (console == null) {
                // Console is not available, use Scanner
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter Email: ");
                email = scanner.nextLine();
                System.out.println("Enter Password: ");
                password = scanner.nextLine();
            } else {
                // Console is available, use readLine and readPassword
                email = console.readLine("Enter Email: ");
                char[] passwordChars = console.readPassword("Enter Password: ");
                password = new String(passwordChars);
            }

            ApiConnector apiConnector = new ApiConnector();
            authToken = apiConnector.loginAndGetToken(email, password);

            Filek2etDAO filek2etDAO = new Filek2etDAO(authToken);

            try {
                new ProcessBuilder("cmd", "/c", "mode con: cols=200 lines=30").inheritIO().start().waitFor();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("             __ __                       __                \n"
                    + "            / //_/_ _________  ___  ___ / /_____           \n"
                    + "           / ,< / // / __/ _ \\/ _ \\/ -_)  '_/ _ \\          \n"
                    + "          /_/|_|\\_,_/_/  \\___/_//_/\\__/_/\\_\\\\___/          \n"
                    + "                                                           \n"
                    + "   ____                       __         ______          __\n"
                    + "  / __/__  __________ _____  / /____ ___/_  __/__  ___  / /\n"
                    + " / _// _ \\/ __/ __/ // / _ \\/ __/ -_) __// / / _ \\/ _ \\/ / \n"
                    + "/___/_//_/\\__/_/  \\_, / .__/\\__/\\__/_/  /_/  \\___/\\___/_/  \n"
                    + "                 /___/_/                                   \n"
                    + "");

            System.out.format("+-----+--------------------------------+-------------------------------------------------------------------------------------------------------------------------+----------------------------+%n");
            System.out.format("| ID  | Name                           | Text                                                                                                                    | Last_Updated               |%n");
            System.out.format("+-----+--------------------------------+-------------------------------------------------------------------------------------------------------------------------+----------------------------+%n");

            for (Filek2et filek2et : filek2etDAO.getAll()) {
                String formattedText = filek2et.getText() != null ?
                        filek2et.getText().replaceAll("\n", " ") :
                        "";

                System.out.format("| %-5d| %-30s| %-120s| %-10s|%n",
                        filek2et.getId(),
                        filek2et.getName(),
                        formattedText,
                        filek2et.getUpdated_at());
            }

            System.out.format("+-----+--------------------------------+-------------------------------------------------------------------------------------------------------------------------+----------------------------+%n");

        }

    }

}
