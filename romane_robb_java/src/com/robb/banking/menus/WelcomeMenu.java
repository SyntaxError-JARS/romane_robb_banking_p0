package com.robb.banking.menus;

import com.robb.banking.services.UserServices;
import java.io.BufferedReader;
import static com.robb.banking.util.AppState.shutdown;
import com.robb.banking.util.logging.Logger;
import java.io.IOException;

public class WelcomeMenu extends Menu {

    private UserServices userServices;

    private final Logger logger;

    public WelcomeMenu(BufferedReader terminalReader, UserServices userServices, Logger logger) {
        super("Welcome", "/welcome", terminalReader);
        this.userServices = userServices;
        this.logger = logger;
    }

    @Override
    public void render() throws Exception {
        String welcome = "Welcome to Robb Banking!";
        String option1 = "1). Login";
        String option2 = "2). Register";
        String option3 = "3). View/Create transactions";
        String option4 = "4). View all transactions";
        String option5 = new String("5). Exit the app");

        System.out.printf("%s \n %s \n %s \n %s \n %s \n %s", welcome, option1, option2, option3, option4, option5).println();

        System.out.print("\n Select number from above\n >");
        String userSelection = terminalReader.readLine();

        switch (userSelection) {
            case "1":
                System.out.println("User has selected login...");
                break;
            case "2":
                System.out.println("User has selected register...");
                RegisterMenu registerMenu = new RegisterMenu(terminalReader);
                registerMenu.render();
                break;
            case "3":
                System.out.println("User has selected view/create transactions...");
                break;
            case "4":
                System.out.println("User has selected to view all transactions...");
                userServices.readCustomer_info();
                break;
            case "5":
                System.out.println("User has selected exit...");
                shutdown();
                logger.info("Application shutting down");
                break;
            default:
                System.out.println("Sorry! No valid user input provided.");
                break;
        }
    }
}
