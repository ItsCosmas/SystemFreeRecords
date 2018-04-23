package SysFreeManager.BackEnd;

/*
  Check that file field is not empty
  Add stylesheets to DialogPanes
  */

import SysFreeManager.MySQLConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

//import java.io.InputStream;

public class RegisterAdmin implements Initializable {

        private LoginModel loginModel = new LoginModel();

        private SceneSwitches sceneSwitches = new SceneSwitches();

        private File file;

        @FXML
        private Label lblIsConnected;

        @FXML Button btnLogin;

        @FXML
        private TextField txtFirstName,txtSecondName,txtUsername;


        @FXML
        private PasswordField txtPassword,txtConfirmPassword;


        @FXML
        private ImageView profilePicture;

        @FXML
        private Stage stage; // This should not be removed even if marked as unused


    @Override
        public void initialize(URL Location, ResourceBundle resourceBundle) {
            // TODO Auto-generated method stub
            //Inverting if statement hides the code duplicate error
            if (!loginModel.isDBConnected()) {
                lblIsConnected.setText(" \uD83D\uDE1E Hmmm,You're Not Connected");
                lblIsConnected.setStyle("-fx-text-fill: red");
            } else {
                lblIsConnected.setText(" \uD83D\uDE07 You're Connected");
                lblIsConnected.setStyle("-fx-text-fill: green");
            }

            btnLogin.setOnAction(event -> {
                try {
                    sceneSwitches.goToLogin(event);
                }catch (Exception e){
                    System.out.println("An Error Occurred");
                }
            });

        }


        private String passwordHash() {

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
                
                //Replaced for loop with foreach
                for (byte aByte : bytes) {
                    sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
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

            if(firstName.trim().isEmpty() || secondName.trim().isEmpty() || username.trim().isEmpty() || password.trim().isEmpty() || confirmPassword.trim().isEmpty()  ){



                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText(" \uD83D\uDE1F Please Fill in All Fields!");
                //	alert.setResizable(true);
                alert.getDialogPane().setPrefSize(280, 100);

                // Get the Stage.
                //Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

                // Add a custom icon.
                //stage.getIcons().add(new Image(this.getClass().getResource("/images/system_login.png").toString()));

                DialogPane dialogPane = alert.getDialogPane();
                //dialogPane.getStylesheets().add(getClass().getResource("myDialogs.css").toExternalForm());
                alert.showAndWait();

            } else {

                if(password.equals(confirmPassword)){
                    //				if(txtPassword.getText().equals(txtConfirmPassword.getText())){


                    try{

                        FileInputStream fileInputStream = new FileInputStream(file);




                        String finalPassword = passwordHash();

                        Connection myConn = MySQLConnection.dbConnector();

                        String setMaxBlobSize = "set global max_allowed_packet = 1024 * 1024 *14 ";
                        PreparedStatement preparedStatement = myConn.prepareStatement(setMaxBlobSize);
                        preparedStatement.execute();
                        //System.out.println("Max File Size Set");

                        String SQL = "INSERT INTO systemfreedb.admins SET adminPic = ?,firstName = ? ,secondName = ?,username = ? ,password = ?";


                        preparedStatement = myConn.prepareStatement(SQL);


                        //more options
                        preparedStatement.setBlob(1,fileInputStream , file.length() );
                        preparedStatement.setString(2,txtFirstName.getText());
                        preparedStatement.setString(3,txtSecondName.getText());
                        preparedStatement.setString(4,txtUsername.getText());
                        preparedStatement.setString(5,finalPassword);


                        preparedStatement.execute();

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information");
                        alert.setHeaderText(null);
                        alert.setContentText("\uD83D\uDE03 Registration Successful");
                        alert.getDialogPane().setPrefSize(250, 100);
                        // Get the Stage.
                        //Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

                        // Add a custom icon.
                        //stage.getIcons().add(new Image(this.getClass().getResource("/images/system_login.png").toString()));

                        DialogPane dialogPane = alert.getDialogPane();
                        //dialogPane.getStylesheets().add(getClass().getResource("myDialogs.css").toExternalForm());
                        alert.showAndWait();



                        preparedStatement.close();


                    } catch (Exception e) {


                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Warning");
                        alert.setHeaderText(null);
                        alert.setContentText("\uD83D\uDE1F Username is already registered");
                        //	alert.setResizable(true);
                        alert.getDialogPane().setPrefSize(280, 100);

                        // Get the Stage.
                        //Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

                        // Add a custom icon.
                        //stage.getIcons().add(new Image(this.getClass().getResource("/images/system_login.png").toString()));

                        DialogPane dialogPane = alert.getDialogPane();
                        //dialogPane.getStylesheets().add(getClass().getResource("myDialogs.css").toExternalForm());
                        alert.showAndWait();



                    }

                } else {


                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("\uD83D\uDE15 Passwords Don't match, Please Re-enter passwords");
                    //	alert.setResizable(true);
                    alert.getDialogPane().setPrefSize(280, 100);

                    // Get the Stage.
                    //Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

                    // Add a custom icon.
                    //stage.getIcons().add(new Image(this.getClass().getResource("/images/system_login.png").toString()));

                    DialogPane dialogPane = alert.getDialogPane();
                    //dialogPane.getStylesheets().add(getClass().getResource("myDialogs.css").toExternalForm());
                    alert.showAndWait();

                }
            }
        }




    @FXML
        public void openFile(){
            //System.out.println("Opening file");
            FileChooser fileChooser = new FileChooser();

            fileChooser.setTitle("Choose a Profile Picture");
            fileChooser.getExtensionFilters().addAll(
                    // To filter only specific image formats
                    new FileChooser.ExtensionFilter("JPG Files","*.JPG"),
                    new FileChooser.ExtensionFilter("PNG Files","*.PNG")
            );



            file = fileChooser.showOpenDialog(stage);

            if (file != null){
                //System.out.println("Chosen File: " + file);
                Image image = new Image(file.toURI().toString());

                //profilePicture = new ImageView(image);
                profilePicture.setImage(image);


            }

        }

    }