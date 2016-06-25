package com.example.antrosgeor.skynotejob;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ContactApp extends AppCompatActivity {
    ListView listView= null;
    ImageView ImageAvatar;
    TextView UserName,UserMail,UserJob;
    String  url, select_Button, User_first, User_last, User_Email, User_avatar, User_name_job, User_type_job,
            json_url, json_string, JSON_STRING;
    int     User_id;
    String Image_Member_File = GlobalClass.Image_Member_File;
    String LH_Destination_images = GlobalClass.LH_Destination_images;
    String LH_Destination = GlobalClass.LH_Destination;
    String imageUrl = LH_Destination_images+Image_Member_File+"no_avatar.png";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_app);
        ImageAvatar = (ImageView) findViewById(R.id.ImageAvatar);
        UserName    = (TextView) findViewById(R.id.UserName);
        UserJob     = (TextView) findViewById(R.id.UserJob);
        UserMail    = (TextView) findViewById(R.id.UserMail);

        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
        User_first    = globalVariable.getUser_first();
        User_last     = globalVariable.getUser_last();
        User_Email    = globalVariable.getUser_email();
        User_avatar   = globalVariable.getUser_avatar();
        User_name_job = globalVariable.getUser_name_job();
        User_type_job = globalVariable.getUser_type_job();
        User_id       = globalVariable.getUser_id();

        if(User_avatar != null && !User_avatar.isEmpty()) {
            imageUrl = LH_Destination_images+Image_Member_File+User_avatar;
        }
        new DownloadImageTask((ImageView) findViewById(R.id.ImageAvatar))
                .execute(imageUrl);

        UserName.setText(getString(R.string.Name)+" : "+User_first+" "+User_last);
        UserMail.setText(getString(R.string.Mail)+" : "+User_Email);
        UserJob.setText(getString(R.string.Job)+" : "+User_name_job);
    }
    public void AddContact(View view){
        Intent intent = new Intent(getApplicationContext(), Change_profile.class);
        Bundle extras = new Bundle();
        extras.putString("EXTRA_Button", "Contact_Add");
        intent.putExtras(extras);
        startActivity(intent);
    }

    public void SeeAdmin(View view){
        url = LH_Destination+"?key=admin_user";
        select_Button = "Admin_select";
        new BackgroudTask().execute();
    }
    public void SeeMember(View view){
        url = LH_Destination+"?key=member";
        select_Button="Member";
        new BackgroudTask().execute();
    }
    public void SeeContact(View view){
        url = LH_Destination+"?key=contact&id_member="+User_id;
        select_Button="Contact";
        new BackgroudTask().execute();
    }
    public void SendMessage(View view){
        String[] items = {getResources().getString(R.string.Send_Message),getResources().getString(R.string.Send_Mail)};

        AlertDialog.Builder builder = new AlertDialog.Builder(ContactApp.this);
        builder.setCancelable(true);
        builder.setView(listView);
        listView = new ListView(this);
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(this, R.layout.list_addplus, R.id.txtitem, items);
        listView.setAdapter(adapter);
        builder.setPositiveButton(R.string.cancel,null);
        final AlertDialog dialog = builder.create();
        dialog.show();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ViewGroup vg = (ViewGroup) view;
                TextView txt = (TextView) vg.findViewById(R.id.txtitem);
                String select = txt.getText().toString();
                if(select.equals(String.valueOf(getResources().getString(R.string.Send_Message)))){
                    Intent intent = new Intent(ContactApp.this, Send_from_App.class);
                    Bundle extras = new Bundle();
                    extras.putString("EXTRA_Button", "Send_message");
                    intent.putExtras(extras);
                    startActivity(intent);
                    Toast.makeText(ContactApp.this, R.string.Send_Message, Toast.LENGTH_LONG).show();
                } else
                    if(select.equals(getResources().getString(R.string.Send_Mail))){
                        Intent intent = new Intent(ContactApp.this, Send_from_App.class);
                        Bundle extras = new Bundle();
                        extras.putString("EXTRA_Button", "Send_mail");
                        intent.putExtras(extras);
                        startActivity(intent);
                        Toast.makeText(ContactApp.this, R.string.Send_Mail, Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(ContactApp.this, R.string.cancel, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    class BackgroudTask extends AsyncTask<Void,Void,String>
    {
        @Override
        protected void onPreExecute() {
            json_url = url;
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
                if(select_Button.equals("Admin_select")) { // pages
                    Intent intent = new Intent(ContactApp.this, Contact_list.class);
                    intent.putExtra("json_data", json_string);
                    intent.putExtra("button",select_Button);
                    startActivity(intent);
                }else if(select_Button.equals("Member")){ //news
                    Intent intent = new Intent(ContactApp.this, Contact_list.class);
                    intent.putExtra("json_data", json_string);
                    intent.putExtra("button",select_Button);
                    startActivity(intent);
                }else if(select_Button.equals("Contact")){ //notes
                    Intent intent = new Intent(ContactApp.this, Contact_list.class);
                    intent.putExtra("json_data", json_string);
                    intent.putExtra("button",select_Button);
                    startActivity(intent);
                }
            }
        }
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }
        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e(getString(R.string.Error), e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }
        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}