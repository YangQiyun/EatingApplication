package com.seu.srtp.data;

/**
 * Created by swallowman on 2017/3/16.
 */

public class User {
    public int _id;
    public int account;
    public String password;
    public User() {
    }
    public void set_id(int id){
        this._id=id;
    }
    public void setAccount(int account){
        this.account=account;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public User(int account, String password) {
        this.account = account;
        this.password = password;
    }
}
