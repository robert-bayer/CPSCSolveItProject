import java.io.*;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;


//Project has tasks, deadline,

//When a project is open store all progress, tasks, and info in ArrayList.  Upon the closing of project, save the Array list in a file.
//To open project read the file and have each line a different task in ArrayList

public class Project {
    FileWriter out = null;
    private String Name;
    List<String> Tasks = new ArrayList<>();
    List<String> TaskStatus = null;
    private String Deadline;
    Scanner fileScanner = null;

    public Project() {}
    public Project(String Name){
        this.Name = Name;
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
        for (String term : Tasks) {
            if (term.equals(task)) {
                Tasks.remove(term);
                status = true;
            }
        }
        if (status == false){
            return "I'm sorry, " + task + " wasn't found in the list of tasks.";
        }
        else{
            return "Successfully removed task: " + task;
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
        try {
            out = new FileWriter(fileName);
            out.write(Name + "\n\n");

            for (String task : Tasks) {
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

    public Project openProject(String projectName){
        String fileName = null;
        boolean status = false;
        File fileDir = new File(System.getProperty("user.dir"));

        int i = 0;
        while(i < projectName.length()){
            if (projectName.charAt(i) != ' '){
                fileName += projectName.charAt(i);
            }
            i++;
        }
        fileName = fileName.substring(4) + ".txt";


        String[] fileList = fileDir.list();

        if (fileList == null){
            System.err.println("There are no projects in this directory.");
        }
        else{
            for (int j = 0; i < fileList.length; i++){
                String testFile = fileList[j];
                if(fileName.equalsIgnoreCase(testFile)){
                    status = true;
                    System.out.println("Project Found, Opening.");
                    break;
                }
            }
        }

        if (!status){
            return null;
        }

        try{
            fileScanner = new Scanner (new FileReader (fileName));
            List<String> tempArray = new ArrayList<>();
            if(fileScanner.hasNext()){
                String projectTitle = fileScanner.nextLine();
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
