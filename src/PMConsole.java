import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class PMConsole {


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

    public static void main(String[] args) {
        String UserString = "";
        String currentProject = "";
        List<Project> projects = new ArrayList<>();
        Scanner stdinScanner = new Scanner(System.in);
        int UserInt = 0;

        System.out.println("Welcome to Project Manager!\nThis program is designed to help you separate work and home while you work from home.\nIn here, you will be able to start, manage, and archive projects as you work on them.\nProjects can be as simple as reading a paper, to curing cancer.\nEach project has the ability to save tasks or goals, set a deadline for the project, and set task statuses.");
        /*
        HERE IS WHERE THE LOG IN WILL HAPPEN, AFTER LOG IN, IMPORT ALL PROJECT.TXT FILES INTO PROJECT ARRAYLIST
         */

        projects.add(Project.openProject("Dude"));
        projects.add(Project.openProject("Hithere"));

        //This Is the Main Execution Loop
        do {
            System.out.println("\n\nMain Menu:\n1)Create new Project\n2)Open Project\n3)Delete Project\n4)Display Projects\n5)Exit");
            System.out.print("\nEnter the numeral for the options you would like: ");
            UserString = stdinScanner.next();



            if (!isInteger(UserString)) {
                System.err.println("\nI'm sorry, that isn't an option.  Please try again.\n");
            }
            else{
                UserInt = Integer.parseInt(UserString);
            }

            if(UserInt < 1 || UserInt > 5 ){
                System.err.println("\nI'm sorry, that isn't an option.  Please try again.\n");
            }
            //Creating a Project
            if (UserInt == 1) {
                stdinScanner.nextLine();
                Project newProject = new Project();

                System.out.print("Please Enter the Name of the Project: ");
                newProject.setName(stdinScanner.nextLine());
                System.out.println("Let's work on " + newProject.getName() + "!");

                //Inserting Tasks into the New Project

                //stdinScanner.nextLine();
                while (!UserString.equals("done")) {
                    System.out.print("Add a task to " + newProject.getName() + " (Type 'done' when your are done) : ");
                    UserString = stdinScanner.nextLine();
                    if (UserString.equals("done") || UserString.equals("Done")) {
                        break;
                    }
                    newProject.addTask(UserString);
                }
                projects.add(newProject);
                currentProject = newProject.getName();

                //Project Manipulation
                do {
                    System.out.println("\n\nProject Menu (" + newProject.getName() + ")\n1)Display Tasks\n2)Create Task\n3)Delete Task\n4)Close Project");
                    System.out.print("Please Enter the numeral for the option you want: ");
                    UserString = stdinScanner.next();

                    if (!isInteger(UserString)) {
                        System.err.println("\nI'm sorry, that isn't an option.  Please try again.\n");
                    }
                    else{
                        UserInt = Integer.parseInt(UserString);
                    }
                    if (UserInt < 1 || UserInt > 4) {
                        System.err.println("That is not an option, please try again.");
                    } else if (UserInt == 1) {
                        for (Project project : projects) {
                            if (project.getName().equals(currentProject)) {
                                System.out.println(project.taskToString());
                            }
                        }
                    } else if (UserInt == 2) {
                        stdinScanner.nextLine();
                        System.out.print("\n\nEnter a task to add: ");
                        UserString = stdinScanner.nextLine();
                        for (Project project : projects) {
                            if (project.getName().equals(currentProject)) {
                                project.addTask(UserString);
                            }
                        }
                    } else if (UserInt == 3) {
                        stdinScanner.nextLine();
                        System.out.println(newProject.taskToString());
                        System.out.print("\n\nEnter the task you want to delete:");
                        UserString = stdinScanner.nextLine();
                        for (Project project : projects) {
                            if (project.getName().equals(currentProject)) {
                                System.out.println(project.removeTask(UserString));
                            }
                        }
                    } else if (UserInt == 4) {
                        System.out.println("Saving to file.");
                        for (Project project : projects) {
                            if (project.getName().equals(currentProject)) {
                                project.saveToFile();
                            }
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
                for (Project project : projects) {
                    if (UserString.equalsIgnoreCase(project.getName())) {
                        currentProject = project.getName();
                        do {
                            System.out.println("\n\nProject Menu (" + currentProject + ")\n1)Display Tasks\n2)Create Task\n3)Delete Task\n4)Close Project");
                            System.out.print("Please Enter the numeral for the option you want: ");
                            UserString = stdinScanner.next();



                            if (!isInteger(UserString)) {
                                System.err.println("\nI'm sorry, that isn't an option.  Please try again.\n");
                            }
                            else{
                                UserInt = Integer.parseInt(UserString);
                            }

                            if (UserInt < 1 || UserInt > 4) {
                                System.err.println("That is not an option, please try again.");
                            } else if (UserInt == 1) {
                                System.out.println(project.taskToString());
                            } else if (UserInt == 2) {
                                stdinScanner.nextLine();
                                System.out.print("\n\nEnter a task to add: ");
                                UserString = stdinScanner.nextLine();
                                project.addTask(UserString);
                            } else if (UserInt == 3) {
                                stdinScanner.nextLine();
                                System.out.println(project.taskToString());
                                System.out.print("\n\nEnter the task you want to delete:");
                                UserString = stdinScanner.nextLine();
                                System.out.println(project.removeTask(UserString));
                            } else if (UserInt == 4) {
                                System.out.println("Saving to file.");
                                project.saveToFile();
                            }
                        }
                        while (UserInt != 4);
                    } else {
                        System.err.println("There is no project named " + UserString);
                    }
                }

            } else if (UserInt == 3) {
                stdinScanner.nextLine();
                int numeral = 1;
                for (Project project : projects) {
                    System.out.println(numeral + ") " + project.getName() + " - Tasks: " + project.taskToString());
                    numeral++;
                }
                System.out.println("What project would you like to delete?");
                UserString = stdinScanner.nextLine();
                boolean projectStatus = false;
                for (Project project : projects) {
                    if (UserString.equalsIgnoreCase(project.getName())) {
                        System.out.println(project.removeTask(UserString));
                        projectStatus = true;
                    }
                }
                if (projectStatus) {
                    System.out.println("Successfully deleted project.");
                } else {
                    System.err.println("ERROR: Could not find project.");
                }

            } else if (UserInt == 4) {
                int numeral = 1;
                for (Project project : projects) {
                    System.out.println(numeral + ") " + project.getName() + " - Tasks: " + project.taskToString());
                    numeral++;
                }
            }
        }
        while (UserInt != 5);
        System.out.println("We will see you next time!");
    }
}

