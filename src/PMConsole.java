import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
/**
 * This is  a program designed to allow a user to create projects which contain tasks and change the status of the tasks,
 * all in an 8-hour period, which is timed. This is designed to promote a separation of work and home life for those who
 * work from home.
 *
 * @author Robert Bayer
 * @author Andrew Gonye
 * @author Clayton Everitt
 * @version 2.3
 */

/**
 * This program will track projects and their progress for its users and keep a log of what users get access to what projects.
 *
 *
 *
 *
 *
 */
public class PMConsole {
    /**
          isInteger checks the user input to see if it is an integer.
          @param UserInput is a string that has been submitted by the user through a scanner
         * @return false if the string is not equivalent to an integer value, true otherwise.
         */
    public static boolean isInteger(String UserInput){
        if(UserInput.isEmpty()) {
            return false;
        }
        for(int i = 0; i < UserInput.length(); i++){
            if(i == 0 && UserInput.charAt(i) == '-') {
                if (UserInput.length() == 1) {
                    return false;
                } else {
                    continue;
                }
            }
            if(Character.digit(UserInput.charAt(i),10) < 0){
                return false;
            }
        }
        return true;

    }
    /**
             timecheck looks at the current time remaining in an 8-hour period and returns the hours, minutes, and seconds.
             @param clocktime is the first recorded time in the program with 8 hours added to it.
            * @return false if there is remaining time, true if otherwise
            */
    public static boolean timecheck(long clocktime) {
        if (System.currentTimeMillis() <= clocktime) {
            long Remainingtime = clocktime - System.currentTimeMillis();
            long RemainingSeconds = Remainingtime / 1000;
            long RemainingMinutes = RemainingSeconds / 60;
            long RemainingHours = RemainingMinutes / 60;
            long RemainingMinutesDisplay = RemainingMinutes - (RemainingHours*60);
            long RemainingSecondsDisplay = RemainingSeconds - (RemainingMinutes*60);
            System.out.println("Current time remaining: " +RemainingHours+ " hour(s), "+ RemainingMinutesDisplay+ " minute(s), and "+ RemainingSecondsDisplay + " second(s).");
            return false;
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
        String UserString = "";
        Project currentProject;
        List<Project> projects = new ArrayList<>();
        Scanner stdinScanner = new Scanner(System.in);
        int UserInt = 0;


        projects = Login.main();

        String myUser = Login.getWorkingUser();


        long time = System.currentTimeMillis();
        long time2 = time + (28800000);
        int FunnyNumber = 0;




        while (FunnyNumber != 1) {



            System.out.println("Welcome to Project Manager!\nThis program is designed to help you separate work and home while you work from home.\nIn here, you will be able to start, manage, and archive projects as you work on them.\nProjects can be as simple as reading a paper, to curing cancer.\nEach project has the ability to save tasks or goals, set a deadline for the project, and set task statuses.");


            //This Is the Main Execution Loop
            do {
                currentProject = null;
                System.out.println("\n\nMain Menu:\n1)Create new Project\n2)Open Project\n3)Delete Project\n4)Display Projects\n5)Exit");
                System.out.print("\nEnter the numeral for the options you would like: ");
                UserString = stdinScanner.next();


                if (!isInteger(UserString)) {
                    System.err.println("\nI'm sorry, that isn't an option.  Please try again.\n");
                } else {
                    UserInt = Integer.parseInt(UserString);
                }

                if (UserInt < 1 || UserInt > 5) {
                    System.err.println("\nI'm sorry, that isn't an option.  Please try again.\n");
                }
                //Creating a Project
                if (UserInt == 1) {

                    stdinScanner.nextLine();
                    Project newProject = new Project();

                    System.out.print("Please Enter the Name of the Project: ");
                    newProject.setName(stdinScanner.nextLine());
                    newProject.addToUser(myUser);
                    System.out.println("Let's work on " + newProject.getName() + "!");

                    //Inserting Tasks into the New Project

                    while (!UserString.equals("done")) {
                        System.out.print("Add a task to " + newProject.getName() + " (Type 'done' when your are done) : ");
                        UserString = stdinScanner.nextLine();
                        if (UserString.equals("done") || UserString.equals("Done")) {
                            break;
                        }
                        newProject.addTask(UserString);
                    }
                    projects.add(newProject);
                    currentProject = projects.get(projects.size() - 1);
                    if (timecheck(time2)) {
                        FunnyNumber = 1;
                    }

                    //Project Manipulation
                    do {
                        System.out.println("\n\nProject Menu (" + currentProject.getName() + ")\n1)Display Tasks\n2)Create Task\n3)Delete Task\n4)Close Project");
                        System.out.print("Please Enter the numeral for the option you want: ");
                        UserString = stdinScanner.next();

                        if (!isInteger(UserString)) {
                            System.err.println("\nI'm sorry, that isn't an option.  Please try again.\n");
                        } else {
                            UserInt = Integer.parseInt(UserString);
                        }

                        if (UserInt < 1 || UserInt > 4) {
                            System.err.println("That is not an option, please try again.");
                        } else if (UserInt == 1) {
                            for (Project project : projects) {
                                if (project.getName().equals(currentProject.getName())) {
                                    System.out.println(project.taskToString());
                                }
                            }
                            if (timecheck(time2)) {
                                FunnyNumber = 1;
                            }
                        } else if (UserInt == 2) {
                            stdinScanner.nextLine();
                            System.out.print("\n\nEnter a task to add: ");
                            UserString = stdinScanner.nextLine();
                            for (Project project : projects) {
                                if (project.getName().equals(currentProject.getName())) {
                                    project.addTask(UserString);
                                }
                            }
                            if (timecheck(time2)) {
                                FunnyNumber = 1;
                            }
                        } else if (UserInt == 3) {
                            stdinScanner.nextLine();
                            System.out.println(newProject.taskToString());
                            System.out.print("\n\nEnter the task you want to delete:");
                            UserString = stdinScanner.nextLine();
                            for (Project project : projects) {
                                if (project.getName().equals(currentProject.getName())) {
                                    System.out.println(project.removeTask(UserString));
                                }
                            }
                            if (timecheck(time2)) {
                                FunnyNumber = 1;
                            }
                        } else if (UserInt == 4) {
                            System.out.println("Saving to file.");
                            for (Project project : projects) {
                                if (project.getName().equals(currentProject.getName())) {
                                    project.saveToFile();
                                }
                            }
                            if (timecheck(time2)) {
                                FunnyNumber = 1;
                            }
                        }
                    }
                    while (UserInt != 4);
                } else if (UserInt == 2) {
                    stdinScanner.nextLine();
                    int numeral = 1;
                    for (Project project : projects) {
                        System.out.println(numeral + ") " + project.getName() + " - Tasks: " + project.taskToString());
                        numeral++;
                    }
                    System.out.print("What project would you like to open: ");
                    UserString = stdinScanner.nextLine().strip();

                    if (!isInteger(UserString)) {
                        System.err.println("\nI'm sorry, that isn't an option.  Please try again.\n");
                    } else {
                        UserInt = Integer.parseInt(UserString);
                    }


                    if(UserInt <= projects.size()){
                        currentProject = projects.get(UserInt-1);
                        do {
                            System.out.println("\n\nProject Menu (" + currentProject.getName() + ")\n1)Display Tasks\n2)Create Task\n3)Delete Task\n4)Close Project");
                            System.out.print("Please Enter the numeral for the option you want: ");
                            UserString = stdinScanner.next();

                            if (!isInteger(UserString)) {
                                System.err.println("\nI'm sorry, that isn't an option.  Please try again.\n");
                            } else {
                                UserInt = Integer.parseInt(UserString);
                            }

                            if (UserInt < 1 || UserInt > 4) {
                                System.err.println("That is not an option, please try again.");
                            } else if (UserInt == 1) {
                                System.out.println(currentProject.taskToString());
                                if (timecheck(time2)) {
                                    FunnyNumber = 1;
                                }
                            } else if (UserInt == 2) {
                                stdinScanner.nextLine();
                                System.out.print("\n\nEnter a task to add: ");
                                UserString = stdinScanner.nextLine();
                                currentProject.addTask(UserString);
                                if (timecheck(time2)) {
                                    FunnyNumber = 1;
                                }
                            } else if (UserInt == 3) {
                                stdinScanner.nextLine();
                                System.out.println(currentProject.taskToString());
                                System.out.print("\n\nEnter the task you want to delete:");
                                UserString = stdinScanner.nextLine();
                                System.out.println(currentProject.removeTask(UserString));
                                if (timecheck(time2)) {
                                    FunnyNumber = 1;
                                }
                            } else if (UserInt == 4) {
                                System.out.println("Saving to file.");
                                currentProject.saveToFile();
                                if (timecheck(time2)) {
                                    FunnyNumber = 1;
                                }
                            }
                            if(FunnyNumber == 1) {
                                stdinScanner.nextLine();
                                System.out.print("It's time to stop working!\nPlease enter any remaining thoughts, what your next step should be, or any other notes here (1 Line): ");
                                UserString = stdinScanner.nextLine();
                                currentProject.addTask(UserString);
                                currentProject.saveToFile();
                                System.out.println("We will see you next time!");
                                System.exit(0);
                            }
                        }
                        while (UserInt != 4);
                        }
                    else{
                        System.err.println("There is no project at that choice");
                    }

                }

                else if (UserInt == 3) {
                    stdinScanner.nextLine();
                    int numeral = 1;
                    for (Project project : projects) {
                        System.out.println(numeral + ") " + project.getName() + " - Tasks: " + project.taskToString());
                        numeral++;
                    }
                    System.out.println("What project would you like to delete?");
                    UserString = stdinScanner.nextLine();

                    if (!isInteger(UserString)) {
                        System.err.println("\nI'm sorry, that isn't an option.  Please try again.\n");
                    } else {
                        UserInt = Integer.parseInt(UserString);
                    }

                    boolean projectStatus = false;
                    if (UserInt <= projects.size()){
                        projects.remove(UserInt-1);
                        projectStatus = true;
                    }
                    if (projectStatus) {
                        System.out.println("Successfully deleted project.");
                    } else {
                        System.err.println("ERROR: Could not find project.");
                    }
                    if (timecheck(time2)) {
                        FunnyNumber = 1;
                    }

                } else if (UserInt == 4) {
                    int numeral = 1;
                    for (Project project : projects) {
                        System.out.println(numeral + ") " + project.getName() + " - Tasks: " + project.taskToString());
                        numeral++;
                    }
                    if (timecheck(time2)) {
                        FunnyNumber = 1;
                    }
                } else if (UserInt == 5) {
                    System.out.println("We will see you next time!");
                    System.exit(0);
                }
                if (FunnyNumber == 1) {
                    System.out.println("It's time to stop working!\n We will see you next time!");
                    System.exit(0);
                }
            }
            while (UserInt != 5);
            System.out.println("We will see you next time!");
        }

    }
}
