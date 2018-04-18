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
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.beans.binding.Bindings;


import javax.swing.text.html.ImageView;
import java.io.InputStream;
import java.io.File;
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
        mediaPlayer.pause();
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
        mediaPlayer.pause();

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
        mediaPlayer.pause();

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
        mediaPlayer.pause();

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
        // Stops media on log out
        mediaPlayer.stop();

    }


    @FXML
    private MediaView homeMedia;

    private MediaPlayer mediaPlayer;

    private Media media;


      //String MEDIA_URL = new File("src/SysFreeManager/Videos/OneRepublic - Kids.MP4").getAbsolutePath();
      //String MEDIA_URL = new File("src/SysFreeManager/Videos/Los Lobos - La Bamba (HQ,16-9) - YouTube.MP4").getAbsolutePath();
      //String MEDIA_URL = new File("src/SysFreeManager/Videos/---OneRepublic - All The Right Moves.MKV").getAbsolutePath();
      //String MEDIA_URL = new File("src/SysFreeManager/Videos/PLO.MP4").getAbsolutePath();
      String MEDIA_URL = new File("src/SysFreeManager/Videos/Kisungu.mp4").getAbsolutePath();
      //String MEDIA_URL = new File("src/SysFreeManager/Videos/Babu Owino.MP4").getAbsolutePath();
      //String MEDIA_URL = new File("src/SysFreeManager/Videos/Electric.MP4").getAbsolutePath();




    @Override
    public void initialize(URL Location, ResourceBundle resources) {
        // TODO Auto-generated method stub

        media = new Media(new File(MEDIA_URL).toURI().toString());
        mediaPlayer = new MediaPlayer(media);

        //mediaPlayer = new MediaPlayer(new Media(this.getClass().getResource(MEDIA_URL).toExternalForm()));

        //mediaPlayer.setAutoPlay(true);
        //mediaPlayer.setCycleCount(10);
        //mediaPlayer.getAudioEqualizer();

        homeMedia.setMediaPlayer(mediaPlayer);

//        Resize Video

        //DoubleProperty width = homeMedia.fitWidthProperty();
        //DoubleProperty height = homeMedia.fitHeightProperty();

        //width.bind(Bindings.selectDouble(homeMedia.sceneProperty(), "width"));
        //height.bind(Bindings.selectDouble(homeMedia.sceneProperty(), "height"));

    }



    @FXML
    private void play(){
        mediaPlayer.play();
    }

    @FXML
    private void pause(){
        mediaPlayer.pause();
    }
    @FXML
    private void stop(){
        mediaPlayer.stop();
    }
    @FXML
    private void restart(){
        mediaPlayer.seek(mediaPlayer.getStartTime());
        mediaPlayer.play();
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
