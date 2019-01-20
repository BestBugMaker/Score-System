package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class dbDao {
    private Connection conn = null;
    public Connection initConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/richard?serverTimezone=GMT&characterEncoding=utf-8",
                "root",
                "likecrr123,.");
        return conn;
    }
}
