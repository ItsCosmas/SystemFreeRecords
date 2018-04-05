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

            //Uncommment this Section to add new user data
            //String sqlInsert = "INSERT INTO systemfreedb.employee(employeeID,employeeFirstName,employeeLastName,employeeSecondName, employeeEmail , employeePhone ,employeeIdNumber) " +
             //       " VALUES( 3 , 'Cozy' , 'DEV' ,'the' , 'cosmastheDEV@mail.co' , '+254704328791' ,341806954);";
            //ResultSet myResultSet = myStatement.executeQuery("SELECT * FROM systemfreedb.employee");

           // myStatement.executeUpdate(sqlInsert);
            //System.out.println("Insert Successful");

            ResultSet myResultSet = myStatement.executeQuery("SELECT * FROM  systemfreedb.employee");

            //Process the result set

            while (myResultSet.next()){
             System.out.println(" Employee ID:" + myResultSet.getString("employeeID") + " \n Full names: " + myResultSet.getString("employeeFirstName") + " "
            + myResultSet.getString("employeeSecondName") + " " + myResultSet.getString("employeeLastName") + ""
                        + " \n Email: " + myResultSet.getString("employeeEmail")
                        + " \n Phone: " + myResultSet.getString("employeePhone")
                        + " \n Id number: " + myResultSet.getString("employeeIdNumber")
                );
            }

        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
