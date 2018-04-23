package SysFreeManager.BackEnd;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class Dashboard implements Initializable {

    @FXML
    private Button btnAddUser,btnAddMedia,btnLogout,btnGoHome;

    SceneSwitches sceneSwitches = new SceneSwitches();




    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub



        btnAddUser.setOnAction(event -> {
            try {
                sceneSwitches.goToAddUser(event);
            }catch (Exception e){}
        });


        btnAddMedia.setOnAction(event -> {
            try {
                sceneSwitches.goToAddMedia(event);
            }catch (Exception e){}
        });

        btnLogout.setOnAction(event -> {
            try {
                sceneSwitches.goToLogin(event);
            }catch (Exception e){}
        });

        btnGoHome.setOnAction(event -> {
            try {
                sceneSwitches.goToHome(event);

            }catch (Exception e){

            }
        });

    } // End of initialize method

}
