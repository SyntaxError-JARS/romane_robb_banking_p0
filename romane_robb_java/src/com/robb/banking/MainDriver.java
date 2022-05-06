// The package is used to group related classes. It is like a folder in a file directory.

package com.robb.banking;

// The use of the import keyword is the import a package, class, or interface.

import com.robb.banking.util.AppState;
import com.robb.banking.models.Customer_info;
import com.robb.banking.models.Account_info;
import java.io.*;

// The use of the public class is to make the Customer_info class accessible and visible to all other classes.
public class MainDriver {

    // public makes the class accessible and visible to all other classes.
    // static means that the method belongs to the Main class and not an object of the Main class.
    // void means that this method does not have a return value.
    // main is the access modifier of the main method. It has to be public. It has to be written EXACTLY like that.
    // The String array argument can be written as: String... args or String args[].
    public static void main (String[] args){

        // System is a class.
        // out is a Variable.
        // println() is a method.
        // This is used to print out the argument passed within the parenthesis.
        System.out.println("1. AppState Instantiated");
        AppState appState = new AppState();

        System.out.println("Robb's Banking Application starting up...");
        appState.startup();

    }
}