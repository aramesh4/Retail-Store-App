/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Models.Orders;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javafx.scene.control.Alert;

/**
 *
 * @author Adarsh
 */
public class OrdersDao {

    //Create connection class object whihc will be used in all the methods
    private Messages con_mes = new Messages();

    public Orders saveOrder(Orders orderData) {

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://www.papademas.net:3306/dbfp", "fpuser", "510");

            java.util.Date date = Calendar.getInstance().getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

            //Query to insert a record to the bank table
            String query = "INSERT INTO a20361171_orders (userid, createdOn, status, storeID, orderAmount) "
                    + "VALUES (?, ?, ?, ?, ?) ;";

            //Use prepared statements to avoid SQL injection attacks
            try (PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                //Set  the parameters to the query
                statement.setInt(1, orderData.getUserid());
                statement.setString(2, sdf.format(date));
                statement.setString(3, "Processed");
                statement.setString(4, orderData.getStoreID());
                statement.setDouble(5, orderData.getOrderAmount());

                //Execute the insert
                statement.executeUpdate();
                //To get the primary key (id) of the newly inserted record
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    //Set the id field of the database to the model
                    orderData.setUserid(resultSet.getInt(1));
                    con_mes.alertMessage(Alert.AlertType.INFORMATION, "Information Message", "Order created successfully");

                } else {
                    con_mes.alertMessage(Alert.AlertType.ERROR, "Error Message", "Error while creating user");
                }
                con.close();
                con = null;
            } catch (SQLException e) {

                con_mes.alertMessage(Alert.AlertType.ERROR, "Error Message", "Error while creating user");
            }

        } catch (SQLException e) {
            con_mes.alertMessage(Alert.AlertType.ERROR, "Login Failed", "Connectio error!!! please try again later");
        }
        return orderData;
    }
}
