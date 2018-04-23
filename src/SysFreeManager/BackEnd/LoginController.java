package SysFreeManager.BackEnd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable  {

    @FXML
    private Hyperlink btnRegister;

    private SceneSwitches sceneSwitches = new SceneSwitches();


    private LoginModel loginModel = new LoginModel();

    @FXML
    private Label lblLoginWarning, lblIsConnected;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;


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

        btnRegister.setOnAction(event -> {
            try {
                sceneSwitches.goToRegisterAdmin(event);
            }catch (Exception e){
                System.out.println("An Error Occurred");
            }
        });

    }





   private String passwordHash() {
        String salt = txtUsername.getText();
        String passwordToBeHashed = txtPassword.getText()+salt;
        String generatedPassword;
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

            //
            return generatedPassword;

        }catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }



    @FXML
    public void Login (ActionEvent event) throws SQLException {

        try {

            if (loginModel.isLogin(txtUsername.getText(),passwordHash())){

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/SysFreeManager/UserInterface/Home.fxml"));
                
                Parent home = loader.load();
                Scene home_scene = new Scene(home);
                Stage home_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                home_stage.setScene(home_scene);
                home_stage.show();

                //Passes the username to home
                Home homepage = loader.getController();
                homepage.setLoggedInUserDetails(txtUsername.getText());

                // Print Welcome Message
                homepage.getAdminDetails();


            }else {
                lblLoginWarning.setText("\uD83D\uDE0F Username or Password is Incorrect");


            }
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
