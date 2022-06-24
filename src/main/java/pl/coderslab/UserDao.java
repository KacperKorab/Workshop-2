package pl.coderslab;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.Arrays;

public class UserDao {
    public static void printUserDaoData() {
        User.printUserData();
    }

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    private User[] addToArray(User u, User[] users) {
        User[] tmpUsers = Arrays.copyOf(users, users.length + 1); // Tworzymy kopię tablicy powiększoną o 1.
        tmpUsers[users.length] = u; // Dodajemy obiekt na ostatniej pozycji.
        return tmpUsers; // Zwracamy nową tablicę.
    }

    public User[] findAll() throws SQLException {
        Connection conn = DbUtil.getConnection("workshop2");
        int userID = 1;
        User[] users = new User[0];
        User user = new User();

        do {
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM users WHERE id = " + userID;
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                String email = result.getString("email");
                User.setEmail(email);
                String username = result.getString("username");
                User.setUserName(username);
                String password = result.getString("password");
                User.setPassword(password);
                users = addToArray(user, users);
            }
            userID++;
        } while (userID < 10);
        return users;
    }


    public void delete(int UserID) throws SQLException {
        Connection conn = DbUtil.getConnection("workshop2");
        String query = "DELETE FROM users WHERE id=?";

        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, String.valueOf(UserID));

        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("A user was deleted successfully!");
        }
    }

    public void update(User user) throws SQLException {
        Connection conn = DbUtil.getConnection("workshop2");
        String query = "UPDATE users SET email=?, username=?, password=? WHERE id=?";

        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, User.getEmail());
        statement.setString(2, User.getUserName());
        statement.setString(3, User.getPassword());
        statement.setString(4, String.valueOf(User.getId()));
        statement.executeUpdate();

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("An existing user was updated successfully!");
        }
    }

    public User read(int userID) throws SQLException {
        Connection conn = DbUtil.getConnection("workshop2");
        String query = "SELECT * FROM users WHERE id = " + userID;
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(query);

        User user = new User();
        while (result.next()) {
            String email = result.getString("email");
            User.setEmail(email);
            String username = result.getString("username");
            User.setUserName(username);
            String password = result.getString("password");
            User.setPassword(password);

            String output = "User with ID %s:\n%s - %s - %s";
            System.out.printf((output) + "%n", userID, email, username, password);
        }
        return user;
    }

    public User create(User user) {
        try (Connection conn = DbUtil.getConnection("workshop2")) {
            String CREATE_USER_QUERY = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, User.getUserName());
            statement.setString(2, User.getEmail());
            statement.setString(3, hashPassword(User.getPassword()));
            statement.executeUpdate();
            //Pobieramy wstawiony do bazy identyfikator, a następnie ustawiamy id obiektu user.
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                User.setId(resultSet.getInt(1));
            }
            return user;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
