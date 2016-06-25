package com.example.antrosgeor.skynotejob;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

public class Profile extends AppCompatActivity {
    TextView TextName, TextEmail, TextJob, TextMobile, TextWord, TextStatus, TextAddress;
    // get data from JSon
    TextView ViewName, ViewEmail, ViewJob, ViewMobile, ViewWord, ViewStatus, ViewAddress;

    String LH_Destination_images = GlobalClass.LH_Destination_images;
    String Image_Member_File = GlobalClass.Image_Member_File;
    int User_id;
    String User_first, User_last, User_email, User_password, User_avatar, User_mphone, User_wphone,
            User_address, User_status, User_type_job, User_name_job ;
    Button change_profile;
    String  imageUrl = LH_Destination_images+Image_Member_File+"no_avatar.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        TextName = (TextView) findViewById(R.id.Textfirst);
        TextEmail = (TextView) findViewById(R.id.TextLast);
        TextJob = (TextView) findViewById(R.id.TextEmail);
        TextMobile = (TextView) findViewById(R.id.TextMobile);
        TextWord = (TextView) findViewById(R.id.TextWord);
        TextStatus = (TextView) findViewById(R.id.TextStatus);
        TextAddress = (TextView) findViewById(R.id.TextPassword);

        ViewName = (TextView) findViewById(R.id.EditFirst);
        ViewEmail = (TextView) findViewById(R.id.EditLast);
        ViewJob = (TextView) findViewById(R.id.EditEmail);
        ViewMobile = (TextView) findViewById(R.id.EditMobile);
        ViewWord = (TextView) findViewById(R.id.ViewWord);
        ViewStatus = (TextView) findViewById(R.id.ViewStatus);
        ViewAddress = (TextView) findViewById(R.id.EditPass);
        change_profile = (Button) findViewById(R.id.update);

        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
        User_id = globalVariable.getUser_id();
        User_first = globalVariable.getUser_first();
        User_last = globalVariable.getUser_last();
        User_email = globalVariable.getUser_email();
        User_password = globalVariable.getUser_password();
        User_avatar = globalVariable.getUser_avatar();
        User_mphone = globalVariable.getUser_mphone();
        User_wphone = globalVariable.getUser_wphone();
        User_address = globalVariable.getUser_address();
        User_status = globalVariable.getUser_status();
        User_type_job = globalVariable.getUser_type_job();
        User_name_job = globalVariable.getUser_name_job();

        if(User_avatar != null && !User_avatar.isEmpty()) {
            imageUrl = LH_Destination_images+Image_Member_File+User_avatar;
        }
        new DownloadImageTask((ImageView) findViewById(R.id.ImageAvatar))
                .execute(imageUrl);

        ViewName.setText(User_first+" "+User_last);
        ViewEmail.setText(User_email);
        ViewJob.setText(User_name_job);
        ViewMobile.setText(User_mphone);
        ViewWord.setText(User_wphone);
        ViewStatus.setText(User_status);
        ViewAddress.setText(User_address);
        change();
    }

    public void change() {
        change_profile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Change_profile.class);
                Bundle extras = new Bundle();
                extras.putString("EXTRA_Button", "Change_My_profile");
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
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