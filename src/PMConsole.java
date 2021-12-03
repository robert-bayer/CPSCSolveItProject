import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class PMConsole {

    public static void main(String[] args) {
        String UserString = "";
        List<Project> projects = new ArrayList<>();
        Scanner stdinScanner = new Scanner(System.in);
        int UserInt;

        System.out.println("Welcome to Project Manager!\nThis program is designed to help you separate work and home while you work from home.\nIn here, you will be able to start, manage, and archive projects as you work on them.\nProjects can be as simple as reading a paper, to curing cancer.\nEach project has the ability to save tasks or goals, set a deadline for the project, and set task statuses.");

        //This Is the Main Execution Loop
        do {
            System.out.println("\n\nMain Menu:\n1)Create new Project\n2)Open Project\n3)Delete Project\n4)Display Projects\n5)Exit");
            System.out.print("\nEnter the numeral for the options you would like: ");
            UserInt = stdinScanner.nextInt();

            if (UserInt < 1 || UserInt > 5) {
                System.err.println("\nI'm sorry, that isn't an option.  Please try again.\n");
            }
            //Creating a Project
            else if (UserInt == 1) {
                stdinScanner.nextLine();
                Project newProject = new Project();

                System.out.print("Please Enter the Name of the Project: ");
                newProject.setName(stdinScanner.nextLine());
                System.out.println("Let's work on " + newProject.getName() + "!");

                //Inserting Tasks into the New Project
                while(!UserString.equals("done")){
                    System.out.print("Add a task to " + newProject.getName() + " (Type 'done' when your are done) : ");
                    UserString = stdinScanner.nextLine();
                    if (UserString.equals("done") || UserString.equals("Done")){
                        break;
                    }
                    newProject.addTask(UserString);
                }

                //Project Manipulation
                do{
                    System.out.println("\n\nProject Menu (" + newProject.getName() + ")\n1)Display Tasks\n2)Create Task\n3)Delete Task\n4)Close Project");
                    System.out.print("Please Enter the numeral for the option you want: ");
                    UserInt = stdinScanner.nextInt();

                    if(UserInt < 1 || UserInt > 4){
                        System.err.println("That is not an option, please try again.");
                    }

                    else if(UserInt == 1){
                        System.out.println(newProject.taskToString());
                    }
                    else if(UserInt == 2){
                        stdinScanner.nextLine();
                        System.out.print("\n\nEnter a task to add: ");
                        newProject.addTask(stdinScanner.nextLine());
                    }
                    else if(UserInt == 3){
                        stdinScanner.nextLine();
                        boolean status = false;
                        int index = 0;
                        System.out.println(newProject.taskToString());
                        System.out.print("\n\nEnter the task you want to delete:");
                        UserString = stdinScanner.nextLine();
                        System.out.println(newProject.removeTask(UserString));
                    }
                    else if(UserInt == 4){
                        System.out.println("Saving to file.");
                        newProject.saveToFile();
                    }
                }
                while(UserInt != 4);
            }

            else if (UserInt == 2) {

            } else if (UserInt == 3) {
                System.out.println("This is option 3");
            } else if (UserInt == 4) {
                System.out.println("This is option 4");
            }
        }

        while (UserInt != 5);


        /*
        System.out.print("To get stared, let's get started by having you tell us your name: ");
        Name = stdinScanner.next();
        stdinScanner.nextLine();
        System.out.println("Hello, " + Name + "!\nLet's get started by creating a Project!");

        System.out.print("What would you like to name your first project: ");
        Project newProject = new Project(stdinScanner.nextLine());
        //stdinScanner.nextLine(); //consumes \n character



        System.out.println("Awesome! Try Listing some tasks. You can continue listing task, and when you're ready to move on type 'done'.");
        do{
            System.out.print("Enter a task: ");
            userInput = stdinScanner.nextLine();
            if(userInput != null && !userInput.equals("") && !userInput.equals("done")){
                newProject.addTask(userInput);
            }
        }
        while(!userInput.equals("done"));

        System.out.println("Fantastic! By default, all projects are set to a status of 'Not Started'. You will have the option to edit each later.");

        System.out.println("I will now display all of the tasks:");
        System.out.println(newProject.taskToString());
        System.out.print("What task do you want to delete: ");
        newProject.removeTask(stdinScanner.nextLine());
        System.out.println("Here is the new list: " + newProject.taskToString());
        System.out.println("Let's try saving it to a file.");
        newProject.saveToFile();
        System.out.println("This concludes our demo.");
    }

 */
    }
}
