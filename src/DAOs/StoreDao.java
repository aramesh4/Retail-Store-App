/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Models.Store;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.scene.control.Alert;

/**
 *
 * @author Adarsh
 */
public class StoreDao {

    //Create connection class object whihc will be used in all the methods
    private Messages con_mes = new Messages();

    public ArrayList getAllStore() {

        ArrayList<String> storeIDList = new ArrayList<>();

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://www.papademas.net:3306/dbfp", "fpuser", "510");
            java.sql.Statement st = con.createStatement();
            String query = "select storeID from a20361171_store;";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String id = rs.getString(1);
                storeIDList.add(id);
            }
        } catch (Exception e) {
            con_mes.alertMessage(Alert.AlertType.ERROR, "Error Message", "Error while fetching StoreID's!!");
        }
        return storeIDList;
    }

    public void addStore(Store storeDetail) {

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://www.papademas.net:3306/dbfp", "fpuser", "510");

            //Query to insert a record to the bank table
            String query = "INSERT INTO a20361171_store (storeID, storeName, location, phonenumber, emailID )" + "VALUES (?, ?, ?, ?, ?);";

            //Use prepared statements to avoid SQL injection attacks
            try (PreparedStatement statement = con.prepareStatement(query)) {
                //Set  the parameters to the query
                statement.setString(1, storeDetail.getStoreID());
                statement.setString(2, storeDetail.getStoreName());
                statement.setString(3, storeDetail.getLocation());
                statement.setString(4, storeDetail.getPhonenumber());
                statement.setString(5, storeDetail.getEmialID());

                //Execute the insert
                statement.executeUpdate();
                con_mes.alertMessage(Alert.AlertType.INFORMATION, "Information Message", "New Store created successfully!!");

                con.close();
                con = null;
            } catch (SQLException e) {
                con_mes.alertMessage(Alert.AlertType.ERROR, "Error Message", "Error while creating product");
            }

        } catch (SQLException e) {
            con_mes.alertMessage(Alert.AlertType.ERROR, "Login Failed", "Connectio error!!! please try again later");
        }
    }

    public void deleteStore(String storeID) {

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://www.papademas.net:3306/dbfp", "fpuser", "510");

            //Query to insert a record to the bank table
            String query = "DELETE FROM a20361171_store WHERE storeID = ?;";

            //Use prepared statements to avoid SQL injection attacks
            try (PreparedStatement statement = con.prepareStatement(query)) {
                //Set  the parameters to the query
                statement.setString(1, storeID);
                //Execute the insert
                statement.executeUpdate();

                con_mes.alertMessage(Alert.AlertType.INFORMATION, "Information Message", "Store with storeid " + storeID + " deleted successfully");

                con.close();
                con = null;
            } catch (SQLException e) {
                con_mes.alertMessage(Alert.AlertType.ERROR, "Error Message", "Error while deleting the user!!");
            }
        } catch (SQLException e) {
            con_mes.alertMessage(Alert.AlertType.ERROR, "Connection failed", "Connectio error!!! please try again later");
        }
    }

    public void updateStore(Store updateValues) {

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://www.papademas.net:3306/dbfp", "fpuser", "510");
            //java.sql.Statement st = con.createStatement();

            //Query to insert a record to the bank table
            String query = "UPDATE a20361171_store SET storeName=? , location=? , phonenumber=? , emailID=?";

            //Use prepared statements to avoid SQL injection attacks
            try (PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                //Set  the parameters to the query

                statement.setString(1, updateValues.getStoreName());
                statement.setString(2, updateValues.getLocation());
                statement.setString(3, updateValues.getPhonenumber());
                statement.setString(4, updateValues.getEmialID());

                //Execute the insert
                statement.executeUpdate();

                con_mes.alertMessage(Alert.AlertType.INFORMATION, "Information Message", "Store with storeID " + updateValues.getStoreID() + " updated successfully");

                con.close();
                con = null;
            } catch (SQLException e) {

                con_mes.alertMessage(Alert.AlertType.ERROR, "Error Message", "Error while updating the user!!");
            }

        } catch (SQLException e) {
            con_mes.alertMessage(Alert.AlertType.ERROR, "Connection failed", "Connectio error!!! please try again later");
        }
    }

    public Store searchStore(String storeID) {
        Store searchRes = new Store();
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://www.papademas.net:3306/dbfp", "fpuser", "510");
            java.sql.Statement st = con.createStatement();
            String query = "select * from a20361171_store where storeID = '" + storeID + "';";

            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {
                searchRes.setStoreID(rs.getString("storeID"));
                searchRes.setStoreName(rs.getString("storeName"));
                searchRes.setLocation(rs.getString("location"));
                searchRes.setPhonenumber(rs.getString("phonenumber"));
                searchRes.setEmialID(rs.getString("emailID"));

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
