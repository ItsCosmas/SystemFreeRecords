package SysFreeManager;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnection {

    public static Connection dbConnector(){

        String dburl = "jdbc:mysql://localhost:3306/systemfreedb" ;
        String username = "root" ;
        String password =  "punkh1gh*Er" ;

        try {

            //Get a Connection to the database
            
            Connection myConn = DriverManager.getConnection( dburl, username , password);


          myConn.setAutoCommit(true);
          return myConn;

        } catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
