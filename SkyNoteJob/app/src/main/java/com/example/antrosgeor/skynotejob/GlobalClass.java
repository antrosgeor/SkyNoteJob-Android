package com.example.antrosgeor.skynotejob;

import android.app.Application;

public class GlobalClass extends Application{
/** Values **/
    private String  User_first, User_last, User_email, User_password, User_avatar, User_mphone,
                    User_wphone, User_address, User_status, User_type_job, User_name_job;
    private int     User_id;

/** Start static values **/
    public static final String Company_Name = "Company Name";
    public static final String Localhost =  "https://internaljob.herokuapp.com/"; //"http://192.168.1.4/";
    public static final String Destination = "Rest/"; //"antros/Papei/My/AAA/AA_81/Rest/";
    public static final String Destination_images = "";
    public static final String Image_Admin_File = "uploads/";
    public static final String Image_Member_File = "uploads_member/";
    public static final String Image_Contacts_File = "uploads_contact/";
/** final Url **/

    public static final String login_url = Localhost+Destination+"login.php";

    public static final String LH_Destination = Localhost+Destination;
    public static final String LH_Destination_images = Localhost+Destination_images;

/** Stop static values **/

/** Start User data from Login **/
    // id User
    public int getUser_id() { return User_id; }
    public void setUser_id(int aUser_id) { User_id = aUser_id; }
    // first User
    public String getUser_first() { return User_first; }
    public void setUser_first(String aUser_first) { User_first = aUser_first; }
    // last User
    public String getUser_last() { return User_last; }
    public void setUser_last(String aUser_last) { User_last = aUser_last; }
    // email User
    public String getUser_email() { return User_email; }
    public void setUser_email(String aUser_email) { User_email  = aUser_email; }
    // password User
    public String getUser_password() { return  User_password; }
    public void setUser_password(String aUser_password) { User_password = aUser_password; }
    // status User
    public String getUser_status() { return  User_status; }
    public void setUser_status(String aUser_status) {User_status  = aUser_status; }
    // type job User
    public String getUser_type_job() { return  User_type_job; }
    public void setUser_type_job(String aUser_type_job) { User_type_job = aUser_type_job; }
    // name job User
    public String getUser_name_job() { return  User_name_job; }
    public void setUser_name_job(String aUser_name_job) { User_name_job = aUser_name_job; }
    // avatar User
    public String getUser_avatar() { return  User_avatar; }
    public void setUser_avatar(String aUser_avatar) { User_avatar  = aUser_avatar; }
    // mobile phone User
    public String getUser_mphone() { return   User_mphone; }
    public void setUser_mphone(String aUser_mphone) { User_mphone = aUser_mphone; }
    // word phone User
    public String getUser_wphone() { return  User_wphone; }
    public void setUser_wphone(String aUser_wphone) { User_wphone = aUser_wphone; }
    // address home User
    public String getUser_address() { return User_address; }
    public void setUser_address(String aUser_address) { User_address = aUser_address; }
/** Stop User data from Login **/
}