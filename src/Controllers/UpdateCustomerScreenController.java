/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAOs.UsersDao;
import Main.Runnable;
import Models.Users;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Adarsh
 */
public class UpdateCustomerScreenController implements Initializable, ControllerScreen {

    ScreensController myController;

    @FXML
    private Button BACK;
    @FXML
    private Button update;
    @FXML
    private Button resert;
    @FXML
    private Button delete;
    @FXML
    private Button customerSearch;
    @FXML
    private ComboBox customerIDDD;
    @FXML
    private Pane customerSearchPane;
    @FXML
    private TextField username;
    @FXML
    private TextField phonenumber;
    @FXML
    private TextField emailid;
    @FXML
    private TextField address;
    @FXML
    private TextField password;
    @FXML
    private TextField usertype;
    @FXML
    private TextField fullname;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //userID dropdown population
        ArrayList<Integer> userList = new ArrayList<Integer>();
        UsersDao ud = new UsersDao();
        userList = ud.getAllUsers();
        customerIDDD.getItems().clear();

        customerIDDD.getItems().addAll(userList);
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    @FXML
    private void backButtonAction(ActionEvent event) {
        customerIDDD.setValue("");
        username.setText("");
        phonenumber.setText("");
        emailid.setText("");
        address.setText("");
        password.setText("");
        usertype.setText("");
        fullname.setText("");
        customerSearchPane.setVisible(false);

        myController.setScreen(Runnable.screen2ID);
    }

    @FXML
    private void updateButtonAction(ActionEvent event) {
        UsersDao updateUser = new UsersDao();
        Users userValues = new Users();
        userValues.setUserID((int) customerIDDD.getValue());
        userValues.setUsername(username.getText());
        userValues.setUserpassword(password.getText());
        userValues.setUsertype(usertype.getText());
        userValues.setAddress(address.getText());
        userValues.setFullname(fullname.getText());
        userValues.setEmailid(emailid.getText());
        userValues.setPhonenumber(phonenumber.getText());

        updateUser.updateUser(userValues);
    }

    @FXML
    private void resetButtonAction(ActionEvent event) {

        username.setText("");
        phonenumber.setText("");
        emailid.setText("");
        address.setText("");
        password.setText("");
        usertype.setText("");
        fullname.setText("");
    }

    @FXML
    private void customerSearchButtonAction(ActionEvent event) {
        Users searchRes;
        UsersDao udSearch = new UsersDao();
        searchRes = udSearch.searchUser((int) customerIDDD.getValue());

        username.setText(searchRes.getUsername());
        phonenumber.setText(searchRes.getPhonenumber());
        emailid.setText(searchRes.getEmailid());
        address.setText(searchRes.getAddress());
        password.setText(searchRes.getUserpassword());
        usertype.setText(searchRes.getUsertype());
        fullname.setText(searchRes.getFullname());

        customerSearchPane.setVisible(true);
    }

    @FXML
    private void deleteButtonAction(ActionEvent event) {
        UsersDao deleteUser = new UsersDao();
        deleteUser.deleteUser((int) customerIDDD.getValue());
        username.setText("");
        phonenumber.setText("");
        emailid.setText("");
        address.setText("");
        password.setText("");
        usertype.setText("");
        fullname.setText("");
    }
}
