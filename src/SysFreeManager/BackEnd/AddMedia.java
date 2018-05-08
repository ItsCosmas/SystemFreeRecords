package SysFreeManager.BackEnd;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;

import java.net.URL;
import java.util.ResourceBundle;


public class AddMedia implements Initializable {

    private SceneSwitches sceneSwitches = new SceneSwitches();

    @FXML
    private Button btnUserManagement,btnAddUser,btnDashboard,btnGoHome;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // TODO Auto-generated method stub



        btnUserManagement.setOnAction(event -> {
            try {
                sceneSwitches.goToUserManagement(event);
                myMediaPlayer.stop();
            }catch (Exception e){
                System.out.println("An Error Occurred");
            }
        });


        btnAddUser.setOnAction(event -> {
            try {
                sceneSwitches.goToAddUser(event);
                myMediaPlayer.stop();
            }catch (Exception e){
                System.out.println("An Error Occurred");
            }
        });

        btnDashboard.setOnAction(event -> {
            try {
                sceneSwitches.goToDashboard(event);
                myMediaPlayer.stop();
            }catch (Exception e){
                System.out.println("An Error Occurred");
            }
        });

        btnGoHome.setOnAction(event -> {
            try {
                sceneSwitches.goToHome(event);
                myMediaPlayer.stop();

            }catch (Exception e){
                System.out.println("An Error Occurred");
            }
        });

    } //End of initialize


    @FXML
    private MediaView theMediaView;
    @FXML
    private Label lblMediaTitle;
    @FXML
    Slider theVolumeSlider;

    private MediaPlayer myMediaPlayer;

    private Stage stage; // Just can't remove this, Used in Show file dialog



    @FXML
    public void openMediaFile(){

        //System.out.println("Opening file");
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Choose Media to Add");
        fileChooser.getExtensionFilters().addAll(
                // To filter only specific image formats
                // To add more image formats
                new FileChooser.ExtensionFilter("Video Files","*.mp4")
                //new FileChooser.ExtensionFilter("All Files","*.*")
        );




        File file = fileChooser.showOpenDialog(stage);

        if (file != null){

            // To Display selected image on ImageView
            String MEDIA_URL = file.toString();
            //System.out.println(MEDIA_URL);


            // The regex below only extracts the Tittle part of the media

            String [] MediaTitlePartsMain = MEDIA_URL.split("\\\\");
            //Extract the Title part which usually the last String after split
            String MediaTitle = MediaTitlePartsMain [MediaTitlePartsMain.length - 1];
            //System.out.println(MediaTitle);
            lblMediaTitle.setText(MediaTitle);

            //lblMediaTitle.setText(MEDIA_URL);

            Media media = new Media(new File(MEDIA_URL).toURI().toString());
            myMediaPlayer = new MediaPlayer(media);

            //mediaPlayer = new MediaPlayer(new Media(this.getClass().getResource(MEDIA_URL).toExternalForm()));

            //mediaPlayer.setAutoPlay(true);
            //mediaPlayer.setCycleCount(10);
            //mediaPlayer.getAudioEqualizer();


            // Volume Slider
            theVolumeSlider.setValue(myMediaPlayer.getVolume() * 100);
            theVolumeSlider.valueProperty().addListener(observable -> myMediaPlayer.setVolume(theVolumeSlider.getValue() / 100));
        /*  //Replaced with Lambda
            homeVolumeSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                mediaPlayer.setVolume(homeVolumeSlider.getValue() / 100);

            }
        }); */

        theMediaView.setMediaPlayer(myMediaPlayer);

        }

        }

    // Controls player icons functions
    @FXML
    private void play(){
        myMediaPlayer.play();
    }

    @FXML
    private void pause(){
        myMediaPlayer.pause();
    }
    @FXML
    private void stop(){
        myMediaPlayer.stop();
    }
    @FXML
    private void restart(){
        myMediaPlayer.seek(myMediaPlayer.getStartTime());
        myMediaPlayer.play();
    }

    }

