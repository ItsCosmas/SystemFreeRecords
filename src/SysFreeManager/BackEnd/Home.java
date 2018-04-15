/**
 * To query the DB for current Admin User and display along with a welcome text
 * To query the DB for number of Exclusive media stored
 * To query the DB for Journalist who grossed the highest pay and display the pay along
 * To query the DB for media item that grossed the highest pay and play the media in future release
 * To give a monthly summary of the media items
 *
 * */

package SysFreeManager.BackEnd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Home {


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


}
