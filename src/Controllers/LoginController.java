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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import DAOs.*;
import Models.*;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;

/**
 *
 * @author Adarsh
 */
public class LoginController implements Initializable, ControllerScreen {

    ScreensController myController;
    
    public static Users loginUserValues = new Users();

    @FXML
    private Button login;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button reset;
    @FXML
    private Label message;
    @FXML
    private Hyperlink register;
    @FXML
    private Text welcome;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }
    
    @FXML
    private void loginButtonAction(ActionEvent event) {

        // Users class reference 
        Users users_ref;

        String username1 = this.username.getText();
        String password1 = this.password.getText();

        username.setText("");
        password.setText("");

        UsersDao ud = new UsersDao();
        
        users_ref = ud.verifyLogin(username1, password1);

        String logintype = users_ref.getUsertype();

        if (logintype.length()>0 && logintype.equals("admin")) {
            myController.setScreen(Runnable.screen2ID);
        } else {
            myController.setScreen(Runnable.screen8ID);
        }
        //set the userid and usertype. which will be referred in other controllers
        Runnable.userid = users_ref.getUserID();
        Runnable.usertType = users_ref.getUsertype();
    }

    @FXML
    private void resetButtonAction(ActionEvent event) {
        //set the username nad password to blank
        username.setText("");
        password.setText("");
        //set the prompt text to username and password
        username.setPromptText("username");
        password.setPromptText("password");
        message.setText("username and password reseted...");
    }

    @FXML
    private void registerLinkAction(ActionEvent event) {
        myController.setScreen(Runnable.screen6ID);
    }
}
