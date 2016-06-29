/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAOs.Messages;
import DAOs.UsersDao;
import Main.Runnable;
import Models.Users;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Adarsh
 */
public class CreateCustomerScreenController implements Initializable, ControllerScreen {

    ScreensController myController;

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField emailid;
    @FXML
    private TextField phonenumber;
    @FXML
    private TextField address;
    @FXML
    private RadioButton adminType;
    @FXML
    private RadioButton customertype;
    @FXML
    private Button createAccount;
    @FXML
    private Button reset;
    @FXML
    private Button back;
    @FXML
    private TextField fullname;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }
    
    @FXML
    private void createAccountButtonAction(ActionEvent event) {

        Messages con_mes = new Messages();
        String LFullname, LUsername, LPassword, LEmailid, LPhonenumber, LAddress, LUsertype = null;
        Date createdon = null;
        LFullname = fullname.getText();
        LUsername = username.getText();
        LPassword = password.getText();
        LEmailid = emailid.getText();
        LPhonenumber = phonenumber.getText();
        LAddress = address.getText();
        
        //Validation block
        if (LFullname.length() == 0 || LUsername.length() == 0 || LPassword.length() == 0
                || LEmailid.length() == 0 || LPhonenumber.length() == 0 || LAddress.length() == 0) {
            con_mes.alertMessage(Alert.AlertType.ERROR, "Error", "Please fill in all the details in the form!!");
            myController.setScreen(Runnable.screen6ID);
        } else {
            boolean conditionPass = true;
            if (adminType.isSelected() && customertype.isSelected()) {

                conditionPass = false;
                con_mes.alertMessage(Alert.AlertType.ERROR, "Error", "Please select either Admin or Customer");
                myController.setScreen(Runnable.screen6ID);

            } else if (adminType.isSelected()) {
                LUsertype = "admin";
            } else if (customertype.isSelected()) {
                LUsertype = "customer";
            } else {
                conditionPass = false;
                con_mes.alertMessage(Alert.AlertType.ERROR, "Error", "Please select user type");
                myController.setScreen(Runnable.screen6ID);
            }
            if (conditionPass) {
                Users createUser = new Users(LUsername, LPassword, LUsertype, LFullname, LEmailid, LPhonenumber, LAddress, createdon, createdon);
                UsersDao ud = new UsersDao();
                Users createdUser = null;
                createdUser = ud.createUser(createUser);
                con_mes.alertMessage(Alert.AlertType.INFORMATION, "Success Message", "User ID " + createdUser.getUserID() + " created succesfully!!!");
                fullname.setText("");
                username.setText("");
                password.setText("");
                emailid.setText("");
                phonenumber.setText("");
                address.setText("");
                adminType.setSelected(false);
                customertype.setSelected(false);
            }
        }
    }

    @FXML
    private void resetButtonAction(ActionEvent event) {
        fullname.setText("");
        username.setText("");
        password.setText("");
        emailid.setText("");
        phonenumber.setText("");
        address.setText("");
        adminType.setSelected(false);
        customertype.setSelected(false);
    }

    @FXML
    private void backButtonAction(ActionEvent event) {
        if(Runnable.userid > 0){
            myController.setScreen(Runnable.screen2ID);
        }else{
            myController.setScreen(Runnable.screen1ID);
        }
    }
}
