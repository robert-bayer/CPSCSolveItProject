import java.util.Scanner;
import java.util.ArrayList;
public class Login {
int FunnyNumber = 0;

    public static boolean usernameCheck(String name, ArrayList<String> Usernames){
        if (Usernames.contains(name)) {
            System.out.println("Username found!");
            return true;
        } else {
            System.out.println("Username not found.");
            return false;
        }
    }
    public static boolean passwordCheck(String password, ArrayList<User> Users, int index){
        if(Users.get(index).getPassword().equals(password)) {
            System.out.println("Correct Password");
            return true;
        }
        else{
            System.out.println("Incorrect Password");
            return false;
        }
    }
    public static String main() {
        User admin = new User("admin", "password");
        ArrayList<User> UserList = new ArrayList<User>();
        UserList.add(admin);
        ArrayList<String> UsernameList = new ArrayList<String>();
        UsernameList.add(admin.getUsername());
        Scanner userInput = new Scanner(System.in);
        int FunnyNumber = 0;
        while (FunnyNumber != 1) {
        System.out.println("Please input your Username: ");
            User UserTester = new User();
            UserTester.setUsername(userInput.next());
            if (usernameCheck(UserTester.getUsername(), UsernameList) == true){
                System.out.println("Please enter " + UserTester.getUsername() + "'s password");
                UserTester.setPassword(userInput.next());
                int UserIndex = UsernameList.indexOf(UserTester.getUsername());
                if(passwordCheck(UserTester.getPassword(), UserList, UserIndex ) == true){
                    System.out.println("Login Success!");
                    return UserTester.getUsername();
                }
            }
            }
    return null;}
}


