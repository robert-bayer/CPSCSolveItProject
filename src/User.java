import java.io.FileWriter;
import java.io.IOException;
/**
 * @author Andrew Gonye
 * @author Robert Bayer
 * @author Clayton Everitt
 */
/**
 * User sets a username and password to a user object as well as returns it.
 */
public class User {

    String Password;
    String Username;
    public User(String Username, String Password){
        setUsername(Username);
        setPassword(Password);
    }
    public User(){}

    /**
     * sets a username
     * @param User username that was input
     */
    public void setUsername(String User){
        //setter for username
        Username = User;
    }

    /**
     * sets a password to a user
     * @param Pass password that was input
     */
    public void setPassword(String Pass){
        //setter for password
        Password = Pass;
    }

    /**
     * returns the password of the user
     * @return password string
     */
    public String getPassword(){
        //getter for password
        return Password;
    }

    /**
     * returns the username of the User
     * @return username string
     */
    public String getUsername(){
        //getter for username
        return Username;
    }

}
