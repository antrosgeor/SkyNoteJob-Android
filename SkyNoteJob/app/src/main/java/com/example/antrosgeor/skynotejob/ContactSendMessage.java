package com.example.antrosgeor.skynotejob;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.telephony.SmsManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;

public class ContactSendMessage extends AppCompatActivity {

    Button      SendButton;
    EditText    editText,TitleMail;
    ImageView   imageavatar;
    TextView    Title,typeSend,FirstTitle;
    String      id, header, name, send, avatar, imageUrl, message, MyFirst, MyLast, MyEmail, MyName, type, email_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_send_message);
        SendButton = (Button) findViewById(R.id.SendButton);
        editText = (EditText) findViewById(R.id.editText);
        TitleMail = (EditText) findViewById(R.id.TitleMail);
        imageavatar = (ImageView) findViewById(R.id.imageavatar);
        Title = (TextView) findViewById(R.id.TextTitle);
        typeSend = (TextView) findViewById(R.id.typeSend);
        FirstTitle = (TextView) findViewById(R.id.FirstTitle);

        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        SendButton.setTypeface(font);
//GlobalClass
        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
        MyFirst =  globalVariable.getUser_first();
        MyLast =  globalVariable.getUser_last();
        MyEmail =  globalVariable.getUser_email();
        MyName = MyFirst+" "+MyLast;
//Intent Extras
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        type = extras.getString("EXTRA_title");
        header = extras.getString("EXTRA_header");
        name = extras.getString("EXTRA_name");
        send = extras.getString("EXTRA_send");
        imageUrl = extras.getString("EXTRA_avatar");
//get from editText
        email_title = TitleMail.getText().toString();
        message = editText.getText().toString();
//ImageView Show
            new DownloadImageTask((ImageView) findViewById(R.id.imageavatar))
                    .execute(imageUrl);

        FirstTitle.setText(type);
        SendButton.setText(getResources().getString(R.string.Send_Message)+"   "+getString(R.string.fa_mail_forward));
//Check
        if (header.equals("mail")) {
            Title.setText(getResources().getString(R.string.Send_mail) + " \n " + name);
            typeSend.setText(getResources().getString(R.string.Email)+" "+send);
//Button SendButtton
            SendButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("message/rfc822");
                    i.putExtra(Intent.EXTRA_EMAIL  , new String[]{send});
                    i.putExtra(Intent.EXTRA_SUBJECT, email_title);
                    i.putExtra(Intent.EXTRA_TEXT   , message);
                    try {
                        startActivity(Intent.createChooser(i, getString(R.string.Send_Mail)));
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(ContactSendMessage.this, R.string.error_send_mail, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else if (header.equals("sms")) {
            TitleMail.setVisibility(View.GONE);
            typeSend.setText(getResources().getString(R.string.Mobile_Phone)+" "+send);
            Title.setText(getResources().getString(R.string.Send_SMS) + " \n " + name);
//Button SendButtton
            SendButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (send.length() > 0 && message.length() > 0) {
                        Sender(send, message);
                        editText.setText("");
                    } else {
                        Toast.makeText(getBaseContext(), R.string.error_sms,
                                Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + send));
//                    intent.putExtra("sms_body", message+"\n"+"Send from "+MyName);
//                    startActivity(intent);
                    }
                }
            });
        }
    }
//ImageAvatar
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
//SMS
    private void Sender(String phoneNumber, String MessageText)
    {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, MessageText, null, null);
        Toast.makeText(getBaseContext(), R.string.Message_sent, Toast.LENGTH_SHORT).show();
    }
}