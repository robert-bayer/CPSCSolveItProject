import java.io.*;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * * This is a program that scans files to check for project names as well as task names.
 * @author Andrew Gonye
 * @author Robert Bayer
 * @author Clayton Everitt
 * @version 1.5
 */
public class Project {
    private static Scanner fileScanner = null;
    private FileWriter out = null;
    private String Name;
    private List<String> Tasks = new ArrayList<>();
    private List<String> TaskStatus = null;
    private String Deadline;

    public Project() {
        TaskStatus = new ArrayList<>();
    }
    public Project(String Name){
        this.Name = Name;
        TaskStatus = new ArrayList<>();
    }
    public Project(String Name, List<String> tasks){
        this.Name = Name;
        this.Tasks = tasks;
        this.TaskStatus = new ArrayList<>(Tasks.size());
    }

    /**
     * closes a writer
     * @param file text file
     */
    public void closeWriter(Writer file){
        try{
            if(file != null){
                file.close();
            }
        }
        catch(IOException e){
            System.err.println("ERROR: There was an IOException when trying to close the file: " + e.getMessage());
        }
    }

    /**
     * sets a dealine
     * @param deadline string the user has input for a deadline
     */
    public void setDeadline(String deadline){
        this.Deadline = deadline;
    }

    /**
     * returns the deadline the user has set
     * @return deadline string
     */
    public String getDeadline(){
        return Deadline;
    }

    /**
     * sets a name the user has input
     * @param Name string the user has input to set as a name
     */
    public void setName(String Name){
        this.Name = Name;
    }

    /**
     * returns the name the user has input
     * @return name the user has set
     */
    public String getName(){
        return Name;
    }

    /**
     * adds a task to the task list
     * @param task task the user has set
     */
    public void addTask(String task){
        Tasks.add(task);
        TaskStatus.add("Not Started");
    }

    /**
           displays tasks
           @return Tasks String arraylist
          */
    public List<String> displayTasks(){
        return Tasks;
    }
    /**
     *
              *removes a task from the task list
              *@param task is the string of task to be removed
              *returns a string that displays that the task was removed, or the task wasn't found in the list of tasks
             */
    public String removeTask(String task){
        boolean status = false;
        int index = 0;

        for (String term : Tasks) {
            if (term.equals(task)) {
                status = true;
                break;
            }
            index++;
        }

        if (!status){
            return "I'm sorry, " + task + " wasn't found in the list of tasks.";
        }
        else{
            Tasks.remove(index);
            return "Successfully removed task: " + task;
        }
    }

    /**
     * Adds information to the userfile
     * @param User the user that has been input
     */
    public void addToUser(String User){
        List<String> tempLines = new ArrayList<>();
        String fileName = null;
        int i = 0;
        while(i < Name.length()) {
            if (Name.charAt(i) != ' ') {
                fileName += Name.charAt(i);
            }
            i++;
        }
        fileName = fileName.substring(4);
        try{
            fileScanner = new Scanner( new FileReader(System.getProperty("user.dir") + "\\src\\" + User + ".txt"));
            while(fileScanner.hasNext()) {
                tempLines.add(fileScanner.nextLine());
            }
        }
        catch( FileNotFoundException e){
            System.err.println("File does not exist: " + e.getMessage());
        }
        catch(InputMismatchException e){
            System.err.println("Error, unable to parse input!" );
        }
        catch(Exception e){
            System.err.println("ERROR: " + e.getMessage());
        }
        finally{
            if (fileScanner != null){
                fileScanner.close();
            }
        }

/**
 *
 *
 *
 *
 *
 **/


        try{
            out = new FileWriter(System.getProperty("user.dir") + "\\src\\" + User + ".txt");

            for(String line : tempLines){
                out.write(line + "\n");
            }
            out.write(fileName + "\n");
        }
        catch (IOException e) {
            System.err.println("ERROR: There was an IOException when writing to file: " + e.getMessage());
        }
        catch (Exception e) {
            System.err.println("ERROR: There was an Exception when writing to file: " + e.getMessage());
        }
        finally{
            closeWriter(out);
        }

    }

    /**
     * saves information to a file
     */
    public void saveToFile() {
        // takes care of all writing and saving to file
        String fileName = null;
        int i = 0;
        while(i < Name.length()){
            if (Name.charAt(i) != ' '){
                fileName += Name.charAt(i);
            }
            i++;
        }
        fileName = fileName.substring(4) + ".txt";
        System.out.println("Saving as: " + fileName);
        try {
            out = new FileWriter(System.getProperty("user.dir") + "\\projects\\" + fileName);
            System.out.println("Writing: " + Name);
            out.write(Name + "\n\n");


            for (String task : Tasks) {
                System.out.println("Writing: " + task);
                out.write(task + "\n");
            }
        }
        catch (IOException e) {
            System.err.println("ERROR: There was an IOException when writing to file: " + e.getMessage());
        }
        catch (Exception e) {
            System.err.println("ERROR: There was an Exception when writing to file: " + e.getMessage());
        }
        finally{
            System.out.println("SUCCESS!");
            closeWriter(out);
        }
    }

    /**
     * outputs a string back to the user
     * @return String containing the list of tasks
     */
    public String taskToString(){
        //returns a task
        String TaskString = "";
        for(String task : Tasks){
            if (!task.equals(Tasks.get(Tasks.size()-1))) {
                TaskString += task + ", ";
            }
            else{
                TaskString += task;
            }
        }
        return TaskString;
    }

    /**
     * returns project info
     * @param projectName name of project the user has input
     * @return project information
     */
    public static Project getProjects(String projectName){
        String fileName = null;
        boolean status = false;
        File fileDir = new File(System.getProperty("user.dir") + "\\projects");

        int i = 0;
        while(i < projectName.length()){
            if (projectName.charAt(i) != ' '){
                fileName += projectName.charAt(i);
            }
            i++;
        }
        fileName = fileName.substring(4) + ".txt";


        File[] fileList = fileDir.listFiles();


        if (fileList == null){
            System.err.println("There are no projects in this directory.");
        }
        else{
            for (int j = 0; j < fileList.length; j++){
                File testFile = fileList[j];
                if(fileName.equalsIgnoreCase(testFile.getName())){
                    status = true;
                    System.out.println("Project Found, Opening.");
                    break;
                }
            }
        }

        if (!status){
            return null;
        }

        List<String> tempArray = new ArrayList<>();
        String projectTitle = "";
        try{
            fileScanner = new Scanner (new FileReader (System.getProperty("user.dir") + "\\projects\\" + fileName));

            if(fileScanner.hasNext()){
                projectTitle = fileScanner.nextLine().strip();
                fileScanner.nextLine();
            }
            while(fileScanner.hasNext()){
                tempArray.add(fileScanner.nextLine());
            }
        }
        catch( FileNotFoundException e){
            System.err.println("That filename is not valid: " + e.getMessage());
            return null;
        }
        catch(InputMismatchException e){
            System.err.println("Error, unable to parse input!" );
            return null;
        }
        catch(Exception e){
            System.err.println("ERROR: " + e.getMessage());
            return null;
        }
        finally{
            if (fileScanner != null){
                fileScanner.close();
            }
            Project openedProject = new Project(projectTitle, tempArray);
            return openedProject;
        }

    }
}
