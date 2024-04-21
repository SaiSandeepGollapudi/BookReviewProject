//package com.goodReads.library.service.impl;
//
//import com.goodReads.library.service.DBService;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.Objects;
//
//public class MySqlDBImpl implements DBService {
//
//    private Connection connection;
//    /**
//     * This method is used to obtain a database connection.
//     * If the connection object is null, meaning there is no established database connection,
//     * it calls the createConnection() method to create a new database connection.
//     * After ensuring that a connection exists, it returns the connection object.
//     * This design ensures that only one database connection is created and reused whenever needed.
//     */public Connection getDatabase() {
//        // If the connection object is null, create a new connection
//        if (Objects.isNull(connection)) {
//            createConnection();
//        }
//        // Return the established connection
//        return connection;
//    }
//
//
//    private void createConnection(){
//        try {
//            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "library","Library@2406");
//        } catch (SQLException e) {
//            System.out.println("Exception here:"+ e.getMessage());
//        }
//    }
//}
