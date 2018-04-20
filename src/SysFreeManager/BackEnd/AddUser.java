package SysFreeManager.BackEnd;

import SysFreeManager.MySQLConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
//import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class AddUser {


    private File file;



    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtSecondName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtIdNumber;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPhone;


    @FXML
    private Label lblAddError;

    @FXML
    private Label lblAddSuccess;

    @FXML
    private ImageView profilePicture;

    @FXML
    private Stage stage; // Just can't remove this

    private Image image;



    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private Connection myConn = null;
    private String SQL;

    @FXML

    private void goToUserManagement (ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/SysFreeManager/UserInterface/UserManagement.fxml"));
        Parent home = loader.load();
        Scene home_scene = new Scene(home);
        Stage home_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        home_stage.setScene(home_scene);
        home_stage.show();
    }


    @FXML

    private void goToAddMedia(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/SysFreeManager/UserInterface/AddMedia.fxml"));
        Parent home = loader.load();
        Scene home_scene = new Scene(home);
        Stage home_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        home_stage.setScene(home_scene);
        home_stage.show();

    }


    @FXML

    private void goToDashboard(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/SysFreeManager/UserInterface/Dashboard.fxml"));
        Parent home = loader.load();
        Scene home_scene = new Scene(home);
        Stage home_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        home_stage.setScene(home_scene);
        home_stage.show();

    }

    @FXML

    private void goToHome(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/SysFreeManager/UserInterface/Home.fxml"));
        Parent home = loader.load();
        Scene home_scene = new Scene(home);
        Stage home_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        home_stage.setScene(home_scene);
        home_stage.show();


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
            image = new Image(file.toURI().toString());


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

        if(employeeFirstName.trim().isEmpty() || employeeSecondName.trim().isEmpty() || employeeLastName.trim().isEmpty() || employeeEmail.trim().isEmpty() || employeePhone.trim().isEmpty() || employeeIdNumber.trim().isEmpty() ){


            lblAddSuccess.setText(null);
            lblAddError.setText("\uD83D\uDE0F Please Fill In All Mandatory Fields");

        } else {



                try{

                    FileInputStream fileInputStream = new FileInputStream(file);



                    myConn = MySQLConnection.dbConnector(); // this is the database connection
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

                    lblAddError.setText(null);
                    lblAddSuccess.setText("\uD83D\uDE0E New User has been Added Successfully");


                    preparedStatement.close();


                } catch (Exception e) {


                    //e.printStackTrace();
                    lblAddSuccess.setText(null);
                    lblAddError.setText("\uD83D\uDE1E An Error Occurred");



                }

        }
    }



}
