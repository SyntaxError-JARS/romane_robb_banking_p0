package com.robb.banking.util.logging;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;

public class Logger {

    private static Logger logger;
    private final boolean printToConsole;

    private Logger(boolean printToConsole) {
        this.printToConsole = printToConsole;
    }

    public static Logger getLogger(boolean printToConsole){
        if(logger == null){
            logger = new Logger(printToConsole);
        }

        return logger;
    }

    public static Logger getLogger(){
        if(logger == null){
            logger = new Logger(true);
        }

        return logger;
    }

    public void log(String message) {

        try (Writer logWriter = new FileWriter("src/main.resources/banking.log", true);) {
            logWriter.write(LocalDateTime.now().toString() + " LOG: " + message + "\n");

            if (printToConsole) {
                System.out.println(LocalDateTime.now().toString() + " LOG: " + message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void info(String message){
       try (Writer logWriter = new FileWriter("src/main.resources/banking.log", true);){
             logWriter.write(LocalDateTime.now().toString() + " INFO: " + message + "\n");

              if(printToConsole){
                  System.out.println(LocalDateTime.now().toString() + " INFO: " + message);
                }

            } catch (IOException e){
                e.printStackTrace();
            }

        }


    public void debug(String message){

        try (Writer logWriter = new FileWriter("src/main.resources/banking.log", true);){
            logWriter.write(LocalDateTime.now().toString() + " DEBUG: " + message + "\n");

            if(printToConsole){
                System.out.println(LocalDateTime.now().toString() + " DEBUG: " + message);
            }

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void warn(String message){
        try (Writer logWriter = new FileWriter("src/main.resources/banking.log", true);){
            logWriter.write(LocalDateTime.now().toString() + " WARN: " + message + "\n");

            if(printToConsole){
                System.out.println(LocalDateTime.now().toString() + " WARN: " + message);
            }

        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
