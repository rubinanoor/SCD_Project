import View.Login;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args){
        Login login = new Login();
        Connection conn = DBConncection();
        login.createLoginPage(conn);


    }
    public static Connection DBConncection(){
        try{
            String driver = "com.mysql.cj.jdbc.Driver";
            String DBurl = "jdbc:mysql://localhost:3306/SMARTSCRIPT";
            String userName = "root";
            String password = "Rubina123abc.";
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(DBurl, userName, password);
            return conn;

        }
        catch(Exception  se){

        }
        return null;
    }
}
