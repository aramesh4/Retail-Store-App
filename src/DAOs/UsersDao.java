/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import Models.*;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javafx.scene.control.Alert;

/**
 *
 * @author Adarsh
 */
public class UsersDao {

    //Create connection class object whihc will be used in all the methods
    private Messages con_mes = new Messages();

    public Users verifyLogin(String username, String password) {

        String logintype = null;
        Users user = null;

        if (!username.isEmpty() && !password.isEmpty()) {
            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://www.papademas.net:3306/dbfp", "fpuser", "510");
                java.sql.Statement st = con.createStatement();
                String query = "select * from a20361171_users where username='" + username + "' AND " + "userpassword='" + password + "';";
                ResultSet rs = st.executeQuery(query);
                if (rs.next()) {
                    user = new Users();
                    user.setUserID(Integer.parseInt(rs.getString("userid")));
                    user.setUsertype(rs.getString("usertype"));
                    user.setFullname(rs.getString("fullname"));
                    user.setUsertype(rs.getString("usertype"));

                } else {
                    con_mes.alertMessage(Alert.AlertType.ERROR, "Login Failed", "Please enter valid credentials!!");
                }
                con.close();
            } catch (Exception e) {
                con_mes.alertMessage(Alert.AlertType.ERROR, "Login Failed", "Connectio error!!! please try again later");
            }
        } else {
            con_mes.alertMessage(Alert.AlertType.ERROR, "Login Failed", "Please enter valid credentials!!");
        }
        return user;
    }

    public Users createUser(Users usersRef) {

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://www.papademas.net:3306/dbfp", "fpuser", "510");
            //java.sql.Statement st = con.createStatement();

            java.util.Date date = Calendar.getInstance().getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

            //Query to insert a record to the bank table
            String query = "INSERT INTO a20361171_users (username, userpassword, usertype, fullname, emailid, phonenumber, address, createdon, lastupdatedon) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ;";

            //Use prepared statements to avoid SQL injection attacks
            try (PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                //Set  the parameters to the query
                statement.setString(1, usersRef.getUsername());
                statement.setString(2, usersRef.getUserpassword());
                statement.setString(3, usersRef.getUsertype());
                statement.setString(4, usersRef.getFullname());
                statement.setString(5, usersRef.getEmailid());
                statement.setString(6, usersRef.getPhonenumber());
                statement.setString(7, usersRef.getAddress());
                statement.setString(8, sdf.format(date));
                statement.setString(9, sdf.format(date));
                //Execute the insert
                statement.executeUpdate();
                //To get the primary key (id) of the newly inserted record
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    //Set the id field of the database to the model
                    usersRef.setUserID(resultSet.getInt(1));
                } else {
                    con_mes.alertMessage(Alert.AlertType.ERROR, "Error Message", "Error while creating user");
                }
                con.close();
                con = null;
            } catch (SQLException e) {
                usersRef = null;
                con_mes.alertMessage(Alert.AlertType.ERROR, "Error Message", "Error while creating user");
            }

        } catch (SQLException e) {
            con_mes.alertMessage(Alert.AlertType.ERROR, "Login Failed", "Connectio error!!! please try again later");
        }

        return usersRef;
    }

    public Users searchUser(int userID) {
        Users searchRes = new Users();
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://www.papademas.net:3306/dbfp", "fpuser", "510");
            java.sql.Statement st = con.createStatement();
            String query = "select * from a20361171_users where userid = '" + userID + "';";

            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                searchRes.setUserID(userID);
                searchRes.setAddress(rs.getString("address"));
                searchRes.setEmailid(rs.getString("emailid"));
                searchRes.setFullname(rs.getString("fullname"));
                searchRes.setPhonenumber(rs.getString("phonenumber"));
                searchRes.setUsername(rs.getString("username"));
                searchRes.setUserpassword(rs.getString("userpassword"));
                searchRes.setUsertype(rs.getString("usertype"));
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

    public void deleteUser(int userid) {

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://www.papademas.net:3306/dbfp", "fpuser", "510");
            java.sql.Statement st = con.createStatement();

            //Query to insert a record to the bank table
            String query = "DELETE FROM a20361171_users WHERE userid = '" + userid + "';";

            st.executeUpdate(query);
            con_mes.alertMessage(Alert.AlertType.INFORMATION, "Information Message", "User with userid " + userid + " deleted successfully");

            con.close();
            con = null;

        } catch (SQLException e) {
            con_mes.alertMessage(Alert.AlertType.ERROR, "Connection failed", "Connectio error!!! please try again later");
        }
    }

    public void updateUser(Users userVal) {

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://www.papademas.net:3306/dbfp", "fpuser", "510");

            java.util.Date date = Calendar.getInstance().getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

            //Query to insert a record to the bank table
            String query = "UPDATE a20361171_users SET username=? , userpassword=? , usertype=? , fullname=? , emailid=? , phonenumber=? , address=? , lastupdatedon=? WHERE userid = ?";

            //Use prepared statements to avoid SQL injection attacks
            try (PreparedStatement statement = con.prepareStatement(query)) {
                //Set  the parameters to the query
                statement.setString(1, userVal.getUsername());
                statement.setString(2, userVal.getUserpassword());
                statement.setString(3, userVal.getUsertype());
                statement.setString(4, userVal.getFullname());
                statement.setString(5, userVal.getEmailid());
                statement.setString(6, userVal.getPhonenumber());
                statement.setString(7, userVal.getAddress());
                statement.setString(8, sdf.format(date));
                statement.setInt(9, userVal.getUserID());

                //Execute the insert
                statement.executeUpdate();
                con_mes.alertMessage(Alert.AlertType.INFORMATION, "Information Message", "User with userid " + userVal.getUserID() + " updated successfully");

                con.close();
                con = null;
            } catch (SQLException e) {
                con_mes.alertMessage(Alert.AlertType.ERROR, "Error Message", "Error while updating the user!!");
            }
        } catch (SQLException e) {
            con_mes.alertMessage(Alert.AlertType.ERROR, "Connection failed", "Connectio error!!! please try again later");
        }
    }

    public ArrayList getAllUsers() {

        ArrayList<Integer> usersList = new ArrayList<Integer>();

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://www.papademas.net:3306/dbfp", "fpuser", "510");
            java.sql.Statement st = con.createStatement();
            String query = "select * from a20361171_users;";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt(1);
                usersList.add(id);
            }
        } catch (Exception e) {
            con_mes.alertMessage(Alert.AlertType.ERROR, "Error Message", "Error while fetching UserID's!!");
        }
        return usersList;
    }
}
