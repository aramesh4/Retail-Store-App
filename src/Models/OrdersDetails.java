/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Adarsh
 */
public class OrdersDetails {

    private int orderID;
    private int orderItem;
    private String productID;
    private int itemQuantity;
    private Double subtotal;

    public OrdersDetails(int orderID, int orderItem, String productID, int itemQuantity, double subtotal) {
        this.orderID = orderID;
        this.orderItem = orderItem;
        this.productID = productID;
        this.itemQuantity = itemQuantity;
        this.subtotal = subtotal;
    }
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(int orderItem) {
        this.orderItem = orderItem;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
}
