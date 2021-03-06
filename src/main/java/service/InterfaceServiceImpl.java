package service;

import java.util.Scanner;
import config.PostgresConnection;
import model.Customer;

public class InterfaceServiceImpl implements InterfaceService {

    private final InsertService insertService;
    private int userOption;

    public InterfaceServiceImpl() {
        PostgresConnection postgresConnection = new PostgresConnection();
        this.insertService = new InsertServiceImpl(postgresConnection);
    }

    @Override
    public void menu() {
        System.out.println("Welcome to our database");
        System.out.println("Please choose an option from below: ");
        System.out.println("1. Insert data into table");
        System.out.println("2. Run query");
        System.out.println("3. Quit");

        while (checkValidNumber() != 3) {
            if (userOption == 1) {
                System.out.println("executeUpdate");
                Customer customer = insertService.createCustomer();
                insertService.insertCustomer(customer);
                backToMenu();
            } else if (userOption == 2) {
                System.out.println("executeQuery");
                //executeQuery();
                backToMenu();
            }
        }

        System.out.println("Goodbye");
        System.exit(0);
    }

    @Override
    public void backToMenu() {
        System.out.println("Press any key to return to menu");
        Scanner userInput = new Scanner(System.in);
        String input = userInput.next();
        if (input != null) {
            menu();
        }
    }

    @Override
    public int checkValidNumber() {
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("Please enter a number between 1 and 3: ");
            while (!input.hasNextInt()) {
                System.out.println("Please enter a valid number: ");
                input.next();
            }
            userOption = input.nextInt();
        } while (userOption <= 0 || userOption > 3);
        return userOption;
    }
}
