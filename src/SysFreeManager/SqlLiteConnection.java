// Not Using SQL LITE
package SysFreeManager;

import java.sql.Connection;
import java.sql.DriverManager;


public class SqlLiteConnection{
    public static Connection dbConnector() {
        try {
            Class.forName("org.sqlite.JDBC");
//			Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:D:\\Projects\\SystemFreeRecords\\src\\SysFreeManager\\Admins.db");
//			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/employeedata","admin","mdantsane");
            conn.setAutoCommit(true);
            return conn;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

}
