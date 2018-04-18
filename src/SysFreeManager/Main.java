package SysFreeManager;

import SysFreeManager.BackEnd.RegisterAdmin;
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

        //RegisterAdmin registerAdmin = (RegisterAdmin) loader.getController();
        //registerAdmin.init(primaryStage);

        primaryStage.getIcons().add(new Image("SysFreeManager/images/icon.png"));
        primaryStage.setTitle("Free Management System");
        //stage.setIconified(true);
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        //stage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
