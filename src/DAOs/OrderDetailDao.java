/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Models.OrdersDetails;
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
public class OrderDetailDao {

    //Create connection class object whihc will be used in all the methods
    private Messages con_mes = new Messages();

    public void createOrderDetail(ArrayList<OrdersDetails> orderDetail) {

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://www.papademas.net:3306/dbfp", "fpuser", "510");

            for (OrdersDetails details : orderDetail) {

                //Query to insert a record to the bank table
                String query = "INSERT INTO a20361171_orders_detail (orderID, orderItem, productID, itemQuantity, subtotal) "
                        + "VALUES (?, ?, ?, ?, ?) ;";

                //Use prepared statements to avoid SQL injection attacks
                try (PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                    //Set  the parameters to the query
                    statement.setInt(1, details.getOrderID());
                    statement.setInt(2, details.getOrderItem());
                    statement.setString(3, details.getProductID());
                    statement.setInt(4, details.getItemQuantity());
                    statement.setDouble(5, details.getSubtotal());

                    //Execute the insert
                    statement.executeUpdate();
                    //To get the primary key (id) of the newly inserted record
                    ResultSet resultSet = statement.getGeneratedKeys();

                    con.close();
                    con = null;
                } catch (SQLException e) {

                    con_mes.alertMessage(Alert.AlertType.ERROR, "Error Message", "Error while creating user");
                }

            }
        } catch (SQLException e) {
            con_mes.alertMessage(Alert.AlertType.ERROR, "Login Failed", "Connectio error!!! please try again later");
        }
    }
}
