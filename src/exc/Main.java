package exc;

import dao.Filek2etDAO;
import dto.Filek2et;
import utils.ApiConnector;

import java.io.Console;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static String authToken;

    public static void main(String[] args) throws IOException {
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
