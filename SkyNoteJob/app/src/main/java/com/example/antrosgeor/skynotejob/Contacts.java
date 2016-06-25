package com.example.antrosgeor.skynotejob;

/**
 *  afto gia na to stilo.
 *
 *  Contacts contacts = new Contacts(id,title,forto);
 contactAdapter.add(contacts);
 *
 *
 * Created by ANTROS on 11/3/2016.
 */
public class Contacts {
    private String name,email,mobile, status;
    String User_id;

    public Contacts(String name,String email,String mobile,String status)
    {
        this.setName(name);
        this.setEmail(email);
        this.setMobile(mobile);
        this.setStatus(status);
    }
    //name
    public  String getName() {
        return name;
    }
    public void setName(String name) {
        this.name =  name;
    }
    //mobile
    public  String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile =  mobile;
    }
    //email
    public  String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email =  email;
    }

    public  String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status =  status;
    }



    public String getUser_id() { return User_id; }
    public void setUser_id(String aUser_id) { User_id = aUser_id; }
}
