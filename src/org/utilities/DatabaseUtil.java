package org.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;

public class DatabaseUtil {

	public static Connection connection = null;
	public static String DB_IP = null;
	public static String DB_User_ID = null;
	public static String DB_Password = null;
	public static String DB_Name = null;
	public static MongoCredential credential = null;
	public static MongoClient mongoClient = null;
	public static DB mongodb = null;

	public static void initConnection() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return;
		}

		try {
			DB_IP = DriverFactory.getProperty("DB_IP") + "/" + DriverFactory.getProperty("DB_NAME");
			DB_User_ID = DriverFactory.getProperty("DB_USER_ID");
			DB_Password = DriverFactory.getProperty("DB_PASSWORD");
			connection = DriverManager.getConnection("jdbc:mysql://" + DB_IP, DB_User_ID, DB_Password);
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}

		if (connection != null) {
			System.out.println("DB connection established!!");

		} else {
			System.out.println("Failed to make DB connection!");
		}
	}

	public static void closeConnection() {

		try {
			if (connection != null) {
				connection.close();
				System.out.println("DB connection closed!!");
			} else {
				System.out.println("Connection is already closed!!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void initMongoDBConnection() throws SQLException {
		DB_IP = DriverFactory.getProperty("Mongo_DB_IP");
		DB_Name = DriverFactory.getProperty("Mongo_DB_NAME");
		DB_User_ID = DriverFactory.getProperty("Mongo_DB_USER_ID");
		DB_Password = DriverFactory.getProperty("Mongo_DB_PASSWORD");

		mongoClient = new MongoClient(DB_IP, 27017);
		mongodb = mongoClient.getDB(DB_Name);

	}

	public static void closeMongoConnection() {

		try {
			if (mongoClient != null) {
				mongoClient.close();
				System.out.println("DB connection closed!!");
			} else {
				System.out.println("Connection is already closed!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
