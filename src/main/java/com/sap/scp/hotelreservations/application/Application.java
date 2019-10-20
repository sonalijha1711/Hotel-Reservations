package com.sap.scp.hotelreservations.application;

import com.sap.scp.hotelreservations.handler.InputHandler;

public class Application {

    public static void main(String[] args) {
        InputHandler.handleInput();
        System.out.println("*************Thank You for visiting our Hotel ***********\n");

    }
}