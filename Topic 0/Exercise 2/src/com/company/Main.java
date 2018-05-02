package com.company;

import com.company.dbs.DBFactory;
/**
 * Represents the creation of an abstract factory example for diferent type of SQL connections.
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
        // Create the factory
        DBFactory oDBFactory = new DBFactory();
        // Show the instances
        System.out.println(oDBFactory.getDB("MySQL").toString());
        System.out.println(oDBFactory.getDB("MongoDB").toString());
        System.out.println(oDBFactory.getDB("MariaDB").toString());
        System.out.println(oDBFactory.getDB("PostgreSQL").toString());
    }
}