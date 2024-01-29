package exc;

import dao.Filek2etDAO;
import utils.ApiConnector;

import java.io.Console;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static String authToken;

    public static void main(String[] args) {
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

        try {
            ApiConnector apiConnector = new ApiConnector();
            authToken = apiConnector.loginAndGetToken(email, password);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Filek2etDAO filek2etDAO = new Filek2etDAO(authToken);
    }
}
