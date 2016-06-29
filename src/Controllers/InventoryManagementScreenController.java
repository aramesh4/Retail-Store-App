/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAOs.Messages;
import DAOs.ProductDao;
import Main.Runnable;
import Models.Product;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Adarsh
 */
public class InventoryManagementScreenController implements Initializable, ControllerScreen {

    ScreensController myController;

    //Create connection class object whihc will be used in all the methods
    private Messages con_mes = new Messages();

    @FXML
    private Button productSave;
    @FXML
    private Button productReset;
    @FXML
    private Button productDelete;
    @FXML
    private Button productUpdate;
    @FXML
    private Button updateReset;
    @FXML
    private Button BACK;
    @FXML
    private TextField CreateProductID;
    @FXML
    private TextField createProductName;
    @FXML
    private TextField createProductDesc;
    @FXML
    private TextField createProductQuantity;
    @FXML
    private TextField createProductPrice;
    @FXML
    private TextField createProductDiscount;
    @FXML
    private DatePicker createProductExpiryDate;
    @FXML
    private TextField createdby;
    @FXML
    private TextField updateProductName;
    @FXML
    private TextField updateProductDesc;
    @FXML
    private TextField updateProductQuantity;
    @FXML
    private TextField updateProductPrice;
    @FXML
    private TextField updateProductDiscount;
    @FXML
    private DatePicker updateProductExpiryDate;
    @FXML
    private Pane addProductPane;
    @FXML
    private Pane deleteProductPane;
    @FXML
    private Pane updateProductPane;
    @FXML
    private Button addProduct;
    @FXML
    private Button deleProduct;
    @FXML
    private Button updateProduct;
    @FXML
    private Pane updateSearchPane;
    @FXML
    private Button searchProduct;
    @FXML
    private ComboBox delProductIDDD;
    @FXML
    private ComboBox updateProductIDDD;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //populating dropdownlist
        ArrayList<Integer> productList = new ArrayList<Integer>();
        ProductDao pd = new ProductDao();
        productList = pd.getAllProductID();

        delProductIDDD.getItems().clear();
        delProductIDDD.getItems().addAll(productList);

        updateProductIDDD.getItems().clear();
        updateProductIDDD.getItems().addAll(productList);
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    @FXML
    private void saveButtonAction(ActionEvent event) {

        Product saveProductValues = new Product();

        Date expiryDate = Date.valueOf(createProductExpiryDate.getValue());

        saveProductValues.setProductID(CreateProductID.getText());
        saveProductValues.setProductName(createProductName.getText());
        saveProductValues.setProductDesc(createProductDesc.getText());
        saveProductValues.setQuantity(Integer.parseInt(createProductQuantity.getText()));
        saveProductValues.setUnitPrice(Double.parseDouble(createProductPrice.getText()));
        saveProductValues.setDiscountPercentage(Double.parseDouble(createProductDiscount.getText()));
        saveProductValues.setExpiryDate(expiryDate);
        saveProductValues.setCreatedBy(Integer.parseInt(createdby.getText()));

        CreateProductID.setText("");
        createProductName.setText("");
        createProductDesc.setText("");
        createProductQuantity.setText("");
        createProductPrice.setText("");
        createProductDiscount.setText("");
        createProductExpiryDate.setValue(LocalDate.MIN);

        ProductDao pd = new ProductDao();
        pd.addProduct(saveProductValues);
    }

    @FXML
    private void resetButtonAction(ActionEvent event) {
        createProductName.setText("");
        createProductDesc.setText("");
        createProductQuantity.setText("");
        createProductPrice.setText("");
        createProductDiscount.setText("");
    }

    @FXML
    private void deleteButtonAction(ActionEvent event) {
        ProductDao pd = new ProductDao();
        pd.deleteProduct((String) delProductIDDD.getValue());
        delProductIDDD.setValue("");
    }

    @FXML
    private void updateButtonAction(ActionEvent event) {
        ProductDao pd = new ProductDao();
        Product updateProduct = new Product();

        Date expiryDate = Date.valueOf(updateProductExpiryDate.getValue());

        updateProduct.setProductID((String) updateProductIDDD.getValue());
        updateProduct.setProductName(updateProductName.getText());
        updateProduct.setProductDesc(updateProductDesc.getText());
        updateProduct.setQuantity(Integer.parseInt(updateProductQuantity.getText()));
        updateProduct.setUnitPrice(Double.parseDouble(updateProductPrice.getText()));
        updateProduct.setDiscountPercentage(Double.parseDouble(updateProductDiscount.getText()));
        updateProduct.setExpiryDate(expiryDate);

        pd.updateProduct(updateProduct);

        updateProductName.setText("");
        updateProductDesc.setText("");
        updateProductQuantity.setText("");
        updateProductPrice.setText("");
        updateProductDiscount.setText("");

    }

    @FXML
    private void updateResetButtonAction(ActionEvent event) {
        updateProductName.setText("");
        updateProductDesc.setText("");
        updateProductQuantity.setText("");
        updateProductPrice.setText("");
        updateProductDiscount.setText("");
    }

    @FXML
    private void backButtonAction(ActionEvent event) {

        updateProductName.setText("");
        updateProductDesc.setText("");
        updateProductQuantity.setText("");
        updateProductPrice.setText("");
        updateProductDiscount.setText("");
        updateProductIDDD.setValue("");

        CreateProductID.setText("");
        createProductName.setText("");
        createProductDesc.setText("");
        createProductQuantity.setText("");
        createProductPrice.setText("");
        createProductDiscount.setText("");

        addProductPane.setVisible(false);
        deleteProductPane.setVisible(false);
        updateProductPane.setVisible(false);
        updateSearchPane.setVisible(false);

        myController.setScreen(Runnable.screen2ID);
    }

    @FXML
    private void addProductButtonAction(ActionEvent event) {
        addProductPane.setVisible(true);
        deleteProductPane.setVisible(false);
        updateProductPane.setVisible(false);
        updateSearchPane.setVisible(false);

        int userid = Runnable.userid;
        createdby.setText(Integer.toString(userid));
    }

    @FXML
    private void deleProductButtonAction(ActionEvent event) {
        deleteProductPane.setVisible(true);
        addProductPane.setVisible(false);
        updateProductPane.setVisible(false);
        updateSearchPane.setVisible(false);
    }

    @FXML
    private void updateProductButtonAction(ActionEvent event) {
        updateSearchPane.setVisible(true);
        updateProductPane.setVisible(false);
        addProductPane.setVisible(false);
        deleteProductPane.setVisible(false);
    }

    @FXML
    private void searchProductButtonAction(ActionEvent event) {
        ProductDao pd = new ProductDao();
        Product productRef = null;
        productRef = pd.searchProduct((String) updateProductIDDD.getValue());

        if (productRef.getProductID().length() > 0) {
            updateProductName.setText(productRef.getProductName());
            updateProductDesc.setText(productRef.getProductDesc());
            updateProductQuantity.setText(Integer.toString(productRef.getQuantity()));
            updateProductPrice.setText(Double.toString(productRef.getUnitPrice()));
            updateProductDiscount.setText(Double.toString(productRef.getDiscountPercentage()));

            updateProductPane.setVisible(true);
        }
    }
}
