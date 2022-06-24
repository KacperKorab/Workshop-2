package pl.coderslab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainDao {
    public static void main(String[] args) {

        User user1 = new User();
        User.setEmail("gal@email");
        User.setUserName("gal");
        User.setPassword("galword");

        UserDao userDao = new UserDao();
        userDao.create(user1);
    }
}
