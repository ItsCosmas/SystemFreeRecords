package SysFreeManager.BackEnd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Dashboard {

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

}
