import java.io.*;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;


//Project has tasks, deadline,

//When a project is open store all progress, tasks, and info in ArrayList.  Upon the closing of project, save the Array list in a file.
//To open project read the file and have each line a different task in ArrayList

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

    public void setDeadline(String deadline){
        this.Deadline = deadline;
    }

    public String getDeadline(){
        return Deadline;
    }

    public void setName(String Name){
        this.Name = Name;
    }

    public String getName(){
        return Name;
    }

    public void addTask(String task){
        Tasks.add(task);
        TaskStatus.add("Not Started");
    }


    public List<String> displayTasks(){
        return Tasks;
    }

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
            System.err.println("That filename is not valid: " + e.getMessage());
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

    public void saveToFile() {
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

    public String taskToString(){
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

    public static Project getProjects(String projectName){
        String fileName = null;
        boolean status = false;
        File fileDir = new File(System.getProperty("user.dir") + "\\projects");
        //System.out.println(fileDir);

        //System.out.println(fileDir.toString());

        int i = 0;
        while(i < projectName.length()){
            if (projectName.charAt(i) != ' '){
                fileName += projectName.charAt(i);
            }
            i++;
        }
        fileName = fileName.substring(4) + ".txt";
        //System.out.println(fileName);


        File[] fileList = fileDir.listFiles();
        //System.out.println(fileList);

        if (fileList == null){
            System.err.println("There are no projects in this directory.");
        }
        else{
            for (int j = 0; j < fileList.length; j++){
                File testFile = fileList[j];
                if(fileName.equalsIgnoreCase(testFile.getName())){
                    status = true;
                    System.out.println("Project Found, Opening.");
                    //System.out.println(testFile.getName());
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
                //System.out.println(projectTitle);
                fileScanner.nextLine();
            }
            while(fileScanner.hasNext()){
                tempArray.add(fileScanner.nextLine());
            }
            //System.out.println(tempArray);
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
            //System.out.println(projectTitle + tempArray);
            Project openedProject = new Project(projectTitle, tempArray);
            return openedProject;
        }

    }

}
