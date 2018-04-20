package SysFreeManager.BackEnd;

import SysFreeManager.MySQLConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RegisterAdmin implements Initializable {

        public LoginModel loginModel = new LoginModel();


        File file;

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

        @FXML
        private ImageView profilePicture;

        @FXML
        private Stage stage;

        private Image image;



        ResultSet resultSet;
        PreparedStatement preparedStatement;
        Connection myConn = null;;
        String SQL;


        @Override
        public void initialize(URL Location, ResourceBundle resourceBundle) {
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
        public void Register(){

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

                        FileInputStream fileInputStream = new FileInputStream(file);




                        String finalPassword = passwordHash();

                        myConn = MySQLConnection.dbConnector(); // this is the database connection
                        String SQL = "INSERT INTO systemfreedb.admins SET adminPic = ?,firstName = ? ,secondName = ?,username = ? ,password = ?" ;


                        PreparedStatement preparedStatement = myConn.prepareStatement(SQL);


                        //more options
                        preparedStatement.setBlob(1,(InputStream)fileInputStream , (long) file.length() );
                        preparedStatement.setString(2,txtFirstName.getText());
                        preparedStatement.setString(3,txtSecondName.getText());
                        preparedStatement.setString(4,txtUsername.getText());
                        preparedStatement.setString(5,finalPassword);


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




        //public void init(Stage stage){
          //  this.stage = stage;
        //}


    @FXML
        public void openFile(){
            //System.out.println("Opening file");
            FileChooser fileChooser = new FileChooser();

            fileChooser.setTitle("Choose a Profile Picture");
            fileChooser.getExtensionFilters().addAll(
                    // To filter only specific image formats
                    new FileChooser.ExtensionFilter("JPG Files","*.*"),
                    new FileChooser.ExtensionFilter("PNG Files","*.*")
            );



            file = fileChooser.showOpenDialog(stage);

            if (file != null){
                //System.out.println("Chosen File: " + file);
                image = new Image(file.toURI().toString());

                //profilePicture = new ImageView(image);
                profilePicture.setImage(image);


            }

        }

    }