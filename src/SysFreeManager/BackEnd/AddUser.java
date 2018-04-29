package SysFreeManager.BackEnd;

/*
* Add Alerts and Dialogs for user interactivity
* Check whether file field is empty
* */

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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

//import java.io.InputStream;


public class AddUser implements Initializable {

    private SceneSwitches sceneSwitches = new SceneSwitches();


    private File file;



    @FXML
    private TextField txtFirstName,txtSecondName,txtLastName,txtIdNumber,txtEmail,txtPhone;




    @FXML
    private ImageView profilePicture;

    @FXML
    private Button btnUserManagement,btnAddMedia,btnDashboard,btnGoHome;

    private Stage stage; // Just can't remove this, Used in Show file dialog


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // TODO Auto-generated method stub



        btnUserManagement.setOnAction(event -> {
            try {
                sceneSwitches.goToUserManagement(event);
            }catch (Exception e){
                System.out.println("An Error Occurred");
            }
        });


        btnAddMedia.setOnAction(event -> {
            try {
                sceneSwitches.goToAddMedia(event);
            }catch (Exception e){
                System.out.println("An Error Occurred");
            }
        });

        btnDashboard.setOnAction(event -> {
            try {
                sceneSwitches.goToDashboard(event);
            }catch (Exception e){
                System.out.println("An Error Occurred");
            }
        });

        btnGoHome.setOnAction(event -> {
            try {
                sceneSwitches.goToHome(event);
                Home home = new Home();
                home.getAdminDetails();

            }catch (Exception e){
                System.out.println("An Error Occurred");
            }
        });

    }


    @FXML
    public void openFile(){
        //System.out.println("Opening file");
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Choose a Profile Picture");
        fileChooser.getExtensionFilters().addAll(
                // To filter only specific image formats
                // To add more image formats
                new FileChooser.ExtensionFilter("Image Files","*.jpg","*.png"),
                new FileChooser.ExtensionFilter("All Files","*.*")
        );




        file = fileChooser.showOpenDialog(stage);

        if (file != null){

            // To Display selected image on ImageView
            Image image = new Image(file.toURI().toString());


            profilePicture.setImage(image);


        }

    }

    @FXML
    private void registerUser(){

        String  employeeFirstName = txtFirstName.getText();
        String employeeSecondName = txtSecondName.getText();
        String employeeLastName = txtLastName.getText();
        String employeeIdNumber = txtIdNumber.getText();
        String employeeEmail  = txtEmail.getText();
        String employeePhone = txtPhone.getText();

        if(employeeFirstName.trim().isEmpty() || employeeSecondName.trim().isEmpty() || employeeLastName.trim().isEmpty() || employeeEmail.trim().isEmpty() || employeePhone.trim().isEmpty() || employeeIdNumber.trim().isEmpty() || file == null ){


            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("\uD83D\uDE0F Please Fill In All Mandatory Fields");
            //	alert.setResizable(true);
            alert.getDialogPane().setPrefSize(300, 150);

            // Get the Stage.
            //Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

            // Add a custom icon.
            //stage.getIcons().add(new Image(this.getClass().getResource("/images/system_login.png").toString()));

            DialogPane dialogPane = alert.getDialogPane();
            //dialogPane.getStylesheets().add(getClass().getResource("myDialogs.css").toExternalForm());
            alert.showAndWait();


        } else {



                try{

                    FileInputStream fileInputStream = new FileInputStream(file);


                    Connection myConn = MySQLConnection.dbConnector();

                    String setMaxBlobSize = "set global max_allowed_packet = 1024 * 1024 *14 ";
                    PreparedStatement preparedStatement = myConn.prepareStatement(setMaxBlobSize);
                    preparedStatement.execute();
                    //System.out.println("Max File Size Set");


                    String SQL = "INSERT INTO systemfreedb.employee SET employeePhoto = ?,employeeFirstName = ? ,employeeSecondName = ?,employeeLastName = ? ,employeeIdNumber = ?, employeeEmail = ?,employeePhone = ?" ;


                    preparedStatement = myConn.prepareStatement(SQL); // Exception handled


                    //more options
                     //preparedStatement.setBinaryStream(1,(InputStream)fileInputStream , (long) file.length() );
                    preparedStatement.setBlob(1, fileInputStream , file.length() );

                    preparedStatement.setString(2,employeeFirstName);
                    preparedStatement.setString(3,employeeSecondName);
                    preparedStatement.setString(4,employeeLastName);
                    preparedStatement.setString(5,employeeIdNumber);
                    preparedStatement.setString(6,employeeEmail);
                    preparedStatement.setString(7,employeePhone);


                    preparedStatement.execute();

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText(null);
                    alert.setContentText("\uD83D\uDE0E New User has been Added Successfully");
                    alert.getDialogPane().setPrefSize(300, 150);
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
                    alert.setContentText("\uD83D\uDE1E An Error Occurred, Please try again, or User already exists, check email, id number or phone");
                    //	alert.setResizable(true);
                    alert.getDialogPane().setPrefSize(300, 150);

                    // Get the Stage.
                    //Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

                    // Add a custom icon.
                    //stage.getIcons().add(new Image(this.getClass().getResource("/images/system_login.png").toString()));

                    DialogPane dialogPane = alert.getDialogPane();
                    //dialogPane.getStylesheets().add(getClass().getResource("myDialogs.css").toExternalForm());
                    alert.showAndWait();

                    //e.printStackTrace();



                }

        }
    }


}
