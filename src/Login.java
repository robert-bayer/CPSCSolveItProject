import java.io.*;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
public class Login {
    private static int UserInt = 0;
    private static int FunnyNumber = 0;
    private static String UserString = "";

    public static void closeWriter(Writer file) {
        try {
            if (file != null) {
                file.close();
            }
        } catch (IOException e) {
            System.err.println("ERROR: There was an IOException when trying to close the file: " + e.getMessage());
        }
    }

    public static boolean usernameCheck(String name, ArrayList<String> Usernames) {
        if (Usernames.contains(name)) {
            System.out.println("Username found!");
            return true;
        } else {
            System.out.println("Username not found.");
            return false;
        }
    }

    public static boolean passwordCheck(String password, ArrayList<User> Users, int index) {
        if (Users.get(index).getPassword().equals(password)) {
            System.out.println("Correct Password");
            return true;
        } else {
            System.out.println("Incorrect Password");
            return false;
        }
    }

    public static String main() {
        FileWriter out = null;
        List<User> UserList = new ArrayList<>();
        Scanner stdinScanner = new Scanner(System.in);
        Scanner fileScanner = null;
        File dir = new File(System.getProperty("user.dir"));
        boolean status = false;
        boolean bigStatus = false;
        String workingUser = "";

        String[] fileList = dir.list();

        if (fileList == null) {
            System.err.println("ERROR: NO USER.TXT FOUND");
        } else {
            try {
                for (int i = 0; i < fileList.length; i++) {
                    String testFile = fileList[i];
                    if (testFile.equalsIgnoreCase("User.txt")) {
                        fileScanner = new Scanner(new FileReader("User.txt"));
                        status = true;
                        while (fileScanner.hasNext()) {
                            User newUser = new User(fileScanner.next().strip(), fileScanner.next().strip());
                            UserList.add(newUser);
                        }
                    }
                    if (status) {

                        break;
                    }
                }
            } catch (FileNotFoundException e) {
                System.err.println("That filename is not valid: " + e.getMessage());
            } catch (InputMismatchException e) {
                System.err.println("Error, unable to parse input!");
            } catch (Exception e) {
                System.err.println("ERROR: " + e.getMessage());
            }
            finally{
                if(fileScanner != null){
                    fileScanner.close();
                    File file = new File(dir+"User.txt");
                    file.delete();
                }
            }
        }

        //Import Users and Passes from File into ArrayLists

        do {
            status = false;
            System.out.println("1) Existing User\n2) New User\nClose");
            System.out.print("Please enter your option: ");
            UserString = stdinScanner.next();

            if (!PMConsole.isInteger(UserString)) {
                System.err.println("\nI'm sorry, that isn't an option.  Please try again.\n");
            } else {
                UserInt = Integer.parseInt(UserString);
            }

            if (UserInt == 1) {
                //Logging in a User
                do {
                    System.out.println("Please input your Username (type 'cancel' to go back) : ");
                    UserString = stdinScanner.nextLine().strip();
                    for (int j = 0; j<UserList.size(); j++) {
                        if(UserList.get(j).getUsername().equals(UserString)){
                            status = false;
                            do {
                                System.out.print("Please input your Password: ");
                                UserString = stdinScanner.nextLine().strip();
                                if (UserList.get(j).getPassword().equals(UserString)) {
                                    workingUser = UserList.get(j).getUsername().strip();
                                    status = true;
                                }
                            }
                            while(!status);
                        }
                    }
                }
                while (!status || !UserString.equalsIgnoreCase("cancel"));
            }
            else if (UserInt == 2){
                User newUser = new User();
                System.out.print("What is your Username: ");
                newUser.setUsername(stdinScanner.nextLine().strip());
                System.out.println("You entered: " + newUser.getUsername());
                System.out.print("What is your Password: ");
                newUser.setPassword(stdinScanner.nextLine().strip());
                System.out.println("You entered: " + newUser.getPassword());
                UserList.add(newUser);
                workingUser = newUser.getUsername();
            }


        }
        while (UserInt != 3 || !bigStatus);

        try {
            out = new FileWriter("Users.txt");
            for (User user : UserList) {
                out.write(user.getUsername() + " " + user.getPassword() + "\n");
            }
        } catch (IOException e) {
            System.err.println("ERROR: There was an IOException when writing to file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("ERROR: There was an Exception when writing to file: " + e.getMessage());
        } finally {
            System.out.println("SUCCESS!");
            closeWriter(out);
        }
    return workingUser;
    }
}






        /*
        User admin = new User("admin", "password");
        List<User> UserList = new ArrayList<>();
        UserList.add(admin);
        List<String> UsernameList = new ArrayList<String>();
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
                    FunnyNumber = 1;
                }
            }
            }
         */

