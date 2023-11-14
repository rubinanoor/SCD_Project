package BusinessLayer;
import dao.*;
public class User {
  UserDao userdao;
  private int userid;
  private String username;
  private char[] password;
  
  public User(){
    userdao = new UserDao();
  }
  public int getUserid() {
    return userid;
  }

  public void setUserid(int userid) {
    this.userid = userid;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public char[] getPassword() {
    return password;
  }

  public void setPassword(char[] password) {
    this.password = password;
  }
  
  public boolean login(String username, char[] password){
     if(userdao.login(username, password)){
         this.username = username;
         this.password = password;
         return true;
     }
     return false;
  }
  
  public boolean logout(){
    return false;
  }
  
  public boolean signup(String username, char[] password){
    if(userdao.signup(username, password)){
         this.username = username;
         this.password = password;
         return true;
     }
     return false;
  }
  
  public boolean updateUsername(String newusername){
    return false;
  }
  
  public boolean updatePassword(String newpassword){
    return false;
  }
  
  public boolean uniqueUsername(String name){
    return false;
  }
}
