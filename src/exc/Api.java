package exc;

import dao.Filek2etDAO;
import dto.Filek2et;
import utils.ApiConnector;

import java.io.Console;
import java.io.IOException;
import java.util.Scanner;

public class Api {

    private static String authToken;

    public void executeApiOptions(){
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

        try {
            authToken = apiConnector.loginAndGetToken(email, password);
        } catch (IOException e) {
            e.printStackTrace();
        }

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

        try {
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
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.format("+-----+--------------------------------+-------------------------------------------------------------------------------------------------------------------------+----------------------------+%n");

    }
}
