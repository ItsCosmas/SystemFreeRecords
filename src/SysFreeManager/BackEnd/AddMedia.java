package SysFreeManager.BackEnd;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

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
            }catch (Exception e){
                System.out.println("An Error Occurred");
            }
        });


        btnAddUser.setOnAction(event -> {
            try {
                sceneSwitches.goToAddUser(event);
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

    } //End of initialize

}
