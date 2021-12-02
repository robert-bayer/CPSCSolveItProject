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
