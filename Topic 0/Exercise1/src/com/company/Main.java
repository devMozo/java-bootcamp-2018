package com.company;
/**
 * Represents the creation of a singleton example for a database connection.
 * @author Mozo Nicolas
 * @version 1.0
 * @since 1.0
 */
public class Main {
    /**
     * Main
     * @param args
     */
    public static void main(String[] args) {
        // Show the instance
        System.out.println(ConnectionSingleton.getInstance().toString());
    }
}