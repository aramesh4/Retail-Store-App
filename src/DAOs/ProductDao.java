/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Models.InventoryTableModel;
import Models.Product;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 *
 * @author Adarsh
 */
public class ProductDao {

    //Create connection class object whihc will be used in all the methods
    private Messages con_mes = new Messages();

    public void addProduct(Product addProduct) {

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://www.papademas.net:3306/dbfp", "fpuser", "510");

            //Query to insert a record to the bank table
            String query = "INSERT INTO a20361171_product (productID, productName, ProductDesc, createdBy, quantity, unitPrice, expiryDate, discountPercentage )" + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

            //Use prepared statements to avoid SQL injection attacks
            try (PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                //Set  the parameters to the query
                statement.setString(1, addProduct.getProductID());
                statement.setString(2, addProduct.getProductName());
                statement.setString(3, addProduct.getProductDesc());
                statement.setInt(4, addProduct.getCreatedBy());
                statement.setDouble(5, addProduct.getQuantity());
                statement.setDouble(6, addProduct.getUnitPrice());
                statement.setDate(7, (Date) addProduct.getExpiryDate());
                statement.setDouble(8, addProduct.getDiscountPercentage());

                //Execute the insert
                statement.executeUpdate();
                con_mes.alertMessage(Alert.AlertType.INFORMATION, "Information Message", "New product created successfully!!");

                con.close();
                con = null;
            } catch (SQLException e) {
                con_mes.alertMessage(Alert.AlertType.ERROR, "Error Message", "Error while creating product");
            }
        } catch (SQLException e) {
            con_mes.alertMessage(Alert.AlertType.ERROR, "Login Failed", "Connectio error!!! please try again later");
        }
    }

    public ArrayList getAllProductID() {

        ArrayList<String> productIDList = new ArrayList<>();

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://www.papademas.net:3306/dbfp", "fpuser", "510");
            java.sql.Statement st = con.createStatement();
            String query = "select productID from a20361171_product;";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String id = rs.getString(1);
                productIDList.add(id);
            }
            con.close();
            con = null;
        } catch (Exception e) {
            con_mes.alertMessage(Alert.AlertType.ERROR, "Error Message", "Error while fetching UserID's!!");
        }
        return productIDList;
    }

    public void deleteProduct(String productID) {

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://www.papademas.net:3306/dbfp", "fpuser", "510");

            //Query to insert a record to the bank table
            String query = "DELETE FROM a20361171_product WHERE productID = ?;";

            //Use prepared statements to avoid SQL injection attacks
            try (PreparedStatement statement = con.prepareStatement(query)) {
                //Set  the parameters to the query
                statement.setString(1, productID);

                //Execute the insert
                statement.executeUpdate();

                con_mes.alertMessage(Alert.AlertType.INFORMATION, "Information Message", "Product with productid " + productID + " deleted successfully");
                con.close();
                con = null;
            } catch (SQLException e) {

                con_mes.alertMessage(Alert.AlertType.ERROR, "Error Message", "Error while deleting the user!!");
            }
        } catch (SQLException e) {
            con_mes.alertMessage(Alert.AlertType.ERROR, "Connection failed", "Connectio error!!! please try again later");
        }
    }

    public void updateProduct(Product updateValues) {

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://www.papademas.net:3306/dbfp", "fpuser", "510");

            //Query to insert a record to the bank table
            String query = "UPDATE a20361171_product SET productName=? , productDesc=? , quantity=? , unitPrice=? , expiryDate=? , discountPercentage=? WHERE productID = ?";

            //Use prepared statements to avoid SQL injection attacks
            try (PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                //Set  the parameters to the query

                statement.setString(1, updateValues.getProductName());
                statement.setString(2, updateValues.getProductDesc());
                statement.setInt(3, updateValues.getQuantity());
                statement.setDouble(4, updateValues.getUnitPrice());
                statement.setDate(5, (Date) updateValues.getExpiryDate());
                statement.setDouble(6, updateValues.getDiscountPercentage());
                statement.setString(7, updateValues.getProductID());

                //Execute the insert
                statement.executeUpdate();
                con_mes.alertMessage(Alert.AlertType.INFORMATION, "Information Message", "Product with productID " + updateValues.getProductID() + " updated successfully");

                con.close();
                con = null;
            } catch (SQLException e) {
                con_mes.alertMessage(Alert.AlertType.ERROR, "Error Message", "Error while updating the user!!");
            }
        } catch (SQLException e) {
            con_mes.alertMessage(Alert.AlertType.ERROR, "Connection failed", "Connectio error!!! please try again later");
        }
    }

    public ObservableList<InventoryTableModel> getInventory() {

        List<InventoryTableModel> productInventory = new ArrayList<InventoryTableModel>();//FXCollections.observableArrayList();

        ObservableList<InventoryTableModel> productList = FXCollections.observableArrayList(productInventory);

        ResultSet rs = null;
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://www.papademas.net:3306/dbfp", "fpuser", "510");
            java.sql.Statement st = con.createStatement();
            String query = "select productID,productName,productDesc,quantity from a20361171_product;";
            rs = st.executeQuery(query);
            while (rs.next()) {
                InventoryTableModel data = new InventoryTableModel();
                data.setProductID(rs.getString("productID"));
                data.setProductName(rs.getString("productName"));
                data.setProductDesc(rs.getString("productDesc"));
                data.setAvailableQuantity(rs.getInt("quantity"));
                productInventory.add(data);
            }
            productList.setAll(productInventory);
            con.close();
            con = null;
        } catch (Exception e) {
            System.out.println(e);
        }
        return productList;
    }

    public Product inventoryCheck(String productID) {
        Product pd = null;
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://www.papademas.net:3306/dbfp", "fpuser", "510");
            java.sql.Statement st = con.createStatement();
            String query = "select * from a20361171_product where productID='" + productID + "';";
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                pd = new Product();
                pd.setProductID(rs.getString(1));
                pd.setQuantity(rs.getInt(5));
                pd.setUnitPrice(rs.getDouble(6));
                pd.setDiscountPercentage(rs.getDouble(8));
            }
            con.close();
            con = null;
        } catch (Exception e) {
            con_mes.alertMessage(Alert.AlertType.ERROR, "Connection failed", "Connectio error!!! please try again later");
        }
        return pd;
    }

    public void updateInventory(String productID, int quantity) {

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://www.papademas.net:3306/dbfp", "fpuser", "510");
            java.sql.Statement st = con.createStatement();
            String query = "UPDATE a20361171_product SET quantity = ? WHERE productID = ?";

            try (PreparedStatement statement = con.prepareStatement(query)) {
                //Set  the parameters to the query

                statement.setInt(1, quantity);
                statement.setString(2, productID);

                statement.executeUpdate();
                con.close();
                con = null;
            } catch (Exception e) {
                con_mes.alertMessage(Alert.AlertType.ERROR, "Connection failed", "Connectio error!!! please try again later");
            }
        } catch (Exception e) {
            con_mes.alertMessage(Alert.AlertType.ERROR, "Connection failed", "Connectio error!!! please try again later");
        }
    }

    public Product searchProduct(String productID) {
        Product searchRes = new Product();
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://www.papademas.net:3306/dbfp", "fpuser", "510");
            java.sql.Statement st = con.createStatement();
            String query = "select * from a20361171_product where productID = '" + productID + "';";

            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {
                searchRes.setProductID(rs.getString("productID"));
                searchRes.setProductName(rs.getString("productName"));
                searchRes.setProductDesc(rs.getString("productDesc"));
                searchRes.setCreatedBy(rs.getInt("createdBy"));
                searchRes.setQuantity(rs.getInt("quantity"));
                searchRes.setUnitPrice(rs.getDouble("unitPrice"));
                searchRes.setExpiryDate(rs.getDate("expiryDate"));
                searchRes.setDiscountPercentage(rs.getDouble("discountPercentage"));
            } else {

                con_mes.alertMessage(Alert.AlertType.ERROR, "Error Message", "Record not found!!!");
            }
            con.close();
            con = null;
            return searchRes;
        } catch (Exception e) {
            con_mes.alertMessage(Alert.AlertType.ERROR, "Error Message", "Connection failure!!");
        }
        return searchRes;
    }
}
