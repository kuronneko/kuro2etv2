package exc;

import java.util.Scanner;

public class Main {

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
            System.out.println("| Select [1] Offline mode                                 |");
            System.out.println("| Select [2] API Login                                    |");
            System.out.println("| Select [3] DB Connection                                |");
            System.out.println("+---------------------------------------------------------+");
            String mainSelection = mainOptionsScanner.nextLine();

        if (mainSelection.equals(String.valueOf(1))) {
            Offline offline = new Offline();
            offline.executeOfflineOptions();
        }

        if (mainSelection.equals(String.valueOf(2))) {
            Api api = new Api();
            api.executeApiOptions();
        }

        if (mainSelection.equals(String.valueOf(3))) {
            Db db = new Db();
            db.executeDbOptions();
        }

    }

}
