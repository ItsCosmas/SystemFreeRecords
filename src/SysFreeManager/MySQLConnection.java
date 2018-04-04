package SysFreeManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQLConnection {

    public static void main(String[] args) {

        try {

            //Get a Connection to the database

            Connection myConn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/systemfreedb" ,"root", "punkh1gh*Er");

            //Create a statement

            Statement myStatement = myConn.createStatement();

            //Execute SQL Query

            ResultSet myResultSet = myStatement.executeQuery("SELECT * FROM systemfreedb.admins");

            //Process the result set

            while (myResultSet.next()){
                System.out.println(myResultSet.getString("secondName") + " " + myResultSet.getString("firstName"));
            }

        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
