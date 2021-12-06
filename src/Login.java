import java.io.*;
import java.util.*;

public class Login {
    private static int UserInt = 0;
    private static int FunnyNumber = 0;
    private static String UserString = "";
    private static String workingUser = "";
    private static FileWriter out = null;
    private static List<User> userList = new ArrayList<>();
    private static Scanner stdinScanner = new Scanner(System.in);
    private static Scanner fileScanner = null;
    //String directory = System.getProperty("user.dir") + "\\src";
    private static File[] dir = new File(System.getProperty("user.dir") + "\\src").listFiles();
    //System.out.println(dir);
    private static boolean status = false;
    private static boolean bigStatus = false;
    private static List<String> FileImport = new ArrayList<>();
    private static List<Project> ProjectList = new ArrayList<>();

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
    public static String getWorkingUser(){
        return workingUser;
    }

    public static List<Project> main() {


        /*
        System.out.println(directory);
        for (File filename : dir) {
            if (filename.isDirectory()) {
                System.out.println("Directory: " + filename.getName());
            } else {
                System.out.println("File: " + filename.getName());
            }
        }

         */
        //System.out.println(System.getProperty("user.dir")+"\\src\\User.txt");
        for (File filename : dir){
            //System.out.println(filename.getName());
            //System.out.println(System.getProperty("user.dir")+"\\src\\User.txt");
            if(filename.getName().equals("User.txt")){
                //System.out.println("Found the file!");
                try{
                    fileScanner = new Scanner( new FileReader(System.getProperty("user.dir")+"\\src\\User.txt"));
                    while(fileScanner.hasNext()){
                        User newUser = new User(fileScanner.next().strip(), fileScanner.next().strip());
                        userList.add(newUser);
                        //System.out.println(userList);
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
                                            //System.out.println("Found the file!");
                                            try{
                                                fileScanner = new Scanner( new FileReader(System.getProperty("user.dir")+"\\src\\" + workingUser + ".txt"));
                                                while(fileScanner.hasNext()){
                                                    FileImport.add(fileScanner.nextLine().strip());
                                                    //System.out.println(userList);
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
                                    UserInt = 3;
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
        }
        while(UserInt != 3 || !bigStatus);

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
            System.err.println("ERROR: There was an Excpetion when writing to file: " + e.getMessage());
        }
        finally{
            System.out.println("Sucess!");
            closeWriter(out);
        }
        System.out.println(FileImport);
        for (String name : FileImport) {
            ProjectList.add(Project.getProjects(name));
        }
        return ProjectList;
    }
}


