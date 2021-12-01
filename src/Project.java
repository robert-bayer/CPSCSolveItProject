import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.Writer;
import java.io.IOException;


//Project has tasks, deadline,

//When a project is open store all progress, tasks, and info in ArrayList.  Upon the closing of project, save the Array list in a file.
//To open project read the file and have each line a different task in ArrayList

public class Project {
    FileWriter out = null;
    private String Name;
    List<String> Tasks = new ArrayList<>();
    List<String> TaskStatus = new ArrayList<>();
    private String Deadline;

    public Project(String Name){
        this.Name = Name;
    }

    public void closeWriter(Writer file){
        try{
            if(file != null){
                file.close();
            }
        }
        catch(IOException e){
            System.err.println("ERROR: There was an IOExeption when trying to close the file: " + e.getMessage());
        }
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

    public void removeTask(String task){
        Tasks.remove(task);
    }

    public void saveToFile() {
        try {
            out = new FileWriter(Name.toUpperCase());
            out.write(Name.toUpperCase() + "\n\n");

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
}
