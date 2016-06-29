/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAOs.Messages;
import DAOs.OrderDetailDao;
import DAOs.OrdersDao;
import DAOs.ProductDao;
import DAOs.StoreDao;
import Main.Runnable;
import Models.Orders;
import Models.OrdersDetails;
import Models.Product;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Adarsh
 */
public class OrdersBuildScreenController implements Initializable, ControllerScreen {

    ScreensController myController;

    //Create connection class object whihc will be used in all the methods
    private Messages con_mes = new Messages();

    private int itemNumber;
    private double orderAmount;

    @FXML
    private Button saveOrder;
    @FXML
    private Button cacelOrder;
    @FXML
    private Button addItem;

    @FXML
    private TextField totalAmount;
    @FXML
    private TextField quantity10;
    @FXML
    private TextField quantity20;
    @FXML
    private TextField quantity30;
    @FXML
    private TextField quantity40;
    @FXML
    private TextField quantity50;
    @FXML
    private TextField quantity60;
    @FXML
    private TextField quantity70;
    @FXML
    private TextField quantity80;
    @FXML
    private TextField quantity90;
    @FXML
    private TextField quantity100;

    @FXML
    private ComboBox productName10DD;
    @FXML
    private ComboBox productName20DD;
    @FXML
    private ComboBox productName30DD;
    @FXML
    private ComboBox productName40DD;
    @FXML
    private ComboBox productName50DD;
    @FXML
    private ComboBox productName60DD;
    @FXML
    private ComboBox productName70DD;
    @FXML
    private ComboBox productName80DD;
    @FXML
    private ComboBox productName90DD;
    @FXML
    private ComboBox productName100DD1;

    @FXML
    private TextField subtotal10;
    @FXML
    private TextField subtotal20;
    @FXML
    private TextField subtotal30;
    @FXML
    private TextField subtotal40;
    @FXML
    private TextField subtotal50;
    @FXML
    private TextField subtotal60;
    @FXML
    private TextField subtotal70;
    @FXML
    private TextField subtotal80;
    @FXML
    private TextField subtotal90;
    @FXML
    private TextField subtotal100;

    @FXML
    private Pane item10Pane;
    @FXML
    private Pane item20Pane;
    @FXML
    private Pane item30Pane;
    @FXML
    private Pane item40Pane;
    @FXML
    private Pane item50Pane;
    @FXML
    private Pane item60Pane;
    @FXML
    private Pane item70Pane;
    @FXML
    private Pane item80Pane;
    @FXML
    private Pane item90Pane;
    @FXML
    private Pane item100Pane;
    @FXML
    private ComboBox storeIDDD;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //populate prodictID dropdown and SToreID dropdown
        ArrayList<Integer> productList = new ArrayList<Integer>();
        ProductDao pd = new ProductDao();
        productList = pd.getAllProductID();

        productName10DD.getItems().clear();
        productName10DD.getItems().addAll(productList);

        productName20DD.getItems().clear();
        productName20DD.getItems().addAll(productList);

        productName30DD.getItems().clear();
        productName30DD.getItems().addAll(productList);

        productName40DD.getItems().clear();
        productName40DD.getItems().addAll(productList);

        productName50DD.getItems().clear();
        productName50DD.getItems().addAll(productList);

        productName60DD.getItems().clear();
        productName60DD.getItems().addAll(productList);

        productName70DD.getItems().clear();
        productName70DD.getItems().addAll(productList);

        productName80DD.getItems().clear();
        productName80DD.getItems().addAll(productList);

        productName90DD.getItems().clear();
        productName90DD.getItems().addAll(productList);

        productName100DD1.getItems().clear();
        productName100DD1.getItems().addAll(productList);

        //StoreID drodown values
        ArrayList<Integer> storeIDList = new ArrayList<Integer>();
        StoreDao sd = new StoreDao();
        storeIDList = sd.getAllStore();

        storeIDDD.getItems().clear();
        storeIDDD.getItems().addAll(storeIDList);
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    @FXML
    private void saveOrderButtonAction(ActionEvent event) {

        OrdersDao od = new OrdersDao();
        Orders orderData = new Orders();
        orderData.setUserid(Runnable.userid);
        orderData.setStoreID((String) storeIDDD.getValue());
        orderData.setOrderAmount(Double.parseDouble(totalAmount.getText()));

        orderData = od.saveOrder(orderData);

        ArrayList<OrdersDetails> orderDetail = new ArrayList<>();
        if (subtotal10.getText().length() > 0) {
            orderDetail.add(new OrdersDetails(orderData.getOrderID(), 10, (String) productName10DD.getValue(), Integer.parseInt(quantity10.getText()), Double.parseDouble(subtotal10.getText())));
        }

        if (subtotal20.getText().length() > 0) {
            orderDetail.add(new OrdersDetails(orderData.getOrderID(), 20, (String) productName20DD.getValue(), Integer.parseInt(quantity20.getText()), Double.parseDouble(subtotal20.getText())));

        }

        if (subtotal30.getText().length() > 0) {
            orderDetail.add(new OrdersDetails(orderData.getOrderID(), 30, (String) productName30DD.getValue(), Integer.parseInt(quantity30.getText()), Double.parseDouble(subtotal30.getText())));

        }

        if (subtotal40.getText().length() > 0) {
            orderDetail.add(new OrdersDetails(orderData.getOrderID(), 40, (String) productName40DD.getValue(), Integer.parseInt(quantity40.getText()), Double.parseDouble(subtotal40.getText())));

        }

        if (subtotal50.getText().length() > 0) {
            orderDetail.add(new OrdersDetails(orderData.getOrderID(), 50, (String) productName50DD.getValue(), Integer.parseInt(quantity50.getText()), Double.parseDouble(subtotal50.getText())));

        }

        if (subtotal60.getText().length() > 0) {
            orderDetail.add(new OrdersDetails(orderData.getOrderID(), 60, (String) productName60DD.getValue(), Integer.parseInt(quantity60.getText()), Double.parseDouble(subtotal60.getText())));
        }

        if (subtotal70.getText().length() > 0) {
            orderDetail.add(new OrdersDetails(orderData.getOrderID(), 70, (String) productName70DD.getValue(), Integer.parseInt(quantity70.getText()), Double.parseDouble(subtotal70.getText())));

        }
        if (subtotal80.getText().length() > 0) {
            orderDetail.add(new OrdersDetails(orderData.getOrderID(), 80, (String) productName80DD.getValue(), Integer.parseInt(quantity80.getText()), Double.parseDouble(subtotal80.getText())));

        }

        if (subtotal90.getText().length() > 0) {
            orderDetail.add(new OrdersDetails(orderData.getOrderID(), 90, (String) productName90DD.getValue(), Integer.parseInt(quantity90.getText()), Double.parseDouble(subtotal90.getText())));

        }

        if (subtotal100.getText().length() > 0) {
            orderDetail.add(new OrdersDetails(orderData.getOrderID(), 100, (String) productName100DD1.getValue(), Integer.parseInt(quantity100.getText()), Double.parseDouble(subtotal100.getText())));

        }
        OrderDetailDao orderDao = new OrderDetailDao();
        orderDao.createOrderDetail(orderDetail);
        
        item10Pane.setVisible(false);
        item20Pane.setVisible(false);
        item30Pane.setVisible(false);
        item40Pane.setVisible(false);
        item50Pane.setVisible(false);
        item60Pane.setVisible(false);
        item70Pane.setVisible(false);
        item80Pane.setVisible(false);
        item90Pane.setVisible(false);
        item100Pane.setVisible(false);
        
        storeIDDD.setValue("");
        productName10DD.setValue("");
        quantity10.setText("");
        subtotal10.setText("");
        
        productName20DD.setValue("");
        quantity20.setText("");
        subtotal20.setText("");
        
        productName30DD.setValue("");
        quantity30.setText("");
        subtotal30.setText("");
        
        productName40DD.setValue("");
        quantity40.setText("");
        subtotal40.setText("");
        
        productName50DD.setValue("");
        quantity50.setText("");
        subtotal50.setText("");
        
        productName60DD.setValue("");
        quantity60.setText("");
        subtotal60.setText("");
        
        productName70DD.setValue("");
        quantity70.setText("");
        subtotal70.setText("");
        
        productName80DD.setValue("");
        quantity80.setText("");
        subtotal80.setText("");
        
        productName90DD.setValue("");
        quantity90.setText("");
        subtotal90.setText("");
        
        productName100DD1.setValue("");
        quantity100.setText("");
        subtotal100.setText("");
        
        totalAmount.setText("");
        
        return;
    }

    @FXML
    private void cacelOrderButtonAction(ActionEvent event) {

        item10Pane.setVisible(false);
        item20Pane.setVisible(false);
        item30Pane.setVisible(false);
        item40Pane.setVisible(false);
        item50Pane.setVisible(false);
        item60Pane.setVisible(false);
        item70Pane.setVisible(false);
        item80Pane.setVisible(false);
        item90Pane.setVisible(false);
        item100Pane.setVisible(false);
        
        storeIDDD.setValue("");
        productName10DD.setValue("");
        quantity10.setText("");
        subtotal10.setText("");
        
        productName20DD.setValue("");
        quantity20.setText("");
        subtotal20.setText("");
        
        productName30DD.setValue("");
        quantity30.setText("");
        subtotal30.setText("");
        
        productName40DD.setValue("");
        quantity40.setText("");
        subtotal40.setText("");
        
        productName50DD.setValue("");
        quantity50.setText("");
        subtotal50.setText("");
        
        productName60DD.setValue("");
        quantity60.setText("");
        subtotal60.setText("");
        
        productName70DD.setValue("");
        quantity70.setText("");
        subtotal70.setText("");
        
        productName80DD.setValue("");
        quantity80.setText("");
        subtotal80.setText("");
        
        productName90DD.setValue("");
        quantity90.setText("");
        subtotal90.setText("");
        
        productName100DD1.setValue("");
        quantity100.setText("");
        subtotal100.setText("");
        
        totalAmount.setText("");
        
        if (Runnable.usertType.equalsIgnoreCase("admin")){
            myController.setScreen(Runnable.screen2ID);
        }else{
            myController.setScreen(Runnable.screen1ID);
        } 
        itemNumber = 0;
    }

    @FXML
    private void addItemButtonAction(ActionEvent event) {
        itemNumber = itemNumber + 10;

        switch (itemNumber) {
            case 10:
                item10Pane.setVisible(true);
                break;
            case 20:
                item20Pane.setVisible(true);
                break;
            case 30:
                item30Pane.setVisible(true);
                break;
            case 40:
                item40Pane.setVisible(true);
                break;
            case 50:
                item50Pane.setVisible(true);
                break;
            case 60:
                item60Pane.setVisible(true);
                break;
            case 70:
                item70Pane.setVisible(true);
                break;
            case 80:
                item80Pane.setVisible(true);
                break;
            case 90:
                item90Pane.setVisible(true);
                break;
            case 100:
                item100Pane.setVisible(true);
                break;
        }
    }

    @FXML
    private void quantity10Event(ActionEvent event) {
        ProductDao pd = new ProductDao();
        Product pdDetail = new Product();

        //autopopulation of subtotal based on selected quantity and prodictID
        pdDetail = pd.inventoryCheck((String) productName10DD.getValue());
        if (quantity10.getText().length() > 0) {
            if ((Integer.parseInt(quantity10.getText())) <= pdDetail.getQuantity()) {
                double sub = (pdDetail.getUnitPrice() - (Integer.parseInt(quantity10.getText()) * pdDetail.getUnitPrice()) * (pdDetail.getDiscountPercentage() / 100));
                subtotal10.setText(Double.toString(sub));
                this.orderAmount = this.orderAmount + sub;
                totalAmount.setText(Double.toString(orderAmount));
                //update Inventory after ordering
                pd.updateInventory((String) productName10DD.getValue(), (pdDetail.getQuantity()-Integer.parseInt(quantity10.getText())));
            } else {
                con_mes.alertMessage(Alert.AlertType.ERROR, "Error Message", "Please enter a lower quantity!!");

            }
        } else {
            totalAmount.setText(Double.toString(orderAmount-Double.parseDouble(subtotal10.getText())));
            subtotal10.setText("");            
        }
    }

    @FXML
    private void quantity20ActionEvent(ActionEvent event) {

        ProductDao pd = new ProductDao();
        Product pdDetail = new Product();

        pdDetail = pd.inventoryCheck((String) productName20DD.getValue());
        if (quantity20.getText().length() > 0) {
            if ((Integer.parseInt(quantity20.getText())) <= pdDetail.getQuantity()) {
                double sub = (pdDetail.getUnitPrice() - (Integer.parseInt(quantity20.getText()) * pdDetail.getUnitPrice()) * (pdDetail.getDiscountPercentage() / 100));
                subtotal20.setText(Double.toString(sub));
                this.orderAmount = this.orderAmount + sub;
                totalAmount.setText(Double.toString(orderAmount));
                
                //update Inventory after ordering
                pd.updateInventory((String) productName20DD.getValue(), (pdDetail.getQuantity()-Integer.parseInt(quantity20.getText())));                       
            
            } else {
                con_mes.alertMessage(Alert.AlertType.ERROR, "Error Message", "Please enter a lower quantity!!");
            }
        } else {           
            totalAmount.setText(Double.toString(orderAmount-Double.parseDouble(subtotal20.getText())));
            subtotal20.setText("");
        }
    }

    @FXML
    private void quantity30ActionEvent(ActionEvent event) {

        ProductDao pd = new ProductDao();
        Product pdDetail = new Product();

        pdDetail = pd.inventoryCheck((String) productName30DD.getValue());
        if (quantity30.getText().length() > 0) {
            if ((Integer.parseInt(quantity30.getText())) <= pdDetail.getQuantity()) {
                double sub = (pdDetail.getUnitPrice() - (Integer.parseInt(quantity30.getText()) * pdDetail.getUnitPrice()) * (pdDetail.getDiscountPercentage() / 100));
                subtotal30.setText(Double.toString(sub));
                this.orderAmount = this.orderAmount + sub;
                totalAmount.setText(Double.toString(orderAmount));
                
                //update Inventory after ordering
                pd.updateInventory((String) productName30DD.getValue(), (pdDetail.getQuantity()-Integer.parseInt(quantity30.getText())));
            
            } else {
                con_mes.alertMessage(Alert.AlertType.ERROR, "Error Message", "Please enter a lower quantity!!");

            }
        } else {
            
            totalAmount.setText(Double.toString(orderAmount-Double.parseDouble(subtotal30.getText())));
            subtotal30.setText("");
        }
    }

    @FXML
    private void quantity50ActionEvent(ActionEvent event) {

        ProductDao pd = new ProductDao();
        Product pdDetail = new Product();

        pdDetail = pd.inventoryCheck((String) productName50DD.getValue());
        if (quantity50.getText().length() > 0) {
            if ((Integer.parseInt(quantity50.getText())) <= pdDetail.getQuantity()) {
                Double sub = (pdDetail.getUnitPrice() - (Integer.parseInt(quantity50.getText()) * pdDetail.getUnitPrice()) * (pdDetail.getDiscountPercentage() / 100));
                subtotal50.setText(Double.toString(sub));
                this.orderAmount = this.orderAmount + sub;
                totalAmount.setText(Double.toString(orderAmount));
                
                //update Inventory after ordering
                pd.updateInventory((String) productName50DD.getValue(), (pdDetail.getQuantity()-Integer.parseInt(quantity50.getText())));
            
            } else {
                con_mes.alertMessage(Alert.AlertType.ERROR, "Error Message", "Please enter a lower quantity!!");

            }
        } else {
            
            totalAmount.setText(Double.toString(orderAmount-Double.parseDouble(subtotal50.getText())));
            subtotal50.setText("");
        }

    }

    @FXML
    private void quantity40ActionEvent(ActionEvent event) {

        ProductDao pd = new ProductDao();
        Product pdDetail = new Product();

        pdDetail = pd.inventoryCheck((String) productName40DD.getValue());
        if (quantity40.getText().length() > 0) {
            if ((Integer.parseInt(quantity40.getText())) <= pdDetail.getQuantity()) {
                Double sub = (pdDetail.getUnitPrice() - (Integer.parseInt(quantity40.getText()) * pdDetail.getUnitPrice()) * (pdDetail.getDiscountPercentage() / 100));
                subtotal40.setText(Double.toString(sub));
                this.orderAmount = this.orderAmount + sub;
                totalAmount.setText(Double.toString(orderAmount));
                
                //update Inventory after ordering
                pd.updateInventory((String) productName40DD.getValue(), (pdDetail.getQuantity()-Integer.parseInt(quantity40.getText())));
            
            } else {
                con_mes.alertMessage(Alert.AlertType.ERROR, "Error Message", "Please enter a lower quantity!!");

            }
        } else {
            
            totalAmount.setText(Double.toString(orderAmount-Double.parseDouble(subtotal40.getText())));
            subtotal40.setText("");
        }
    }

    @FXML
    private void quantity60ActionEvent(ActionEvent event) {

        ProductDao pd = new ProductDao();
        Product pdDetail = new Product();

        pdDetail = pd.inventoryCheck((String) productName60DD.getValue());
        if (quantity60.getText().length() > 0) {
            if ((Integer.parseInt(quantity60.getText())) <= pdDetail.getQuantity()) {
                Double sub = (pdDetail.getUnitPrice() - (Integer.parseInt(quantity60.getText()) * pdDetail.getUnitPrice()) * (pdDetail.getDiscountPercentage() / 100));
                subtotal60.setText(Double.toString(sub));
                this.orderAmount = this.orderAmount + sub;
                totalAmount.setText(Double.toString(orderAmount));
                
                //update Inventory after ordering
                pd.updateInventory((String) productName60DD.getValue(), (pdDetail.getQuantity()-Integer.parseInt(quantity60.getText())));
            
            } else {
                con_mes.alertMessage(Alert.AlertType.ERROR, "Error Message", "Please enter a lower quantity!!");

            }
        } else {
            
            totalAmount.setText(Double.toString(orderAmount-Double.parseDouble(subtotal60.getText())));
            subtotal60.setText("");
        }
    }

    @FXML
    private void quantity70ActionEvent(ActionEvent event) {

        ProductDao pd = new ProductDao();
        Product pdDetail = new Product();

        pdDetail = pd.inventoryCheck((String) productName70DD.getValue());
        if (quantity70.getText().length() > 0) {
            if ((Integer.parseInt(quantity70.getText())) <= pdDetail.getQuantity()) {
                Double sub = (pdDetail.getUnitPrice() - (Integer.parseInt(quantity70.getText()) * pdDetail.getUnitPrice()) * (pdDetail.getDiscountPercentage() / 100));
                subtotal70.setText(Double.toString(sub));
                this.orderAmount = this.orderAmount + sub;
                totalAmount.setText(Double.toString(orderAmount));
                
                //update Inventory after ordering
                pd.updateInventory((String) productName70DD.getValue(), (pdDetail.getQuantity()-Integer.parseInt(quantity70.getText())));
            
            } else {
                con_mes.alertMessage(Alert.AlertType.ERROR, "Error Message", "Please enter a lower quantity!!");

            }
        } else {
            totalAmount.setText(Double.toString(orderAmount-Double.parseDouble(subtotal70.getText())));
            subtotal70.setText("");
        }
    }

    @FXML
    private void quantity80ActionEvent(ActionEvent event) {

        ProductDao pd = new ProductDao();
        Product pdDetail = new Product();

        pdDetail = pd.inventoryCheck((String) productName80DD.getValue());
        if (quantity80.getText().length() > 0) {
            if ((Integer.parseInt(quantity80.getText())) <= pdDetail.getQuantity()) {
                Double sub = (pdDetail.getUnitPrice() - (Integer.parseInt(quantity80.getText()) * pdDetail.getUnitPrice()) * (pdDetail.getDiscountPercentage() / 100));
                subtotal80.setText(Double.toString(sub));
                this.orderAmount = this.orderAmount + sub;
                totalAmount.setText(Double.toString(orderAmount));
                
                //update Inventory after ordering
                pd.updateInventory((String) productName80DD.getValue(), (pdDetail.getQuantity()-Integer.parseInt(quantity80.getText())));
            
            } else {
                con_mes.alertMessage(Alert.AlertType.ERROR, "Error Message", "Please enter a lower quantity!!");

            }
        } else {
            
            totalAmount.setText(Double.toString(orderAmount-Double.parseDouble(subtotal80.getText())));
            subtotal80.setText("");
        }
    }

    @FXML
    private void quantity90ActionEvent(ActionEvent event) {

        ProductDao pd = new ProductDao();
        Product pdDetail = new Product();

        pdDetail = pd.inventoryCheck((String) productName90DD.getValue());
        if (quantity90.getText().length() > 0) {
            if ((Integer.parseInt(quantity90.getText())) <= pdDetail.getQuantity()) {
                Double sub = (pdDetail.getUnitPrice() - (Integer.parseInt(quantity90.getText()) * pdDetail.getUnitPrice()) * (pdDetail.getDiscountPercentage() / 100));
                subtotal90.setText(Double.toString(sub));
                this.orderAmount = this.orderAmount + sub;
                totalAmount.setText(Double.toString(orderAmount));
                
                //update Inventory after ordering
                pd.updateInventory((String) productName90DD.getValue(), (pdDetail.getQuantity()-Integer.parseInt(quantity90.getText())));
            
            } else {
                con_mes.alertMessage(Alert.AlertType.ERROR, "Error Message", "Please enter a lower quantity!!");

            }
        } else {
            
            totalAmount.setText(Double.toString(orderAmount-Double.parseDouble(subtotal90.getText())));
            subtotal90.setText("");
        }
    }

    @FXML
    private void quantity100ActionEvent(ActionEvent event) {

        ProductDao pd = new ProductDao();
        Product pdDetail = new Product();

        pdDetail = pd.inventoryCheck((String) productName100DD1.getValue());
        if (quantity100.getText().length() > 0) {
            if ((Integer.parseInt(quantity100.getText())) <= pdDetail.getQuantity()) {
                Double sub = (pdDetail.getUnitPrice() - (Integer.parseInt(quantity100.getText()) * pdDetail.getUnitPrice()) * (pdDetail.getDiscountPercentage() / 100));
                subtotal100.setText(Double.toString(sub));
                this.orderAmount = this.orderAmount + sub;
                totalAmount.setText(Double.toString(orderAmount));
                
                //update Inventory after ordering
                pd.updateInventory((String) productName100DD1.getValue(), (pdDetail.getQuantity()-Integer.parseInt(quantity100.getText())));
            
            } else {
                con_mes.alertMessage(Alert.AlertType.ERROR, "Error Message", "Please enter a lower quantity!!");
            }
        } else { 
            totalAmount.setText(Double.toString(orderAmount-Double.parseDouble(subtotal100.getText())));
            subtotal100.setText("");
        }
    }
}
