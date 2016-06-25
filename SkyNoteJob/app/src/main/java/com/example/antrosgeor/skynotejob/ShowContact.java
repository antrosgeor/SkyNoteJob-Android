package com.example.antrosgeor.skynotejob;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

public class ShowContact extends AppCompatActivity {

    String id, first, last, email, avatar, mobilephone, wordphone, homeaddress, note, type_job, job;
    TextView Name, Email, address, mphone, wphone, Note, title;
    ImageView imageavatar;
    Button mailbutton, Send_SMS, Call, Calla, Remember_Contact_member;
    String LH_Destination_images = GlobalClass.LH_Destination_images;
    String Image_Contacts_File = GlobalClass.Image_Contacts_File;
    String Image_Member_File = GlobalClass.Image_Member_File;
    String Image_Admin_File = GlobalClass.Image_Admin_File;
    String imageUrl = LH_Destination_images+Image_Member_File+"no_avatar.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_contact);
        imageavatar = (ImageView)findViewById(R.id.imageavatar);
        Name = (TextView) findViewById(R.id.Name);
        Email = (TextView) findViewById(R.id.Email);
        address = (TextView) findViewById(R.id.address);
        mphone = (TextView) findViewById(R.id.mphone);
        wphone = (TextView) findViewById(R.id.wphone);
        Note = (TextView) findViewById(R.id.Note);
        title = (TextView) findViewById(R.id.Text_Title);
        mailbutton = (Button) findViewById(R.id.mailbutton);
        Send_SMS = (Button) findViewById(R.id.Send_SMS);
        Call = (Button) findViewById(R.id.Call);
        Calla = (Button) findViewById(R.id.Calla);
        Remember_Contact_member = (Button) findViewById(R.id.Remember_Contact_member);

        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        mailbutton.setTypeface(font);
        Send_SMS.setTypeface(font);
        Call.setTypeface(font);
        Calla.setTypeface(font);
        Remember_Contact_member.setTypeface(font);
        mailbutton.setText(getResources().getString(R.string.Send_Mail)+"  "+getResources().getString(R.string.fa_mail_forward));
        Send_SMS.setText(getResources().getString(R.string.sms)+"  "+getResources().getString(R.string.fa_comments));
        Call.setText(getResources().getString(R.string.call)+"  "+getResources().getString(R.string.fa_phone));
        Calla.setText(getResources().getString(R.string.call)+"  "+getResources().getString(R.string.fa_phone));
        Remember_Contact_member.setText(getResources().getString(R.string.fa_flag)+"  "+getResources().getString(R.string.remember_member));

//Intent Extras
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String button = extras.getString("EXTRA_button");

        if(button.equals("Contact")){
            id =  extras.getString("EXTRA_id");
            first =  extras.getString("EXTRA_first");
            last =  extras.getString("EXTRA_last");
            email =  extras.getString("EXTRA_email");
            avatar =  extras.getString("EXTRA_avatar");
            mobilephone =  extras.getString("EXTRA_mphone");
            wordphone =  extras.getString("EXTRA_wphone");
            homeaddress =  extras.getString("EXTRA_address");
            note =  extras.getString("EXTRA_note");
//Show
            if(avatar != null && !avatar.isEmpty()) {
                 imageUrl = LH_Destination_images+Image_Contacts_File+avatar;
            }
            new DownloadImageTask((ImageView) findViewById(R.id.imageavatar))
                    .execute(imageUrl);

            title.setText(button);
            Name.setText(getResources().getString(R.string.Name)+": " +first+" "+last);
            Remember_Contact_member.setVisibility(View.GONE);
            if(homeaddress.isEmpty()){
                address.setVisibility(View.GONE);
            }else {
                address.setText(getResources().getString(R.string.Address) + ": " + homeaddress);
            }
            if (note.isEmpty()){
                Note.setVisibility(View.GONE);
            }else {
                Note.setText(getResources().getString(R.string.notes) + ": " + note);
            }
//Button Call Mobile
            if(mobilephone.isEmpty()){
                Call.setVisibility(View.GONE);
                Send_SMS.setVisibility(View.GONE);
                mphone.setVisibility(View.GONE);
            }else {
                mphone.setText(getResources().getString(R.string.Mobile_Phone)+": "+mobilephone);
                Call.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
////                        Intent callIntent = new Intent(Intent.ACTION_CALL);
////                        callIntent.setData(Uri.parse("tel:" + mobilephone));
////                        startActivity(callIntent);
//                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mobilephone));
//                        startActivity(intent);
                        Intent callIntent = new Intent(Intent.ACTION_DIAL);
                        callIntent.setData(Uri.parse("tel:"+Uri.encode(mobilephone.trim())));
                        callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(callIntent);

                    }
                });
                //Button Send_SMS
                Send_SMS.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ShowContact.this, ContactSendMessage.class);
                        Bundle extras = new Bundle();
                        extras.putString("EXTRA_title","Contact");
                        extras.putString("EXTRA_header","sms");
                        extras.putString("EXTRA_avatar",imageUrl);
                        extras.putString("EXTRA_name",first+" "+last);
                        extras.putString("EXTRA_send",mobilephone);
                        intent.putExtras(extras);
                        startActivity(intent);
                    }
                });
            }
//Button Calls Word
            if(wordphone.isEmpty()){
                Calla.setVisibility(View.GONE);
                wphone.setVisibility(View.GONE);
            } else {
                wphone.setText(getResources().getString(R.string.Word_Phone)+": "+wordphone);
                Calla.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Intent callIntent = new Intent(Intent.ACTION_CALL);
//                        callIntent.setData(Uri.parse("tel:" + wordphone));
//                        // startActivity(callIntent);
                        Intent callIntent = new Intent(Intent.ACTION_DIAL);
                        callIntent.setData(Uri.parse("tel:"+Uri.encode(wordphone.trim())));
                        callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(callIntent);
                    }
                });
            }
//Button mailbutton
            if(email.isEmpty()){
                mailbutton.setVisibility(View.GONE);
                Email.setVisibility(View.GONE);
            } else {
                Email.setText(getResources().getString(R.string.Email) + ": " + email);
                mailbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ShowContact.this, ContactSendMessage.class);
                        Bundle extras = new Bundle();
                        extras.putString("EXTRA_title", "Contact");
                        extras.putString("EXTRA_header", "mail");
                        extras.putString("EXTRA_avatar", imageUrl);
                        extras.putString("EXTRA_name", first + " " + last);
                        extras.putString("EXTRA_send", email);
                        intent.putExtras(extras);
                        startActivity(intent);
                    }
                });
            }
        }else if(button.equals("Member")){
            id =  extras.getString("EXTRA_id");
            first =  extras.getString("EXTRA_first");
            last =  extras.getString("EXTRA_last");
            email =  extras.getString("EXTRA_email");
            avatar =  extras.getString("EXTRA_avatar");
            mobilephone =  extras.getString("EXTRA_mphone");
            wordphone =  extras.getString("EXTRA_wphone");
            homeaddress =  extras.getString("EXTRA_address");
            type_job =  extras.getString("EXTRA_type_job");
            job =  extras.getString("EXTRA_job");
//Show
            if(avatar != null && !avatar.isEmpty()) {
                 imageUrl = LH_Destination_images+Image_Member_File+avatar;
            }
            new DownloadImageTask((ImageView) findViewById(R.id.imageavatar))
                    .execute(imageUrl);

            title.setText(button);
            Name.setText(getResources().getString(R.string.Name)+": " +first+" "+last);

            Remember_Contact_member.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ShowContact.this, Change_NR.class);
                    Bundle extras = new Bundle();
                    extras.putString("EXTRA_Button", "Remember_Member");
                    extras.putString("EXTRA__Member_Id", id);
                    extras.putString("EXTRA__Member_first", first);
                    extras.putString("EXTRA__Member_last", last);
                    extras.putString("EXTRA__Member_avatar", imageUrl);
                    intent.putExtras(extras);
                    startActivity(intent);
                }
            });

            if(homeaddress.isEmpty()){
                address.setVisibility(View.GONE);
            }else{
                address.setText(getResources().getString(R.string.Address)+": "+homeaddress);
            }
            if(job.isEmpty()){
                Note.setVisibility(View.GONE);
            }else{
                Note.setText(getResources().getString(R.string.Job)+": "+job);
            }
            if(email.isEmpty()){
                Email.setVisibility(View.GONE);
                mailbutton.setVisibility(View.GONE);
            }else {
                Email.setText(getResources().getString(R.string.Email)+": "+email);
//Button mailbutton
                mailbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ShowContact.this, ContactSendMessage.class);
                        Bundle extras = new Bundle();
                        extras.putString("EXTRA_title", "Member");
                        extras.putString("EXTRA_header", "mail");
                        extras.putString("EXTRA_avatar", imageUrl);
                        extras.putString("EXTRA_name", first + " " + last);
                        extras.putString("EXTRA_send", email);
                        intent.putExtras(extras);
                        startActivity(intent);
                    }
                });
            }
            if(mobilephone.isEmpty()){
                mphone.setVisibility(View.GONE);
                Send_SMS.setVisibility(View.GONE);
                Call.setVisibility(View.GONE);
            }else {
                mphone.setText(getResources().getString(R.string.Mobile_Phone)+": "+mobilephone);
//Button Send_SMS
                Send_SMS.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ShowContact.this, ContactSendMessage.class);
                        Bundle extras = new Bundle();
                        extras.putString("EXTRA_title", "Member");
                        extras.putString("EXTRA_header", "sms");
                        extras.putString("EXTRA_avatar", imageUrl);
                        extras.putString("EXTRA_name", first + " " + last);
                        extras.putString("EXTRA_send", mobilephone);
                        intent.putExtras(extras);
                        startActivity(intent);
                    }
                });
                //Button Call
                Call.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent callIntent = new Intent(Intent.ACTION_DIAL);
                        callIntent.setData(Uri.parse("tel:"+Uri.encode(mobilephone.trim())));
                        callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(callIntent);
                    }
                });
            }
            if (wordphone.isEmpty()){
                wphone.setVisibility(View.GONE);
                Calla.setVisibility(View.GONE);
            }else {
                wphone.setText(getResources().getString(R.string.Word_Phone)+": "+wordphone);
//Button Calls
                Calla.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent callIntent = new Intent(Intent.ACTION_DIAL);
                        callIntent.setData(Uri.parse("tel:"+Uri.encode(wordphone.trim())));
                        callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(callIntent);
                    }
                });
            }
        }else if(button.equals("Admin_select")) {
            id = extras.getString("EXTRA_id");
            first = extras.getString("EXTRA_first");
            last = extras.getString("EXTRA_last");
            email = extras.getString("EXTRA_email");
            avatar = extras.getString("EXTRA_avatar");

            //Show
            if(avatar != null && !avatar.isEmpty()) {
                 imageUrl = LH_Destination_images+Image_Admin_File+avatar;
            }
            new DownloadImageTask((ImageView) findViewById(R.id.imageavatar))
                    .execute(imageUrl);

            title.setText(button);
            Name.setText(getResources().getString(R.string.Name)+": " +first+" "+last);
            Remember_Contact_member.setVisibility(View.GONE);
            if(email.isEmpty()){
                Email.setVisibility(View.GONE);
                mailbutton.setVisibility(View.GONE);
            }else{
                Email.setText(getResources().getString(R.string.Email)+": "+email);
                mailbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ShowContact.this, ContactSendMessage.class);
                        Bundle extras = new Bundle();
                        extras.putString("EXTRA_title", "Admin_select");
                        extras.putString("EXTRA_header", "mail");
                        extras.putString("EXTRA_avatar", imageUrl);
                        extras.putString("EXTRA_name", first + " " + last);
                        extras.putString("EXTRA_send", email);
                        intent.putExtras(extras);
                        startActivity(intent);
                    }
                });
            }
//hide the Button.
            Calla.setVisibility(View.GONE);
            Call.setVisibility(View.GONE);
            Send_SMS.setVisibility(View.GONE);
//hide the Textview.
            address.setVisibility(View.GONE);
            mphone.setVisibility(View.GONE);
            wphone.setVisibility(View.GONE);
            Note.setVisibility(View.GONE);
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