package pl.coderslab;

import java.sql.SQLException;

//TODO hashowanie hasła w update
//TODO findAll
public class MainDao {
    public static void main(String[] args) throws SQLException {
        boolean create = false;
        boolean read = false;
        boolean update = false;
        boolean delete = false;
        boolean findAll = true; //nie działa (jeszcze)

        if (create) {
            User user1 = new User();
            User.setEmail("test2@email");
            User.setUserName("test");
            User.setPassword("testword");

            UserDao userDaoCreate = new UserDao();
            userDaoCreate.create(user1);
        }

        if (read) {
            UserDao userDaoRead = new UserDao();
            userDaoRead.read(4);
            UserDao.printUserDaoData();
        }

        if (update) {
            UserDao userDaoUpdate = new UserDao();
            int userID = 3;
            User userUpdate = userDaoUpdate.read(userID);
            User.setId(userID);
            User.setEmail("newguy@mail");
            User.setUserName("newguy");
            User.setPassword("newguyword");
            userDaoUpdate.update(userUpdate);
        }
        if (delete) {
            UserDao userDaoDelete = new UserDao();
            userDaoDelete.delete(1);
        }
        if (findAll) {
            UserDao userDaoFindAll = new UserDao();
            User[] userArrResult = userDaoFindAll.findAll();
            User userResult = userArrResult[1];
            System.out.println(userResult.getUserName());
        }
    }
}
