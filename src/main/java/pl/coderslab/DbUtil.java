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
        return DriverManager.getConnection(DB_URL(dataBaseName), DB_USER, DB_PASS);
    }

    public static void insert(Connection conn, String query, String... params) {
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                statement.setString(i + 1, params[i]);
            }
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void printData(Connection conn, String query, String... columnNames) throws SQLException {

        try (PreparedStatement statement = conn.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                for (String columnName : columnNames) {
                    System.out.println(resultSet.getString(columnName));
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void remove(Connection conn, String tableName, int id) {
        try (PreparedStatement statement =
                     conn.prepareStatement(DELETE_QUERY.replace("tableName", tableName))) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
