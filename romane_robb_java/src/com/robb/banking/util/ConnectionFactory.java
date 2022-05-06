package com.robb.banking.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
public class ConnectionFactory {

    private static final ConnectionFactory connectionFactory = new ConnectionFactory();

    private Properties prop = new Properties();

    private ConnectionFactory(){
        try {
            prop.load(new FileReader("src/main/resources/db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionFactory getInstance() { return connectionFactory; }

    public Connection getConnection() {

        Connection conn = null;

        // This properties files helps obfuscate the information for connecting out our database.

        String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=romane_robb_p0";
        String user = "postgres";
        String password = "4383369yo";

        try {
            conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }
}
