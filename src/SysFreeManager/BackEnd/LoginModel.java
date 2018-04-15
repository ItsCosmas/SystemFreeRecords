package SysFreeManager.BackEnd;

import java.sql.*;

import SysFreeManager.MySQLConnection;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginModel {
    Connection connection;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    public LoginModel (){
        connection = MySQLConnection.dbConnector();
        if (connection == null) System.exit(1);
    }

    public boolean isDBConnected() {
        try {
            return !connection.isClosed();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }




    public boolean isLogin(String username, String password) throws SQLException{
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "select * from systemfreedb.admins where username = ? and password = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
            // TODO: handle exception
        } finally {
            preparedStatement.close();
            resultSet.close();
        }
    }

}


