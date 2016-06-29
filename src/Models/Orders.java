/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Date;

/**
 *
 * @author Adarsh
 */
public class Orders {

    private int orderID;
    private int userid;
    private Date createdOn;
    private String status;
    private String storeID;
    private Double orderAmount;
    private String billingAddress;

//    public Orders(int orderID, int userid, Date createdOn, String status, String storeID, Double orderAmount, String billingAmount){
//        this.orderID = orderID;
//        this.userid = userid;
//        this.createdOn = createdOn;
//        this.status = status;
//        this.storeID = storeID;
//        this.orderAmount = orderAmount;
//        this.billingAddress = billingAmount;
//    }
    public Orders() {
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStoreID() {
        return storeID;
    }

    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }
}
