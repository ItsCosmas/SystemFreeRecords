/*
  To query the DB for current Admin User and display along with a welcome text
  To query the DB for number of Exclusive media stored
  To query the DB for Journalist who grossed the highest pay and display the pay along
  To query the DB for media item that grossed the highest pay and play the media in future release
  To give a monthly summary of the media items

  */

/*
 * BUG ALERT
 * Leaving the home scene and returning back doesn't set the Username and Image again */


package SysFreeManager.BackEnd;

import SysFreeManager.MySQLConnection;
//import javafx.beans.InvalidationListener;
//import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class Home implements Initializable {

    private SceneSwitches sceneSwitches = new SceneSwitches();


    private String theUsername;

    @FXML
    private Label lblUsername,lblMediaTitle;

    @FXML
    ImageView adminPicHome;

    @FXML
    private Slider homeVolumeSlider;




    @FXML
    private Button btnUserManagement,btnAddUser,btnAddMedia,btnDashboard,btnLogout;


    @FXML
    private MediaView homeMedia;

    private MediaPlayer mediaPlayer;


      private String MEDIA_URL = new File("D:\\Music Videos\\Rock\\M83 'Midnight City' Official video - YouTube.MP4").getAbsolutePath();
      //private String MEDIA_URL = new File("D:\\Tutorials\\Stuff\\What Can You Do With Python – Computer Programming (Guide) - YouTube.mp4").getAbsolutePath();
      //private String MEDIA_URL = new File("D:\\Music Videos\\Pop\\The+Lion+King+-+Hakuna+Matata+(HD).mp4").getAbsolutePath();
      //private String MEDIA_URL = new File("D:\\Videos\\(20) 4K Ultra HD Video - Awesome Captain's View - YouTube.mkv").getAbsolutePath();
      //private String MEDIA_URL = new File("src/SysFreeManager/Videos/OneRepublic - Kids.MP4").getAbsolutePath();
      //private String MEDIA_URL = new File("D:\\Videos\\Silicon Valley Season 5 Official Teaser (2018) - HBO.MP4").getAbsolutePath();
      //private String MEDIA_URL = new File("C:\\Users\\user\\Videos\\eminemagain.MP4").getAbsolutePath();
      //String MEDIA_URL = new File("src/SysFreeManager/Videos/Los Lobos - La Bamba.MP4").getAbsolutePath();
      //String MEDIA_URL = new File("src/SysFreeManager/Videos/---OneRepublic - All The Right Moves.MKV").getAbsolutePath();
      //String MEDIA_URL = new File("src/SysFreeManager/Videos/PLO.MP4").getAbsolutePath();
      //String MEDIA_URL = new File("src/SysFreeManager/Videos/Kisungu.mp4").getAbsolutePath();
      //String MEDIA_URL = new File("src/SysFreeManager/Videos/Babu Owino.MP4").getAbsolutePath();
      //String MEDIA_URL = new File("src/SysFreeManager/Videos/Electric.MP4").getAbsolutePath();




    @Override
    public void initialize(URL Location, ResourceBundle resources) {

        btnUserManagement.setOnAction(event -> {
            try {
                sceneSwitches.goToUserManagement(event);
                mediaPlayer.stop();
            }catch (Exception e){
                System.out.println("An Error Occurred");
            }
        });


        btnAddUser.setOnAction(event -> {
            try {
                sceneSwitches.goToAddUser(event);
                mediaPlayer.stop();
            }catch (Exception e){
                System.out.println("An Error Occurred");
            }
        });


        btnAddMedia.setOnAction(event -> {
            try {
                sceneSwitches.goToAddMedia(event);
                mediaPlayer.stop();
            }catch (Exception e){
                System.out.println("An Error Occurred");
            }
        });

        btnDashboard.setOnAction(event -> {
            try {
                sceneSwitches.goToDashboard(event);
                mediaPlayer.stop();
            }catch (Exception e){
                System.out.println("An Error Occurred");
            }
        });

        btnLogout.setOnAction(event -> {
            try {
                sceneSwitches.goToLogin(event);
                mediaPlayer.stop();
            }catch (Exception e){
                System.out.println("An Error Occurred");
            }
        });



        // The regex below only extracts the Tittle part of the media

        String [] MediaTitlePartsMain = MEDIA_URL.split("\\\\");
        //Extract the Title part which usually the last String after split
        String MediaTitle = MediaTitlePartsMain [MediaTitlePartsMain.length - 1];
        //System.out.println(MediaTitle);
        lblMediaTitle.setText(MediaTitle);

        //lblMediaTitle.setText(MEDIA_URL);

        Media media = new Media(new File(MEDIA_URL).toURI().toString());
        mediaPlayer = new MediaPlayer(media);

        //mediaPlayer = new MediaPlayer(new Media(this.getClass().getResource(MEDIA_URL).toExternalForm()));

        //mediaPlayer.setAutoPlay(true);
        //mediaPlayer.setCycleCount(10);
        //mediaPlayer.getAudioEqualizer();


        // Volume Slider
        homeVolumeSlider.setValue(mediaPlayer.getVolume() * 100);
        homeVolumeSlider.valueProperty().addListener(observable -> mediaPlayer.setVolume(homeVolumeSlider.getValue() / 100));
        /*  //Replaced with Lambda
            homeVolumeSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                mediaPlayer.setVolume(homeVolumeSlider.getValue() / 100);

            }
        }); */

        homeMedia.setMediaPlayer(mediaPlayer);

//        Resize Video

        //DoubleProperty width = homeMedia.fitWidthProperty();
        //DoubleProperty height = homeMedia.fitHeightProperty();

        //width.bind(Bindings.selectDouble(homeMedia.sceneProperty(), "width"));
        //height.bind(Bindings.selectDouble(homeMedia.sceneProperty(), "height"));

    }



    // Controls player icons functions
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

    //To fetch admin details
    //setText admins's name and setImage admin's profile picture

    // Receives admin's Username from the Login page
    public void setLoggedInUserDetails(String username){


        theUsername = username;
        //System.out.println("The Username passed is " + theUsername);
        //String theUsername = username;
        //this.lblUsername.setText(username);
        //System.out.println(username);

    }



    public void getAdminDetails(){


        try {
            Connection myConn = MySQLConnection.dbConnector();


            String mySQL = "SELECT * FROM systemfreedb.admins WHERE username = ? ;";

            PreparedStatement preparedStatement = myConn.prepareStatement(mySQL);

            //System.out.println(theUsername);

            preparedStatement.setString(1,theUsername);


            //preparedStatement.execute();


            //ResultSet myResultSet = myStatement.executeQuery(SQL);

            ResultSet myResultSet = preparedStatement.executeQuery();

            //Process the result set

            while (myResultSet.next()) {

                //System.out.println(myResultSet.getString("firstName"));
                lblUsername.setText(myResultSet.getString("firstName"));

                // Extracting Image from DB
                InputStream inputStream = myResultSet.getBinaryStream("adminPic");
                OutputStream outputStream = new FileOutputStream(new File("adminPicture.jpg"));
                byte [] content = new byte[1024];
                int size;

                while ((size = inputStream.read(content)) != -1){
                        outputStream.write(content, 0, size);
                }

                outputStream.close();
                inputStream.close();


                Image image = new Image("file:adminPicture.jpg");

                adminPicHome.setImage(image);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    } // getAdminDetails method ends here


}
