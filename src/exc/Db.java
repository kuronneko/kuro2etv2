package exc;

import dao.db.Filek2etDAO;
import dto.Filek2et;
import java.util.Scanner;
import java.sql.Timestamp;
import utils.DbConnector;
import utils.KuroEncrypterTool;

public class Db extends DbConnector {
    public void executeDbOptions(){

        KuroEncrypterTool KuroEncrypterTool = new KuroEncrypterTool();

        try {
                String leftAlignFormat = "| %-3s | %-30s | %-128s | %-10s |%n";
                Scanner sc = new Scanner(System.in);

                Filek2etDAO Filek2etDAO = new Filek2etDAO();

                System.out.println("+---------------------------------------------------------+");
                System.out.println("| Select 1 to show full list                              |");
                System.out.println("| Select 2 to add new entry                               |");
                System.out.println("| Select 3 to edit entry                                  |");
                System.out.println("| Select 4 to delete entry                                |");
                System.out.println("+---------------------------------------------------------+");
                String selection = sc.nextLine();
                if (selection.equals(String.valueOf(1))) {
                    System.out.format("+-----+--------------------------------+----------------------------------------------------------------------------------------------------------------------------------+---------------------+%n");
                    System.out.format("| ID  | Name                           | Text                                                                                                                             | Last_Updated        |%n");
                    System.out.format("+-----+--------------------------------+----------------------------------------------------------------------------------------------------------------------------------+---------------------+%n");
                    for (Filek2et Filek2et : Filek2etDAO.getAll()) {
                        System.out.format(leftAlignFormat, String.valueOf(Filek2et.getId()), Filek2et.getName(), Filek2et.getText(), Filek2et.getUpdated_at());
                    }
                    System.out.format("+-----+--------------------------------+----------------------------------------------------------------------------------------------------------------------------------+---------------------+%n");

                } else if (selection.equals(String.valueOf(2))) {
                    Scanner textInput = new Scanner(System.in);
                    System.out.println("Insert text you want to encrypt: ");
                    String normalText = textInput.nextLine();
                    System.out.println("Insert filename: ");
                    String filename = textInput.nextLine();
                    Filek2etDAO.add(new Filek2et(filename, normalText, String.valueOf(new Timestamp(System.currentTimeMillis())), String.valueOf(new Timestamp(System.currentTimeMillis()))));
                    System.out.format("+-----+--------------------------------+----------------------------------------------------------------------------------------------------------------------------------+---------------------+%n");
                    System.out.format("| ID  | Name                           | Text                                                                                                                             | Last_Updated        |%n");
                    System.out.format("+-----+--------------------------------+----------------------------------------------------------------------------------------------------------------------------------+---------------------+%n");
                    for (Filek2et Filek2et : Filek2etDAO.getAll()) {
                        System.out.format(leftAlignFormat, String.valueOf(Filek2et.getId()), Filek2et.getName(), Filek2et.getText(), Filek2et.getUpdated_at());
                    }
                    System.out.format("+-----+--------------------------------+----------------------------------------------------------------------------------------------------------------------------------+---------------------+%n");
                    System.out.println("Entry added successfully !");

                } else if (selection.equals(String.valueOf(3))) {
                    System.out.format("+-----+--------------------------------+----------------------------------------------------------------------------------------------------------------------------------+---------------------+%n");
                    System.out.format("| ID  | Name                           | Text                                                                                                                             | Last_Updated        |%n");
                    System.out.format("+-----+--------------------------------+----------------------------------------------------------------------------------------------------------------------------------+---------------------+%n");
                    for (Filek2et Filek2et : Filek2etDAO.getAll()) {
                        System.out.format(leftAlignFormat, String.valueOf(Filek2et.getId()), Filek2et.getName(), Filek2et.getText(), Filek2et.getUpdated_at());
                    }
                    System.out.format("+-----+--------------------------------+----------------------------------------------------------------------------------------------------------------------------------+---------------------+%n");
                    Scanner sc2 = new Scanner(System.in);
                    System.out.println("Edit entry by ID: ");
                    int editId = sc2.nextInt();
                    Filek2et Filek2etEdited = Filek2etDAO.get(editId);
                    if (Filek2etEdited.getName() != null) {
                        Scanner textInputEdit = new Scanner(System.in);
                        System.out.println("Inset new name: ");
                        String name = textInputEdit.nextLine();
                        System.out.println("Insert new text: ");
                        String text = textInputEdit.nextLine();

                        Filek2etEdited.setName(name);
                        Filek2etEdited.setText(text);
                        Filek2etEdited.setUpdated_at(String.valueOf(new Timestamp(System.currentTimeMillis())));
                        Filek2etDAO.update(Filek2etEdited);

                        System.out.format("+-----+--------------------------------+----------------------------------------------------------------------------------------------------------------------------------+---------------------+%n");
                        System.out.format("| ID  | Name                           | Text                                                                                                                             | Last_Updated        |%n");
                        System.out.format("+-----+--------------------------------+----------------------------------------------------------------------------------------------------------------------------------+---------------------+%n");
                        for (Filek2et Filek2et : Filek2etDAO.getAll()) {
                            System.out.format(leftAlignFormat, String.valueOf(Filek2et.getId()), Filek2et.getName(), Filek2et.getText(), Filek2et.getUpdated_at());
                        }
                        System.out.format("+-----+--------------------------------+----------------------------------------------------------------------------------------------------------------------------------+---------------------+%n");
                        System.out.println("Entry updated successfully !");
                    } else {
                        System.out.println("Entry not found");
                    }

                } else if (selection.equals(String.valueOf(4))) {
                    System.out.format("+-----+--------------------------------+----------------------------------------------------------------------------------------------------------------------------------+---------------------+%n");
                    System.out.format("| ID  | Name                           | Text                                                                                                                             | Last_Updated        |%n");
                    System.out.format("+-----+--------------------------------+----------------------------------------------------------------------------------------------------------------------------------+---------------------+%n");
                    for (Filek2et Filek2et : Filek2etDAO.getAll()) {
                        System.out.format(leftAlignFormat, String.valueOf(Filek2et.getId()), Filek2et.getName(), Filek2et.getText(), Filek2et.getUpdated_at());
                    }
                    System.out.format("+-----+--------------------------------+----------------------------------------------------------------------------------------------------------------------------------+---------------------+%n");
                    Scanner sc3 = new Scanner(System.in);
                    System.out.println("Delete entry by ID: ");
                    int deleteId = sc3.nextInt();
                    if (Filek2etDAO.get(deleteId).getName() != null) {
                        Filek2etDAO.delete(Filek2etDAO.get(deleteId));
                        System.out.format("+-----+--------------------------------+----------------------------------------------------------------------------------------------------------------------------------+---------------------+%n");
                        System.out.format("| ID  | Name                           | Text                                                                                                                             | Last_Updated        |%n");
                        System.out.format("+-----+--------------------------------+----------------------------------------------------------------------------------------------------------------------------------+---------------------+%n");
                        for (Filek2et Filek2et : Filek2etDAO.getAll()) {
                            System.out.format(leftAlignFormat, String.valueOf(Filek2et.getId()), Filek2et.getName(), Filek2et.getText(), Filek2et.getUpdated_at());
                        }
                        System.out.format("+-----+--------------------------------+----------------------------------------------------------------------------------------------------------------------------------+---------------------+%n");
                        System.out.println("Entry delete successfully !");
                    } else {
                        System.out.println("Entry not found");
                    }

                } else if (selection.equals("exit")) {
                    System.out.println("+--------- Sayonara ! ------------------------------------+");
                } else {
                    System.out.println("+--------- You need to select a valid option -------------+");
                    System.out.println("+--------- Try again ! -----------------------------------+");
                }
        } catch (Exception e) {
            System.out.println("Wrong password or invalid format => " + e.getMessage());
        }
    }
}