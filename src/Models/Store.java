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
public class Store {

    private String storeID;
    private String storeName;
    private String location;
    private String phonenumber;
    private String emialID;

    public void Store(String storeID, String storeName, String location, String phonenumber, String emailID) {
        this.storeID = storeID;
        this.storeName = storeName;
        this.location = location;
        this.phonenumber = phonenumber;
        this.emialID = emailID;
    }

    public String getStoreID() {
        return storeID;
    }

    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmialID() {
        return emialID;
    }

    public void setEmialID(String emialID) {
        this.emialID = emialID;
    }
}
