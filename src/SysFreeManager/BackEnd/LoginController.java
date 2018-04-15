package SysFreeManager.BackEnd;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


import SysFreeManager.MySQLConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class LoginController implements Initializable {
    public LoginModel loginModel = new LoginModel();

    ResultSet resultSet;
    PreparedStatement preparedStatement;
    Connection myConn = null;
    String SQL;
    @FXML
    private Label lblLoginWarning;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label lblIsConnected;



    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        if (loginModel.isDBConnected()){
            lblIsConnected.setText(" \uD83D\uDE07 You're Connected");
            lblIsConnected.setStyle("-fx-text-fill: green");
        }else {
            lblIsConnected.setText(" \uD83D\uDE1E Hmmm,You're Not Connected");
            lblIsConnected.setStyle("-fx-text-fill: red");
        }
    }




   public String passwordHash() {
        String salt = txtUsername.getText();
        String passwordToBeHashed = txtPassword.getText()+salt;
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // Add password bytes to digest
            md.update(passwordToBeHashed.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i=0; i<bytes.length; i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff)+ 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
            return generatedPassword;

        }catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(generatedPassword);
        return generatedPassword;
    }



    @FXML
    public void Login (ActionEvent event) throws SQLException {

        try {
            //		if (loginModel.isLogin(txtUsername.getText(), txtPassword.getText())){
            if (loginModel.isLogin(txtUsername.getText(),passwordHash())){
                //lblLoginWarning.setText("Correct Login Credentials");
                //lblLoginWarning.setStyle("-fx-text-fill: green");

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/SysFreeManager/UserInterface/Home.fxml"));
                Parent home = loader.load();
                Scene home_scene = new Scene(home);
                Stage home_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                home_stage.setScene(home_scene);
                home_stage.show();


            }else {
                lblLoginWarning.setText("\uD83D\uDE0F Username or Password is Incorrect");
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
    }



            @FXML
            private void goToRegisterAdmin (ActionEvent event) throws Exception {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/SysFreeManager/UserInterface/RegisterAdmin.fxml"));
                Parent home = loader.load();
                Scene home_scene = new Scene(home);
                Stage home_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                home_stage.setScene(home_scene);
                home_stage.show();

            }

        }
