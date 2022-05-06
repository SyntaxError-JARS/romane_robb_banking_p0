package com.robb.banking.util;

import com.robb.banking.menus.RegisterMenu;
import com.robb.banking.menus.WelcomeMenu;
import com.robb.banking.services.UserServices;
import com.robb.banking.util.logging.Logger;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppState {

    private static boolean isRunning;

    private WelcomeMenu welcomeMenu;

    private RegisterMenu registerMenu;

    private final Logger logger;

    public AppState() {

        logger = Logger.getLogger(true);

        System.out.println("2. Generating instance of AppState.");
        isRunning = true;
        BufferedReader terminalReader = new BufferedReader(new InputStreamReader(System.in));
        UserServices userServices = new UserServices();

        this.welcomeMenu = new WelcomeMenu(terminalReader, userServices, logger);
        this.registerMenu = new RegisterMenu(terminalReader);
    }

    public void startup(){
        try {
            while(isRunning) {
                System.out.println("Application successfully started");
                welcomeMenu.render();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void shutdown() { isRunning = false; }

}
