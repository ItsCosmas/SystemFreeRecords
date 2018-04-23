package SysFreeManager;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserInterface/Login.fxml"));
        Parent root = loader.load();


        //Icon
        primaryStage.getIcons().add(new Image("SysFreeManager/images/icon.png"));
        //Title
        primaryStage.setTitle("Free Management System");

        //Add scene to primaryStage
        primaryStage.setScene(new Scene(root));

        //Hide maximize window
        primaryStage.setResizable(false);

        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
