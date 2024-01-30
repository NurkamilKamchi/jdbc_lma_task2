package java12.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Configuraion {

    private final static String url = "jdbc:postgresql://localhost:5432/test_project";
    private final static String username = "postgres";
    private final static String password = "nurkamil192.168";

    public static Connection getConnection() {
        Connection connection = null;
        try  {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return connection ;
    }
}
