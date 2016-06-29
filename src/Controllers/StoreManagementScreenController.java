/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAOs.StoreDao;
import Main.Runnable;
import Models.Store;
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
public class StoreManagementScreenController implements Initializable, ControllerScreen {

    ScreensController myController;

    @FXML
    private Button submit;
    @FXML
    private Button reset;
    @FXML
    private Button back;
    @FXML
    private TextField storeID;
    @FXML
    private TextField storeName;
    @FXML
    private TextField storeLocation;
    @FXML
    private TextField storePhonenumber;
    @FXML
    private TextField storeEmailID;
    @FXML
    private Button addStore;
    @FXML
    private Button deleteStore;
    @FXML
    private Button updateStore;
    @FXML
    private Pane deleteProductPane;
    @FXML
    private ComboBox deleteStoreIDDD;
    @FXML
    private Button storeDelete;
    @FXML
    private Pane updatePane;
    @FXML
    private TextField updateStoreID1;
    @FXML
    private Button update;
    @FXML
    private TextField updateStoreName1;
    @FXML
    private TextField updateStoreLocation1;
    @FXML
    private TextField updateStorePhonenumber1;
    @FXML
    private TextField updateStoreEmailID1;

    @FXML
    private ComboBox searchDtoreIDDD1;
    @FXML
    private Button storeSearch;
    @FXML
    private Pane addStorePane;
    @FXML
    private Button updateReset;
    @FXML
    private Pane searchStorePane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //StoreID drodown values
        ArrayList<Integer> storeIDList = new ArrayList<Integer>();
        StoreDao sd = new StoreDao();
        storeIDList = sd.getAllStore();

        deleteStoreIDDD.getItems().clear();
        deleteStoreIDDD.getItems().addAll(storeIDList);

        searchDtoreIDDD1.getItems().clear();
        searchDtoreIDDD1.getItems().addAll(storeIDList);
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    @FXML
    private void submitButtonAction(ActionEvent event) {

        Store createStore = new Store();

        createStore.setStoreID(storeID.getText());
        createStore.setStoreName(storeName.getText());
        createStore.setLocation(storeLocation.getText());
        createStore.setPhonenumber(storePhonenumber.getText());
        createStore.setEmialID(storeEmailID.getText());

        storeID.setText("");
        storeName.setText("");
        storeLocation.setText("");
        storePhonenumber.setText("");
        storeEmailID.setText("");

        StoreDao sd = new StoreDao();
        sd.addStore(createStore);
    }

    @FXML
    private void resetButtonAction(ActionEvent event) {

        storeID.setText("");
        storeName.setText("");
        storeLocation.setText("");
        storePhonenumber.setText("");
        storeEmailID.setText("");
    }

    @FXML
    private void backButtonAction(ActionEvent event) {
        storeID.setText("");
        storeName.setText("");
        storeLocation.setText("");
        storePhonenumber.setText("");
        storeEmailID.setText("");
        deleteStoreIDDD.setValue("");
        searchDtoreIDDD1.setValue("");

        deleteProductPane.setVisible(false);
        updatePane.setVisible(false);
        searchStorePane.setVisible(false);
        addStorePane.setVisible(false);

        myController.setScreen(Runnable.screen2ID);
    }

    @FXML
    private void addStoreButtonAction(ActionEvent event) {
        deleteProductPane.setVisible(false);
        updatePane.setVisible(false);
        searchStorePane.setVisible(false);
        addStorePane.setVisible(true);
    }

    @FXML
    private void deleteStoreButtonAction(ActionEvent event) {
        deleteProductPane.setVisible(true);
        updatePane.setVisible(false);
        searchStorePane.setVisible(false);
        addStorePane.setVisible(false);
    }

    @FXML
    private void updateStoreButtonAction(ActionEvent event) {

        deleteProductPane.setVisible(false);
        updatePane.setVisible(false);
        searchStorePane.setVisible(true);
        addStorePane.setVisible(false);

    }

    @FXML
    private void updateButtonAction(ActionEvent event) {

        Store updateStore = new Store();

        updateStore.setStoreName(updateStoreName1.getText());
        updateStore.setLocation(updateStoreLocation1.getText());
        updateStore.setPhonenumber(updateStorePhonenumber1.getText());
        updateStore.setEmialID(updateStoreEmailID1.getText());

        updateStoreName1.setText("");
        updateStoreLocation1.setText("");
        updateStorePhonenumber1.setText("");
        updateStoreEmailID1.setText("");

        StoreDao sd = new StoreDao();
        sd.updateStore(updateStore);
    }

    @FXML
    private void storeSearchButtonAction(ActionEvent event) {
        deleteProductPane.setVisible(false);
        updatePane.setVisible(true);
        searchStorePane.setVisible(true);
        addStorePane.setVisible(false);

        Store searchRes;
        StoreDao sd = new StoreDao();
        searchRes = sd.searchStore((String) searchDtoreIDDD1.getValue());

        updateStoreName1.setText(searchRes.getStoreName());
        updateStoreLocation1.setText(searchRes.getLocation());
        updateStorePhonenumber1.setText(searchRes.getPhonenumber());
        updateStoreEmailID1.setText(searchRes.getEmialID());
    }

    @FXML
    private void updateResetButtonAction(ActionEvent event) {

        storeName.setText("");
        storeLocation.setText("");
        storePhonenumber.setText("");
        storeEmailID.setText("");

        searchDtoreIDDD1.setValue("");
    }

    @FXML
    private void storeDeleteButtonAction(ActionEvent event) {
        StoreDao sd = new StoreDao();
        sd.deleteStore((String) deleteStoreIDDD.getValue());
        deleteStoreIDDD.setValue("");
    }
}
