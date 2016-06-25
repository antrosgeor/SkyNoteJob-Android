package com.example.antrosgeor.skynotejob;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

public class Change_profile extends AppCompatActivity {
/** Values */
    String LH_Destination_images = GlobalClass.LH_Destination_images;
    String Image_Member_File = GlobalClass.Image_Member_File;
    String imageUrl = LH_Destination_images+Image_Member_File+"no_avatar.png";

    String  First, Last, Email, Pass, Address, Mobile, Word, Note, Type_Button, User_avatar, method,
            User_first, User_last, User_email, User_address, User_mobile, User_word;
    int     User_id;
    TextView TextFirst, TextLast, TextEmail, TextMobile, TextWord, TextPass, TextAddress, TextNotes;
    EditText EditFirst, EditLast, EditEmail, EditMobile, EditWord, EditPass, EditAddress, EditNotes;
    ImageView ImageAvatar;
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_profile);

        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
        User_id = globalVariable.getUser_id();
//EditText
        EditFirst = (EditText) findViewById(R.id.EditFirst);
        EditLast = (EditText) findViewById(R.id.EditLast);
        EditEmail = (EditText) findViewById(R.id.EditEmail);
        EditMobile = (EditText) findViewById(R.id.EditMobile);
        EditWord = (EditText) findViewById(R.id.EditWord);
        EditPass = (EditText) findViewById(R.id.EditPass);
        EditAddress = (EditText) findViewById(R.id.EditAddress);
        EditNotes = (EditText) findViewById(R.id.EditNotes);
//TextView
        TextFirst = (TextView) findViewById(R.id.Textfirst);
        TextLast = (TextView) findViewById(R.id.TextLast);
        TextEmail = (TextView) findViewById(R.id.TextEmail);
        TextMobile = (TextView) findViewById(R.id.TextMobile);
        TextWord = (TextView) findViewById(R.id.TextWord);
        TextPass = (TextView) findViewById(R.id.TextPassword);
        TextAddress = (TextView) findViewById(R.id.textAddress);
        TextNotes = (TextView) findViewById(R.id.textNotes);
//ImageView
        ImageAvatar  =(ImageView) findViewById(R.id.ImageAvatar);
        update = (Button) findViewById(R.id.update);

        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        update.setTypeface(font);
        // B_Login.setText(    @string/Login);
        update.setText(getString(R.string.fa_cloud_upload)+"    "+getString(R.string.update));

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        Type_Button = extras.getString("EXTRA_Button");
        if(Type_Button.equals("Change_My_profile")){
            User_avatar = globalVariable.getUser_avatar();
            User_first = globalVariable.getUser_first();
            User_last = globalVariable.getUser_last();
            User_email= globalVariable.getUser_email();
            User_address= globalVariable.getUser_address();
            User_mobile = globalVariable.getUser_mphone();
            User_word= globalVariable.getUser_wphone();
            if(User_avatar != null && !User_avatar.isEmpty()) {
                 imageUrl = LH_Destination_images+Image_Member_File+User_avatar;
            }
            TextNotes.setVisibility(View.GONE);
            EditNotes.setVisibility(View.GONE);
            EditFirst.setText(User_first);
            EditLast.setText(User_last);
            EditEmail.setText(User_email);
            EditMobile.setText(User_mobile);
            EditWord.setText(User_word);
            EditAddress.setText(User_address);
        }else if(Type_Button.equals("Contact_Add")){
            TextPass.setVisibility(View.GONE);
            EditPass.setVisibility(View.GONE);
        }
        new DownloadImageTask((ImageView) findViewById(R.id.ImageAvatar))
                .execute(imageUrl);
    }

    public void update(View view){
        if(Type_Button.equals("Change_profile")) {
            First = EditFirst.getText().toString();
            Last = EditLast.getText().toString();
            Email = EditEmail.getText().toString();
            Mobile = EditMobile.getText().toString();
            Word = EditWord.getText().toString();
            Address = EditAddress.getText().toString();
            Pass = EditPass.getText().toString();
            method = "update";
            // edo trexoume tin class. BackgroundTask.class.
            BackgroundTask backgroundTask = new BackgroundTask(this);
            //metaferoume ta String sto BackgroundTask.class
            backgroundTask.execute(method, First, Last, Email, Pass, Address, Mobile, Word, String.valueOf(User_id));
        }else if (Type_Button.equals("Contact_Add")){
            First = EditFirst.getText().toString();
            Last = EditLast.getText().toString();
            Email = EditEmail.getText().toString();
            Mobile = EditMobile.getText().toString();
            Word = EditWord.getText().toString();
            Address = EditAddress.getText().toString();
            Note = EditNotes.getText().toString();
            method = "Contact_Add";
            // edo trexoume tin class. BackgroundTask.class.
            BackgroundTask backgroundTask = new BackgroundTask(this);
            //metaferoume ta String sto BackgroundTask.class
            backgroundTask.execute(method, First, Last, Email, Note, Address, Mobile, Word, String.valueOf(User_id));
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