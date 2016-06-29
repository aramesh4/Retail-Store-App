package Controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Main.Runnable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Adarsh
 */
public class AdminScreenController extends LoginController implements Initializable, ControllerScreen {
    ScreensController myController;

    @FXML
    private Label welcomeMes;
    @FXML
    private Button updateInfo;
    @FXML
    private Button buildOrder;
    @FXML
    private Button createCustomer;
    @FXML
    private Button inventoryReport;
    @FXML
    private Button logout;
    @FXML
    private Button inventoryManagement;
    @FXML
    private Button storeManagement;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
    @Override
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }   
    
    @FXML
    private void updateButtonAction(ActionEvent event) {
        myController.setScreen(Runnable.screen4ID);        
    }

    @FXML
    private void buildOrderButtonAction(ActionEvent event) {
        myController.setScreen(Runnable.screen8ID);
    }

    @FXML
    private void createCustomerButtonAction(ActionEvent event) {
        myController.setScreen(Runnable.screen6ID);
    }

    @FXML
    private void inventoryReportButtonAction(ActionEvent event) {
        myController.setScreen(Runnable.screen7ID);
    }

    @FXML
    private void logoutButtonAction(ActionEvent event) {
        myController.setScreen(Runnable.screen1ID); 
    }

    @FXML
    private void inventoryManagementButtonAction(ActionEvent event) {
        myController.setScreen(Runnable.screen3ID);
    }

    @FXML
    private void storeManagementButtonAction(ActionEvent event) {
        myController.setScreen(Runnable.screen5ID);
    }    
}
