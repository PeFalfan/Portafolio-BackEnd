package cl.medvet.medvetbackend.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;

public class DataBaseConnection {

    private static String url = "jdbc:mysql://localhost:3306/proyecto_portafolio?serverTimezine=America/Santiago;max_allowed_packet=33554432";
    private static String userName = "root";
    private static String password = "knuuto56";
    private static BasicDataSource pool;

    public static BasicDataSource getInstance() throws SQLException {
        if (pool == null){
            pool = new BasicDataSource();
            pool.setUrl(url);
            pool.setUsername(userName);
            pool.setPassword(password);
            pool.setInitialSize(20);
            pool.setMinIdle(20);
            pool.setMaxIdle(90);
            pool.setMaxTotal(90);

        }
        return pool;
    }

    public static Connection getConnection() throws SQLException {
        return getInstance().getConnection();
    }

}
