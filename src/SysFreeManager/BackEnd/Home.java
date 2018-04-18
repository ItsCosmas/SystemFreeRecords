/**
 * To query the DB for current Admin User and display along with a welcome text
 * To query the DB for number of Exclusive media stored
 * To query the DB for Journalist who grossed the highest pay and display the pay along
 * To query the DB for media item that grossed the highest pay and play the media in future release
 * To give a monthly summary of the media items
 *
 * */

package SysFreeManager.BackEnd;

import SysFreeManager.MySQLConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.scene.media.Media;

import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class Home implements Initializable {


    ResultSet resultSet;
    PreparedStatement preparedStatement;
    Connection myConn = null;;
    String SQL;

    @FXML
    Label lblUsername;




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
    private void goToAddUser(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/SysFreeManager/UserInterface/AddUser.fxml"));
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
        private void goToLogin(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/SysFreeManager/UserInterface/Login.fxml"));
        Parent home = loader.load();
        Scene home_scene = new Scene(home);
        Stage home_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        home_stage.setScene(home_scene);
        home_stage.show();

    }


    @FXML
    private MediaView homeMedia;

    private MediaPlayer mediaPlayer;


     //private static final String MEDIA_URL = "../Videos/OneRepublic - Kids.MP4";
     //private static final String MEDIA_URL = "../Videos/PLO.MP4";
     private static final String MEDIA_URL = "../Videos/Kisungu.MP4";
     //private static final String MEDIA_URL = "../Videos/Electric.MP4";
     //private static final String MEDIA_URL = "../Videos/Babu Owino.MP4";



    @Override
    public void initialize(URL Location, ResourceBundle resourceBundle) {
        // TODO Auto-generated method stub

        mediaPlayer = new MediaPlayer(new Media(this.getClass().getResource(MEDIA_URL).toExternalForm()));

        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(10);
        //mediaPlayer.getAudioEqualizer();

        homeMedia.setMediaPlayer(mediaPlayer);

    }

    public void getAdminDetails(){


        try {
            myConn = MySQLConnection.dbConnector();
            Statement myStatement = myConn.createStatement();

            String SQL = "SELECT firstName FROM systemfreedb.admins WHERE username = ? " ;


            ResultSet myResultSet = myStatement.executeQuery(SQL);

            //Process the result set

            while (myResultSet.next())

                lblUsername.setText(myResultSet.getString("firstName"));


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }





}
