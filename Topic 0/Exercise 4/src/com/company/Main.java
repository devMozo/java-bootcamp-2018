package com.company;

import com.company.builders.*;
/**
 * Represents the creation of a builder example for database connection.
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
		// MYSQL
		BuilderMySQL oBuilderMySQL = new BuilderMySQL();
		BuilderDirector oBuilderDirector = new BuilderDirector(oBuilderMySQL);
		oBuilderDirector.constructDatabase();
		oBuilderDirector.getDB().connect();
		// MARIADB
		BuilderMariaDB oBuilderMariaDB = new BuilderMariaDB();
		oBuilderDirector = new BuilderDirector(oBuilderMariaDB);
		oBuilderDirector.constructDatabase();
		oBuilderDirector.getDB().connect();
		// MONGODB
		BuilderMongoDB oBuilderMongoDB = new BuilderMongoDB();
		oBuilderDirector = new BuilderDirector(oBuilderMongoDB);
		oBuilderDirector.constructDatabase();
		oBuilderDirector.getDB().connect();
		// POSTGRESQL
		BuilderPostgreSQL oBuilderPostgreSQL = new BuilderPostgreSQL();
		oBuilderDirector = new BuilderDirector(oBuilderMySQL);
		oBuilderDirector.constructDatabase();
		oBuilderDirector.getDB().connect();
    }
}