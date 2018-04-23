package SysFreeManager.BackEnd;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class UserManagement implements Initializable{

    @FXML
    private Button btnAddUser,btnAddMedia,btnDashboard,btnGoHome;

    private SceneSwitches sceneSwitches = new SceneSwitches();




    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub



        btnAddUser.setOnAction(event -> {
            try {
                sceneSwitches.goToAddUser(event);
            }catch (Exception e){
                System.out.println("An Error Occurred");
            }
        });


        btnAddMedia.setOnAction(event -> {
            try {
                sceneSwitches.goToAddMedia(event);
            }catch (Exception e){
                System.out.println("An Error Occurred");
            }
        });

        btnDashboard.setOnAction(event -> {
            try {
                sceneSwitches.goToDashboard(event);
            }catch (Exception e){
                System.out.println("An Error Occurred");
            }
        });

        btnGoHome.setOnAction(event -> {
            try {
                sceneSwitches.goToHome(event);

            }catch (Exception e){
                System.out.println("An Error Occurred");
            }
        });

    } // End of initialize method

}  // End of class
