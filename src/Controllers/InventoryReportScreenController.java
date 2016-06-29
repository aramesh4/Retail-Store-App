/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAOs.ProductDao;
import Main.Runnable;
import Models.InventoryTableModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Adarsh
 */
public class InventoryReportScreenController implements Initializable, ControllerScreen {

    ScreensController myController;

    @FXML
    private TableView<InventoryTableModel> inventoryTable;
    @FXML
    private Button back;
    @FXML
    private TableColumn<InventoryTableModel, String> productID;
    @FXML
    private TableColumn<InventoryTableModel, String> productName;
    @FXML
    private TableColumn<InventoryTableModel, String> productDesc;
    @FXML
    private TableColumn<InventoryTableModel, Number> availableQuantity;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Populating table view data
        productID.setCellValueFactory(new PropertyValueFactory<InventoryTableModel, String>("productID"));
        productName.setCellValueFactory(new PropertyValueFactory<InventoryTableModel, String>("productName"));
        productDesc.setCellValueFactory(new PropertyValueFactory<InventoryTableModel, String>("productDesc"));
        availableQuantity.setCellValueFactory(new PropertyValueFactory<InventoryTableModel, Number>("availableQuantity"));

        ProductDao productTable = new ProductDao();
        ObservableList<InventoryTableModel> tableData = FXCollections.observableArrayList();

        inventoryTable.setItems(productTable.getInventory());
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    @FXML
    private void backButtonAction(ActionEvent event) {
        myController.setScreen(Runnable.screen2ID);
    }
}
