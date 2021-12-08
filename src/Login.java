import java.io.*;
import java.util.*;

/**
 * Basic log in system for the PMConsole.  Gets the working user.
 * @author Andrew Gonye
 * @author Robert Bayer
 * @author Clayton Everitt
 * @version 2.0
 *
 */

public class Login {
    private static int UserInt = 0;
    private static int FunnyNumber = 0;
    private static String UserString = "";
    private static String workingUser = "";
    private static FileWriter out = null;
    private static List<User> userList = new ArrayList<>();
    private static Scanner stdinScanner = new Scanner(System.in);
    private static Scanner fileScanner = null;
    private static File[] dir = new File(System.getProperty("user.dir") + "\\src").listFiles();
    private static boolean status = false;
    private static boolean bigStatus = false;
    private static List<String> FileImport = new ArrayList<>();
    private static List<Project> ProjectList = new ArrayList<>();

    /**
     * closes a writer
     * @param file filename
     */
    public static void closeWriter(Writer file) {
        try {
            if (file != null) {
                file.close();
            }
        }
        catch (IOException e){
            System.err.println("ERROR: There was an IOException when trying to close the file: " + e.getMessage());
        }
    }
    /**
     * Checks for accuracy of a username
     * @param name : a username the user has input
     * @param Usernames : an arraylist of usernames
     * @return : true if the username is in the arraylist, false otherwise
     */
    public static boolean usernameCheck(String name, ArrayList<String> Usernames){

        if (Usernames.contains(name)) {
            System.out.println("Username found!");
            return true;
        } else {
            System.out.println("Username not found.");
            return false;
        }
    }
    /**
     * Checks for accuracy of a password
     * @param password : a password the user has input
     * @param Users : an arraylist of Users
     * @param index : the index of a specific User
     * @return : true if the passwords match, false if otherwise
     */
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

    /**
     * gets a working user string
     * @return working user
     */
    public static String getWorkingUser(){
        return workingUser;
    }

    /**
     * gets file information as well as fetching certain information the user requests
     * @return a new user, or just provides a login
     */
    public static List<Project> main() {


        for (File filename : dir){
            if(filename.getName().equals("User.txt")){
                try{
                    fileScanner = new Scanner( new FileReader(System.getProperty("user.dir")+"\\src\\User.txt"));
                    while(fileScanner.hasNext()){
                        User newUser = new User(fileScanner.next().strip(), fileScanner.next().strip());
                        userList.add(newUser);
                    }
                }
                catch(FileNotFoundException e){
                    System.err.println("ERROR: No valid filename was found: "+e.getMessage());
                }
                catch(InputMismatchException e){
                    System.err.println("ERROR: Unable to parse input: " + e.getMessage());
                }
                catch(Exception e){
                    System.err.println("ERROR: " + e.getMessage());
                }
                finally{
                    if (fileScanner != null){
                        fileScanner.close();
                        File file = new File(System.getProperty("user.dir")+"\\src\\User.txt");
                        file.delete();
                    }
                }
                break;
            }
        }


        do{
            status = false;
            System.out.println("\n\n1) Existing User\n2) New User\n3) Close");
            System.out.print("Please enter your choice: ");
            UserString = stdinScanner.next();

            if(!PMConsole.isInteger(UserString)){
                System.err.println("ERROR: That is not an acceptable input.");
            }
            else{
                UserInt = Integer.parseInt(UserString);
                stdinScanner.nextLine();
            }

            if(UserInt == 1){
                status = false;
                do{
                    System.out.print("Please Input your Username ('cancel' to go back): ");
                    UserString = stdinScanner.nextLine().strip();
                    for(int j = 0; j<userList.size(); j++){
                        if(userList.get(j).getUsername().equals(UserString)){
                            do {
                                System.out.print("Please input your password: ");
                                UserString = stdinScanner.nextLine().strip();
                                if (userList.get(j).getPassword().equals(UserString)) {
                                    System.out.println("Logging in!");
                                    status = true;
                                    workingUser = userList.get(j).getUsername().strip();
                                    for(File filename : dir){
                                        if(filename.getName().equals(workingUser + ".txt")){
                                            try{
                                                fileScanner = new Scanner( new FileReader(System.getProperty("user.dir")+"\\src\\" + workingUser + ".txt"));
                                                while(fileScanner.hasNext()){
                                                    FileImport.add(fileScanner.nextLine().strip());
                                                }
                                            }
                                            catch(FileNotFoundException e){
                                                System.err.println("ERROR: No valid filename was found: "+e.getMessage());
                                            }
                                            catch(InputMismatchException e){
                                                System.err.println("ERROR: Unable to parse input: " + e.getMessage());
                                            }
                                            catch(Exception e){
                                                System.err.println("ERROR: " + e.getMessage());
                                            }
                                            finally{
                                                if (fileScanner != null){
                                                    fileScanner.close();
                                                    File file = new File(System.getProperty("user.dir")+"\\src\\User.txt");
                                                    file.delete();
                                                }
                                            }
                                            break;
                                        }
                                    }
                                    bigStatus = true;
                                }
                            }
                            while(!status);
                        }
                        if (status){
                            break;
                        }
                    }
                }
                while(!status && !UserString.equalsIgnoreCase("cancel"));
            }
            else if (UserInt == 2){
                User newUser = new User();
                System.out.print("What is your desired Username: ");
                newUser.setUsername(stdinScanner.nextLine().strip());
                System.out.println("You entered: " + newUser.getUsername());
                System.out.print("What is your desired password: ");
                newUser.setPassword(stdinScanner.nextLine().strip());
                System.out.println("You entered: " + newUser.getPassword());
                userList.add(newUser);
                workingUser = newUser.getUsername();
                bigStatus = true;
            }
            else if(UserInt == 3){
                break;
            }
        }
        while(UserInt != 3 && !bigStatus);

        try{
            out = new FileWriter(System.getProperty("user.dir")+"\\src\\User.txt");
            for (User user : userList){
                out.write(user.getUsername() + " " + user.getPassword() + "\n");
            }
        }
        catch(IOException e){
            System.err.println("ERROR: There was an IOException when writing to file: " + e.getMessage());
        }
        catch(Exception e){
            System.err.println("ERROR: There was an Exception when writing to file: " + e.getMessage());
        }
        finally{
            System.out.println("Success!");
            closeWriter(out);
        }
        System.out.println(FileImport);
        for (String name : FileImport) {
            ProjectList.add(Project.getProjects(name));
        }

        if(UserInt == 3){
            System.out.println("Goodbye");
            System.exit(0);
        }
        return ProjectList;
    }
}
