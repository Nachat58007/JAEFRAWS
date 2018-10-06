package com.jaefraws.jaefraws.Model;

import com.jaefraws.jaefraws.Uprofile;

import java.util.HashMap;
import java.util.Map;

public class User {
   public String name;
   public String email;
   public String phone;
   public String id;
   public String status;
   public String wname;

public User(){

}

    public User(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.id = id;
        this.status = status;
        this.wname = wname;

}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWname() {
        return wname;
    }

    public void setWname(String wname) {
        this.wname = wname;
    }
}




