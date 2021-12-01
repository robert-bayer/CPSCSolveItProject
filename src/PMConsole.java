import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class PMConsole {

    public static void main(String[] args){
        String Name;
        List<String> projects = new ArrayList<>();
        Scanner stdinScanner = new Scanner(System.in);
        String userInput;

        System.out.println("Welcome to Project Manager!\nThis program is designed to help you separate work and home while you work from home.\nIn here, you will be able to start, manage, and archive projects as you work on them.\nProjects can be as simple as reading a paper, to curing cancer.\nEach project has the ability to save tasks or goals, set a deadline for the project, and set task statuses.");

        System.out.print("To get stared, let's get started by having you tell us your name: ");
        Name = stdinScanner.next();
        System.out.println("Hello, " + Name + "!\nLet's get started by creating a Project!");

        System.out.print("What would you like to name your first project: ");
        Project newProject = new Project(stdinScanner.next());
        stdinScanner.nextLine(); //consumes \n character

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
}
