package com.example.antrosgeor.skynotejob;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/** Created by ANTROS on 21/3/2016. */

public class BackgroundTask extends AsyncTask<String,Void,String> {
/** Values */
    String  json_string, login_pass, login_email, First, Last, Email, Pass, Address, Id_Note, Member_id, Mobile, Word, id,
            Notes, json_url, Title_Mail_No_User, Type, Edit_email, Edit_First, Edit_Last, Edit_Phone, Edit_Message, method,
            my_Id, comment, selectTitle, data, selectBody, selectLever, selectColor, selectDate,selectTime, My_send;
    String  JSON_STRING = null, mail = "", response = "", line  = "";
    JSONObject jsonObject;
    JSONArray jsonArray;
    String login_url    = GlobalClass.login_url;
    String LH_Destination = GlobalClass.LH_Destination;
    String Url_mail     = LH_Destination+"?key=member&mail=";
    String URL_Insert   = LH_Destination+"insert.php";
    String URL_Delete   = LH_Destination+"delete.php";
    Bundle extras;
    URL url;
    Intent intent;

    AlertDialog alertDialog;
    // edo exoume tin metafora ton dedomenon apo to register.java
    Context ctx;
    BackgroundTask(Context ctx)
    {
        this.ctx =ctx;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("Login Information....");
    }

    @Override
    protected String doInBackground(String... params) {
        // edo pernoume se String tin timi method apo to register.java
        method = params[0];
        if (method.equals("login"))
        {
            login_email = params[1];
            login_pass  = params[2];
            mail        = login_email;
            try {
                url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                data = URLEncoder.encode("login_email","UTF-8")+"="+URLEncoder.encode(login_email,"UTF-8")+"&"+
                              URLEncoder.encode("login_pass","UTF-8")+"="+URLEncoder.encode(login_pass,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                response = "";
                line  = "";
                while ((line = bufferedReader.readLine())!=null)
                {
                    response+= line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (method.equals("update")) {
            First   = params[1];
            Last    = params[2];
            Email   = params[3];
            Pass    = params[4];
            Address = params[5];
            Mobile  = params[6];
            Word    = params[7];
            id      = params[8];

            try {
                url = new URL(URL_Insert);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                data = URLEncoder.encode("method","UTF-8")+"="+URLEncoder.encode(method,"UTF-8")+"&"+
                        URLEncoder.encode("change_email","UTF-8")+"="+URLEncoder.encode(Email,"UTF-8")+"&"+
                        URLEncoder.encode("change_pass","UTF-8")+"="+URLEncoder.encode(Pass,"UTF-8")+"&"+
                        URLEncoder.encode("change_first","UTF-8")+"="+URLEncoder.encode(First,"UTF-8")+"&"+
                        URLEncoder.encode("change_last","UTF-8")+"="+URLEncoder.encode(Last,"UTF-8")+"&"+
                        URLEncoder.encode("change_mobile","UTF-8")+"="+URLEncoder.encode(Mobile,"UTF-8")+"&"+
                        URLEncoder.encode("change_word","UTF-8")+"="+URLEncoder.encode(Word,"UTF-8")+"&"+
                        URLEncoder.encode("change_address","UTF-8")+"="+URLEncoder.encode(Address,"UTF-8")+"&"+
                        URLEncoder.encode("change_id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                response = "";
                line  = "";
                while ((line = bufferedReader.readLine())!=null)
                {
                    response+= line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (method.equals("Contact_Add")){
            First   = params[1];
            Last    = params[2];
            Email   = params[3];
            Notes   = params[4];
            Address = params[5];
            Mobile  = params[6];
            Word    = params[7];
            id      = params[8];

            try {
                url = new URL(URL_Insert);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                data = URLEncoder.encode("method","UTF-8")+"="+URLEncoder.encode(method,"UTF-8")+"&"+
                        URLEncoder.encode("Add_First","UTF-8")+"="+URLEncoder.encode(First,"UTF-8")+"&"+
                        URLEncoder.encode("Add_Last","UTF-8")+"="+URLEncoder.encode(Last,"UTF-8")+"&"+
                        URLEncoder.encode("Add_Email","UTF-8")+"="+URLEncoder.encode(Email,"UTF-8")+"&"+
                        URLEncoder.encode("Add_Notes","UTF-8")+"="+URLEncoder.encode(Notes,"UTF-8")+"&"+
                        URLEncoder.encode("Add_Address","UTF-8")+"="+URLEncoder.encode(Address,"UTF-8")+"&"+
                        URLEncoder.encode("Add_Mobile","UTF-8")+"="+URLEncoder.encode(Mobile,"UTF-8")+"&"+
                        URLEncoder.encode("Add_Word","UTF-8")+"="+URLEncoder.encode(Word,"UTF-8")+"&"+
                        URLEncoder.encode("Add_Id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                response = "";
                line  = "";
                while ((line = bufferedReader.readLine())!=null)
                {
                    response+= line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(method.equals("No_User_Message")){
            Title_Mail_No_User = params[1];
            Type        = params[2];
            Edit_email  = params[3];
            Edit_First  = params[4];
            Edit_Last   = params[5];
            Edit_Phone  = params[6];
            Edit_Message = params[7];

            try {
                url = new URL(URL_Insert);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                data = URLEncoder.encode("method","UTF-8")+"="+URLEncoder.encode("No_User_Message","UTF-8")+"&"+
                        URLEncoder.encode("Add_To","UTF-8")+"="+URLEncoder.encode("No_User_Message","UTF-8")+"&"+
                        URLEncoder.encode("Add_Title_Mail_No_User","UTF-8")+"="+URLEncoder.encode(Title_Mail_No_User,"UTF-8")+"&"+
                        URLEncoder.encode("Add_Type","UTF-8")+"="+URLEncoder.encode(Type,"UTF-8")+"&"+
                        URLEncoder.encode("Add_Edit_email","UTF-8")+"="+URLEncoder.encode(Edit_email,"UTF-8")+"&"+
                        URLEncoder.encode("Add_Edit_First","UTF-8")+"="+URLEncoder.encode(Edit_First,"UTF-8")+"&"+
                        URLEncoder.encode("Add_Edit_Last","UTF-8")+"="+URLEncoder.encode(Edit_Last,"UTF-8")+"&"+
                        URLEncoder.encode("Add_Edit_Phone","UTF-8")+"="+URLEncoder.encode(Edit_Phone,"UTF-8")+"&"+
                        URLEncoder.encode("Add_Edit_Message","UTF-8")+"="+URLEncoder.encode(Edit_Message,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                response = "";
                line  = "";
                while ((line = bufferedReader.readLine())!=null)
                {
                    response+= line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(method.equals("Notes_add")){
            selectTitle = params[1];
            selectBody  = params[2];
            selectLever = params[3];
            selectColor = params[4];
            selectColor = selectColor.toLowerCase();
            my_Id       = params[5];

            try {
                url = new URL(URL_Insert);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                data = URLEncoder.encode("method","UTF-8")+"="+URLEncoder.encode(method,"UTF-8")+"&"+
                        URLEncoder.encode("selectTitle","UTF-8")+"="+URLEncoder.encode(selectTitle,"UTF-8")+"&"+
                        URLEncoder.encode("selectBody","UTF-8")+"="+URLEncoder.encode(selectBody,"UTF-8")+"&"+
                        URLEncoder.encode("selectLever","UTF-8")+"="+URLEncoder.encode(selectLever,"UTF-8")+"&"+
                        URLEncoder.encode("selectColor","UTF-8")+"="+URLEncoder.encode(selectColor,"UTF-8")+"&"+
                        URLEncoder.encode("my_Id","UTF-8")+"="+URLEncoder.encode(my_Id,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                response = "";
                line  = "";
                while ((line = bufferedReader.readLine())!=null)
                {
                    response+= line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(method.equals("Remember_add")){
            selectTitle = params[1];
            selectBody  = params[2];
            selectDate  = params[3];
            selectTime  = params[4];
            selectLever = params[5];
            selectColor = params[6];
            selectColor = selectColor.toLowerCase();
            my_Id       = params[7];
            My_send     = params[8];
            comment     = params[9];

            try {
                url = new URL(URL_Insert);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                data = URLEncoder.encode("method","UTF-8")+"="+URLEncoder.encode(method,"UTF-8")+"&"+
                        URLEncoder.encode("selectTitle","UTF-8")+"="+URLEncoder.encode(selectTitle,"UTF-8")+"&"+
                        URLEncoder.encode("selectBody","UTF-8")+"="+URLEncoder.encode(selectBody,"UTF-8")+"&"+
                        URLEncoder.encode("selectDate","UTF-8")+"="+URLEncoder.encode(selectDate,"UTF-8")+"&"+
                        URLEncoder.encode("selectTime","UTF-8")+"="+URLEncoder.encode(selectTime,"UTF-8")+"&"+
                        URLEncoder.encode("selectLever","UTF-8")+"="+URLEncoder.encode(selectLever,"UTF-8")+"&"+
                        URLEncoder.encode("selectColor","UTF-8")+"="+URLEncoder.encode(selectColor,"UTF-8")+"&"+
                        URLEncoder.encode("comment","UTF-8")+"="+URLEncoder.encode(comment,"UTF-8")+"&"+
                        URLEncoder.encode("my_Id","UTF-8")+"="+URLEncoder.encode(my_Id,"UTF-8")+"&"+
                        URLEncoder.encode("My_send","UTF-8")+"="+URLEncoder.encode(My_send,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                response = "";
                line  = "";
                while ((line = bufferedReader.readLine())!=null)
                {
                    response+= line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(method.equals("Notes_change")){
            selectTitle = params[1];
            selectBody  = params[2];
            selectLever = params[3];
            selectColor = params[4];
            selectColor = selectColor.toLowerCase();
            my_Id       = params[5];
            Id_Note     = params[6];

            try {
                url = new URL(URL_Insert);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                data = URLEncoder.encode("method","UTF-8")+"="+URLEncoder.encode(method,"UTF-8")+"&"+
                        URLEncoder.encode("selectTitle","UTF-8")+"="+URLEncoder.encode(selectTitle,"UTF-8")+"&"+
                        URLEncoder.encode("selectBody","UTF-8")+"="+URLEncoder.encode(selectBody,"UTF-8")+"&"+
                        URLEncoder.encode("selectLever","UTF-8")+"="+URLEncoder.encode(selectLever,"UTF-8")+"&"+
                        URLEncoder.encode("selectColor","UTF-8")+"="+URLEncoder.encode(selectColor,"UTF-8")+"&"+
                        URLEncoder.encode("my_Id","UTF-8")+"="+URLEncoder.encode(my_Id,"UTF-8")+"&"+
                        URLEncoder.encode("Id_Note","UTF-8")+"="+URLEncoder.encode(Id_Note,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                response = "";
                line  = "";
                while ((line = bufferedReader.readLine())!=null)
                {
                    response+= line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(method.equals("Remember_change")){
            selectTitle = params[1];
            selectBody  = params[2];
            selectDate  = params[3];
            selectTime  = params[4];
            selectLever = params[5];
            selectColor = params[6];
            selectColor = selectColor.toLowerCase();
            my_Id       = params[7];
            Id_Note     = params[8];

            try {
                url = new URL(URL_Insert);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                data = URLEncoder.encode("method","UTF-8")+"="+URLEncoder.encode(method,"UTF-8")+"&"+
                        URLEncoder.encode("selectTitle","UTF-8")+"="+URLEncoder.encode(selectTitle,"UTF-8")+"&"+
                        URLEncoder.encode("selectBody","UTF-8")+"="+URLEncoder.encode(selectBody,"UTF-8")+"&"+
                        URLEncoder.encode("selectLever","UTF-8")+"="+URLEncoder.encode(selectLever,"UTF-8")+"&"+
                        URLEncoder.encode("selectColor","UTF-8")+"="+URLEncoder.encode(selectColor,"UTF-8")+"&"+
                        URLEncoder.encode("my_Id","UTF-8")+"="+URLEncoder.encode(my_Id,"UTF-8")+"&"+
                        URLEncoder.encode("selectDate","UTF-8")+"="+URLEncoder.encode(selectDate,"UTF-8")+"&"+
                        URLEncoder.encode("selectTime","UTF-8")+"="+URLEncoder.encode(selectTime,"UTF-8")+"&"+
                        URLEncoder.encode("Id_Note","UTF-8")+"="+URLEncoder.encode(Id_Note,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                response = "";
                line  = "";
                while ((line = bufferedReader.readLine())!=null)
                {
                    response+= line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(method.equals("Remember_Member")){
            selectTitle = params[1];
            selectBody  = params[2];
            selectLever = params[3];
            selectColor = params[4];
            selectColor = selectColor.toLowerCase();
            selectDate  = params[5];
            selectTime  = params[6];
            my_Id       = params[7];
            Member_id   = params[8];
            try {
                url = new URL(URL_Insert);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                data = URLEncoder.encode("method","UTF-8")+"="+URLEncoder.encode(method,"UTF-8")+"&"+
                        URLEncoder.encode("selectTitle","UTF-8")+"="+URLEncoder.encode(selectTitle,"UTF-8")+"&"+
                        URLEncoder.encode("selectBody","UTF-8")+"="+URLEncoder.encode(selectBody,"UTF-8")+"&"+
                        URLEncoder.encode("selectLever","UTF-8")+"="+URLEncoder.encode(selectLever,"UTF-8")+"&"+
                        URLEncoder.encode("selectColor","UTF-8")+"="+URLEncoder.encode(selectColor,"UTF-8")+"&"+
                        URLEncoder.encode("my_Id","UTF-8")+"="+URLEncoder.encode(my_Id,"UTF-8")+"&"+
                        URLEncoder.encode("Member_id","UTF-8")+"="+URLEncoder.encode(Member_id,"UTF-8")+"&"+
                        URLEncoder.encode("selectDate","UTF-8")+"="+URLEncoder.encode(selectDate,"UTF-8")+"&"+
                        URLEncoder.encode("selectTime","UTF-8")+"="+URLEncoder.encode(selectTime,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                response = "";
                line  = "";
                while ((line = bufferedReader.readLine())!=null)
                {
                    response+= line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(method.equals("notesDelete") || method.equals("rememberDelete")){
            Id_Note = params[1];
            my_Id   = params[2];
            try {
                url = new URL(URL_Delete);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                data = URLEncoder.encode("method","UTF-8")+"="+URLEncoder.encode(method,"UTF-8")+"&"+
                        URLEncoder.encode("Id_Note","UTF-8")+"="+URLEncoder.encode(Id_Note,"UTF-8")+"&"+
                        URLEncoder.encode("my_Id","UTF-8")+"="+URLEncoder.encode(my_Id,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                response = "";
                line  = "";
                while ((line = bufferedReader.readLine())!=null)
                {
                    response+= line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        if ((result.equals("Registration Success...")) || (result.equals("No info Is available")) ||
                (result.equals("We have meny user with email")) || (result.equals("Error ..."))) {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
        else {
            if (result.equals("welcome member")) {
                new BackgroudTaskLogin().execute();
            } else if (result.equals("UpDate Is available...")) {
                Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
                intent = new Intent(ctx.getApplicationContext(), Profile.class);
                ctx.startActivity(intent); /* Edit the value here*/
            } else if (result.equals("No UpDate Is available...")) {
                Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
                intent = new Intent(ctx.getApplicationContext(), Change_profile.class);
                ctx.startActivity(intent); /* Edit the value here*/
            } else if (result.equals("No Send Message To Admin...") || result.equals("Error Send Message...") ) {
                Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
                intent = new Intent(ctx.getApplicationContext(), Login_mail.class);
                ctx.startActivity(intent); /* Edit the value here*/
            } else if (result.equals("Message Send To Admin...")) {
                Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
                intent = new Intent(ctx.getApplicationContext(), Login.class);
                ctx.startActivity(intent); /* Edit the value here*/
            } else if (result.equals("No Insert...") || result.equals("Error Insert...") || result.equals("No Delete...")) {
                Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
                extras = new Bundle();
                extras.putInt("First_Login", 2);
                intent = new Intent(ctx.getApplicationContext(), Show_Notes.class);
                intent.putExtras(extras);
                ctx.startActivity(intent); /* Edit the value here*/
            }else if (result.equals("Insert...") || result.equals("Delete...") || result.equals("Add New Contact...")) {
                Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
                extras = new Bundle();
                extras.putInt("First_Login", 2);
                Intent intent = new Intent(ctx.getApplicationContext(), User_Menu.class);
                intent.putExtras(extras);
                ctx.startActivity(intent); /* Edit the value here*/
            }else if (result.equals("No Add Contact...") || result.equals("Error Add Contact...")) {
                Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
                Bundle extras = new Bundle();
                extras.putInt("First_Login", 2);
                Intent intent = new Intent(ctx.getApplicationContext(), User_Menu.class);
                intent.putExtras(extras);
                ctx.startActivity(intent); /* Edit the value here*/
            } else {
                alertDialog.setMessage(result);
                alertDialog.show();
            }
        }
    }

    class BackgroudTaskLogin extends AsyncTask<Void,Void,String>
    {
        @Override
        protected void onPreExecute() {
            json_url = Url_mail+mail;
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while ((JSON_STRING = bufferedReader.readLine()) != null)
                {
                    stringBuilder.append((JSON_STRING+"\n"));
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            } catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            json_string = result;
            if (json_string != null) {
                try {
                    jsonObject = new JSONObject(json_string);
                    jsonArray = jsonObject.getJSONArray("data");
                    int count = 0;
                    String  first, last, email, password, avatar, mphone, wphone, address, status, type_job, name_job;
                    int id;
                    while (count<jsonArray.length())
                    {
                        JSONObject JO = jsonArray.getJSONObject(count);
                        id = JO.getInt("id");
                        first = JO.getString("first");
                        last = JO.getString("last");
                        email = JO.getString("email");
                        password = JO.getString("password");
                        avatar = JO.getString("avatar");
                        mphone = JO.getString("mphone");
                        wphone = JO.getString("wphone");
                        address = JO.getString("address");
                        status = JO.getString("status");
                        type_job = JO.getString("type_job");
                        name_job = JO.getString("name_job");

                        Bundle extras = new Bundle();
                        extras.putInt("First_Login", 1);
                        extras.putInt("EXTRA_User_id",id);
                        extras.putString("EXTRA_User_first",first);
                        extras.putString("EXTRA_User_last",last);
                        extras.putString("EXTRA_User_email",email);
                        extras.putString("EXTRA_User_password",password);
                        extras.putString("EXTRA_User_avatar",avatar);
                        extras.putString("EXTRA_User_mphone",mphone);
                        extras.putString("EXTRA_User_wphone",wphone);
                        extras.putString("EXTRA_User_address",address);
                        extras.putString("EXTRA_User_status",status);
                        extras.putString("EXTRA_User_type_job",type_job);
                        extras.putString("EXTRA_User_name_job",name_job);
                        Toast.makeText(ctx, first, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ctx.getApplicationContext(), User_Menu.class);
                        intent.putExtras(extras);
                        ctx.startActivity(intent); /* Edit the value here*/
                        count++;
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}