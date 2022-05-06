package com.robb.banking;

import com.robb.banking.util.AppState;

import com.robb.banking.models.Customer_info;
import com.robb.banking.models.Account_info;

import java.io.*;

public class MainDriver {

    static BufferedReader terminalReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main (String[] args){

        System.out.println("1. AppState Instantiated");
        AppState appState = new AppState();

        System.out.println("Robb's Banking Application starting up...");
        appState.Startup();

    }
}
