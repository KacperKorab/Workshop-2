package pl.coderslab;

import java.sql.SQLException;
import java.util.Arrays;

public class MainDao {
    public static void main(String[] args) throws SQLException {
        boolean create = false;
        boolean read = false;
        boolean update = false;
        boolean delete = false;
        boolean findAll = false;

        if (create) {
            User user1 = new User();
            User.setEmail("bloke@email");
            User.setUserName("bloke");
            User.setPassword("blokeword");

            UserDao userDaoCreate = new UserDao();
            userDaoCreate.create(user1);
        }

        if (read) {
            UserDao userDaoRead = new UserDao();
            userDaoRead.read(1);
            UserDao.printUserDaoData();
        }

        if (update) {
            UserDao userDaoUpdate = new UserDao();
            int userID = 1;
            User userUpdate = userDaoUpdate.read(userID);
            User.setId(userID);
            User.setEmail("newbro@mail");
            User.setUserName("newbro");
            User.setPassword("newbroword");
            userDaoUpdate.update(userUpdate);
        }
        if (delete) {
            UserDao userDaoDelete = new UserDao();
            userDaoDelete.delete(2);
        }
        if (findAll) {
            UserDao userDaoFindAll = new UserDao();
            User[] userArrResult = userDaoFindAll.findAll();
            User userResult = userArrResult[2];
            System.out.println(User.getUserName());
        }
    }
}
