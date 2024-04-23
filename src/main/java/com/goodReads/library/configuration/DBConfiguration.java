package com.goodReads.library.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration// spring will call this before starting the project and everything inside this will be configured
public class DBConfiguration {

    @Value("${mysql-uri}")// get value for this from application properties file
    private String uri;
    @Value("${mysql-username}")
            private String username;
    @Value("${mysql-password}")

        private String password;
//  //  @Bean("mysqlDatabaseConnection")
//    @Bean("myDatabase")
//    @Primary// if we don't ask for a specific connection as it's marked primary by default this will be given but
//    public Connection mysqlConnection(){// so based on specific host, username, given dynamically a specific connection
//        // will be given
//        System.out.println("Getting called from configuration");
//        System.out.println("host:"+host+"username"+username+"password"+password);
//        Connection connection=null;
//        try {
//            connection= DriverManager.getConnection("jdbc:mysql://"+host+"/library", username,password);
//        } catch (SQLException e) {
//            System.out.println("Exception here:"+ e.getMessage());
//        }
//        return connection;
//    }

//    @Bean("oracleDatabaseConnection")
//    public Connection oracleConnection(){
//        System.out.println("Getting called from  oracleConnection configuration");
//        Connection connection=null;
//        try {
//            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "library","Library@2406");
//        } catch (SQLException e) {
//            System.out.println("Exception here:"+ e.getMessage());
//        }
//        return connection;
//    }

}
