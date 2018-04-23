package SysFreeManager.BackEnd;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * To Create public methods that will switch scenes on request
 * This will remove code repetition
 * */


public class SceneSwitches  {



    public  void   goToUserManagement (ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/SysFreeManager/UserInterface/UserManagement.fxml"));
        Parent home = loader.load();
        Scene home_scene = new Scene(home);
        Stage home_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        home_stage.setScene(home_scene);
        home_stage.show();
    }





    public void goToAddMedia(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/SysFreeManager/UserInterface/AddMedia.fxml"));
        Parent home = loader.load();
        Scene home_scene = new Scene(home);
        Stage home_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        home_stage.setScene(home_scene);
        home_stage.show();

    }


    public void goToAddUser(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/SysFreeManager/UserInterface/AddUser.fxml"));
        Parent home = loader.load();
        Scene home_scene = new Scene(home);
        Stage home_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        home_stage.setScene(home_scene);
        home_stage.show();

    }


    public void goToDashboard(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/SysFreeManager/UserInterface/Dashboard.fxml"));
        Parent home = loader.load();
        Scene home_scene = new Scene(home);
        Stage home_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        home_stage.setScene(home_scene);
        home_stage.show();

    }


    public void goToLogin(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/SysFreeManager/UserInterface/Login.fxml"));
        Parent home = loader.load();
        Scene home_scene = new Scene(home);
        Stage home_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        home_stage.setScene(home_scene);
        home_stage.show();
        // Stops media on log out


}

    public void goToHome(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/SysFreeManager/UserInterface/Home.fxml"));
        Parent home = loader.load();
        Scene home_scene = new Scene(home);
        Stage home_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        home_stage.setScene(home_scene);
        home_stage.show();

    }

        public void goToRegisterAdmin (ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/SysFreeManager/UserInterface/RegisterAdmin.fxml"));
        Parent home = loader.load();
        Scene home_scene = new Scene(home);
        Stage home_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        home_stage.setScene(home_scene);
        home_stage.show();

    }
}
