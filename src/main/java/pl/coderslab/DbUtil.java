package pl.coderslab;

import java.sql.*;

public class DbUtil {
    public static final String DB_USER = "root";
    public static final String DB_PASS = "coderslab";
    private static final String DELETE_QUERY = "DELETE FROM tableName where id = ?";

    public static String DB_URL(String DB_NAME) {
        return "jdbc:mysql://localhost:3306/" + DB_NAME + "?useSSL=false&characterEncoding=utf8&serverTimezone=UTC";
    }

    public static Connection getConnection(String dataBaseName) throws SQLException {
        Connection result = null;
        try {
            result = DriverManager.getConnection(DB_URL(dataBaseName), DB_USER, DB_PASS);
            if (result != null) {
                System.out.println("Connected to '" + dataBaseName + "' database.");
            }
            return result;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
