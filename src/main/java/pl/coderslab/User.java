package pl.coderslab;

public class User {
    private static int id;
    private static String email;
    private static String userName;
    private static String password;

    //    public User(String email, String userName, String password) {
//        this.email = email;
//        this.userName = userName;
//        this.password = password;
//    }
    public static void printUserData() {
        System.out.println("User data:\n" + email + " - " + userName + " - " + password);
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        User.id = id;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        User.email = email;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String username) {
        User.userName = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        User.password = password;
    }
}
