package SysFreeManager.BackEnd;

import SysFreeManager.MySQLConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterAdmin implements Initializable {

    @FXML
    private void goToLogin(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/SysFreeManager/UserInterface/Login.fxml"));
        Parent home = loader.load();
        Scene home_scene = new Scene(home);
        Stage home_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        home_stage.setScene(home_scene);
        home_stage.show();

    }

        public LoginModel loginModel = new LoginModel();


        @FXML
        private Label lblIsConnected;

        @FXML
        private TextField txtFirstName;

        @FXML
        private TextField txtSecondName;

        @FXML
        private TextField txtUsername;

        @FXML
        private PasswordField txtPassword;

        @FXML
        private PasswordField txtConfirmPassword;


        @FXML
        private Label lblRegError;

        @FXML
        private Label lblRegSuccess;


        ResultSet resultSet;
        PreparedStatement preparedStatement;
        Connection myConn = null;;
        String SQL;


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
            return generatedPassword;

        }





        @FXML
        public void Register(ActionEvent event){

            String firstName = txtFirstName.getText();
            String secondName = txtSecondName.getText();
            String username = txtUsername.getText();
            String password = txtPassword.getText();
            String confirmPassword = txtConfirmPassword.getText();

            if(firstName.trim().isEmpty() || secondName.trim().isEmpty() || username.trim().isEmpty() || password.trim().isEmpty() || confirmPassword.trim().isEmpty() ){

                lblRegSuccess.setText(null);
                lblRegError.setText(" \uD83D\uDE1F Please Fill in All Fields");

            } else {

                if(password.equals(confirmPassword)){
                    //				if(txtPassword.getText().equals(txtConfirmPassword.getText())){


                    try{

                        String finalpassword = passwordHash();

                        myConn = MySQLConnection.dbConnector(); // this is the database connection
                        String SQL = "INSERT INTO systemfreedb.admins (firstName,secondName,username,password) values ('"+txtFirstName.getText()+"' , '"+txtSecondName.getText()+"' , '"+txtUsername.getText()+"' , '"+finalpassword+"')" ;

                        PreparedStatement preparedStatement = myConn.prepareStatement(SQL);

                        preparedStatement.execute();

                        lblRegSuccess.setText("\uD83D\uDE03 Registration Successful");


                        preparedStatement.close();


                    } catch (Exception e) {

                        lblRegSuccess.setText(null);
                        lblRegError.setText("\uD83D\uDE1F Username is already registered");



                    }

                } else {

                    lblRegSuccess.setText(null);
                    lblRegError.setText("\uD83D\uDE15 Passwords Don't match");

                }
            }
        }
    }