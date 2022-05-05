package com.robb.banking;

import com.robb.banking.util.AppState;
public class MainDriver {

    public static void main (String[] args){

        System.out.println("1. AppState Instantiated");
        AppState appState = new AppState();

        System.out.println("Robb Banking Application starting up...");
        appState.Startup();

    }
}
