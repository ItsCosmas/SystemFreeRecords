package SysFreeManager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("UserInterface/Login.fxml"));
        stage.setTitle("Free Management System");
        //stage.setIconified(true);
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
