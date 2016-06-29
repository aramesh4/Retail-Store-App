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
public class Users {

    private int userid;

    private String username;

    private String userpassword;

    private String usertype;

    private String fullname;

    private String emailid;

    private String phonenumber;

    private String address;

    private Date createdon;

    private Date lastupdatedon;

    public Users(String username, String userpassword, String usertype, String fullname, String emailid, String phonenumber, String address, Date createdon, Date lastupdatedon) {
//        this.userid = userid;
        this.username = username;
        this.userpassword = userpassword;
        this.usertype = usertype;
        this.fullname = fullname;
        this.emailid = emailid;
        this.phonenumber = phonenumber;
        this.address = address;
        this.createdon = createdon;
        this.lastupdatedon = lastupdatedon;
    }

    public Users() {

    }

    public int getUserID() {
        return userid;
    }

    public void setUserID(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreatedon() {
        return createdon;
    }

    public void setCreatedon(Date createdon) {
        this.createdon = createdon;
    }

    public Date getLastupdatedon() {
        return lastupdatedon;
    }

    public void setLastupdatedon(Date lastupdatedon) {
        this.lastupdatedon = lastupdatedon;
    }
}
