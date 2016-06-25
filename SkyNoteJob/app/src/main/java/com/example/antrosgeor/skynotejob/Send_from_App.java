package com.example.antrosgeor.skynotejob;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;

public class Send_from_App extends AppCompatActivity {

    String method, SendTo, SendMessage, MyFirst, MyLast, MyEmail, Myavatar, Mymphone, Mywphone, Myaddress, Mytype_job, Myname_job;
    int Myid;
    TextView FirstTitle, TextTitle, typeSend;
    ImageView imageavatar;
    TextInputLayout input_layout_email, input_layout_editText;
    EditText TitleMail, editText;
    Button SendButton;
    String Image_Member_File = GlobalClass.Image_Member_File;
    String LH_Destination_images = GlobalClass.LH_Destination_images;
    String  imageUrl = LH_Destination_images+Image_Member_File+"no_avatar.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_from__app);
        FirstTitle =(TextView) findViewById(R.id.FirstTitle);
        TextTitle =(TextView) findViewById(R.id.TextTitle);
        typeSend =(TextView) findViewById(R.id.typeSend);
        imageavatar =(ImageView) findViewById(R.id.imageavatar);
        input_layout_email =(TextInputLayout) findViewById(R.id.input_layout_email);
        input_layout_editText =(TextInputLayout) findViewById(R.id.input_layout_editText);
        TitleMail =(EditText) findViewById(R.id.TitleMail);
        editText =(EditText) findViewById(R.id.editText);
        SendButton =(Button) findViewById(R.id.SendButton);
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        SendButton.setTypeface(font);

        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
        MyFirst =  globalVariable.getUser_first();
        MyLast =  globalVariable.getUser_last();
        MyEmail =  globalVariable.getUser_email();
        Myid = globalVariable.getUser_id();
        Myavatar = globalVariable.getUser_avatar();
        Mymphone = globalVariable.getUser_mphone();
        Mywphone = globalVariable.getUser_wphone();
        Myaddress = globalVariable.getUser_address();
        Mytype_job = globalVariable.getUser_type_job();
        Myname_job = globalVariable.getUser_name_job();


        if (Myavatar != null && !Myavatar.isEmpty()) {
            imageUrl = LH_Destination_images+Image_Member_File+Myavatar;
        }
        new DownloadImageTask((ImageView) findViewById(R.id.imageavatar))
                .execute(imageUrl);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        method = extras.getString("EXTRA_Button");
        if(method.equals("Send_message")) {
            FirstTitle.setText(getResources().getString(R.string.Send_message_from_your_app));
            TextTitle.setText(getResources().getString(R.string.Send_sms));
            SendButton.setText(getResources().getString(R.string.Send_sms)+"   "+getString(R.string.fa_mail_forward));
            TitleMail.setHint(getResources().getString(R.string.Mobile_Phone_send));
            typeSend.setText(getResources().getString(R.string.My_phone) + Mymphone);
            TitleMail.setInputType(InputType.TYPE_CLASS_NUMBER);
            Toast.makeText(Send_from_App.this, method, Toast.LENGTH_SHORT).show();
        } else if (method.equals("Send_mail")) {
            FirstTitle.setText(getResources().getString(R.string.Send_mail_from_your_app));
            TextTitle.setText(getResources().getString(R.string.Send_Mail));
            SendButton.setText(getResources().getString(R.string.Send_Mail)+"   "+getString(R.string.fa_mail_forward));
            TitleMail.setHint(getResources().getString(R.string.Mail_to_send));
            TitleMail.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
            typeSend.setText(getResources().getString(R.string.My_mail) + MyEmail);
            Toast.makeText(Send_from_App.this, method, Toast.LENGTH_SHORT).show();
        }
    }

    public void SendButton(View view){
        submitForm();
    }

    private void submitForm() {
        if(method.equals("Send_message")) {
            if (!validatePhone()) { return; }
            if (!validateMessage()) { return; }

            SendTo = TitleMail.getText().toString();
            SendMessage = editText.getText().toString();
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(SendTo, null, SendMessage, null, null);
            Toast.makeText(Send_from_App.this, getResources().getString(R.string.Send_sms), Toast.LENGTH_SHORT).show();

        } else if (method.equals("Send_mail")) {
            if (!validateEmail()) { return; }
            if (!validateMessage()) { return; }
            SendTo = TitleMail.getText().toString();
            SendMessage = editText.getText().toString();

            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("message/rfc822");
            i.putExtra(Intent.EXTRA_EMAIL  , new String[]{SendTo});
            i.putExtra(Intent.EXTRA_SUBJECT, MyFirst + " " + MyLast + " Mail : " + MyEmail);
            i.putExtra(Intent.EXTRA_TEXT   , SendMessage);
            try {
                startActivity(Intent.createChooser(i, getResources().getString(R.string.Send_Mail)));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(Send_from_App.this, getResources().getString(R.string.Mail_error), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean validateEmail() {
        SendTo = TitleMail.getText().toString().trim();
        if (SendTo.isEmpty() || !isValidEmail(SendTo)) {
            input_layout_email.setError(getString(R.string.err_msg_email));
            requestFocus(TitleMail);
            return false;
        } else {
            input_layout_email.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatePhone() {
        SendTo = TitleMail.getText().toString().trim();
        if (SendTo.isEmpty()) {
            input_layout_email.setError(getString(R.string.err_msg_Phone));
            requestFocus(TitleMail);
            return false;
        } else {
            input_layout_email.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateMessage() {
        if (editText.getText().toString().trim().isEmpty()) {
            input_layout_editText.setError(getString(R.string.err_msg_message));
            requestFocus(editText);
            return false;
        } else {
            input_layout_editText.setErrorEnabled(false);
        }
        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
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
