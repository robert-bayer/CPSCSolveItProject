import java.io.FileWriter;
import java.io.IOException;

/**
 * Basic User Object
 * @author Andrew Gonye
 * @version 0.1
 *
 */

public class User {
    String Password;
    String Username;
    public User(String Username, String Password){
        setUsername(Username);
        setPassword(Password);
    }
    public User(){}
    public void setUsername(String User){
        Username = User;
    }
    public void setPassword(String Pass){
        Password = Pass;
    }
    public String getPassword(){
        return Password;
    }
    public String getUsername(){
        return Username;
    }



}
