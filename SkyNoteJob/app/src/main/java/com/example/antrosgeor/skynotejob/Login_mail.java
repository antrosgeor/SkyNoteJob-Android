package com.example.antrosgeor.skynotejob;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login_mail extends AppCompatActivity {

    String   Buttons, Title_Mail_No_User, Edit_email, Edit_First, Edit_Last, Edit_Phone, Edit_Message, Type, method = "No_User_Message";
    TextView Title;
    EditText Email, First_Name, Last_Name, Phone, Message;
    Button   Send_Mail;

    TextInputLayout input_layout_email, input_layout_First_Name, input_layout_Last_Name, input_layout_Phone;
    ConnectivityManager ConnectManager;
    NetworkInfo networkInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_mail);
        Title = (TextView) findViewById(R.id.Title);
        Email =(EditText) findViewById(R.id.Email);
        First_Name = (EditText) findViewById(R.id.First_Name);
        Last_Name = (EditText) findViewById(R.id.Last_Name);
        Phone = (EditText) findViewById(R.id.Phone);
        Message = (EditText)findViewById(R.id.Message);
        Send_Mail =(Button) findViewById(R.id.Send_Mail);

        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        Send_Mail.setTypeface(font);
        Send_Mail.setText(getResources().getString(R.string.Send_Mail)+"   "+getString(R.string.fa_mail_forward));

        input_layout_email = (TextInputLayout) findViewById(R.id.input_layout_email);
        input_layout_First_Name = (TextInputLayout) findViewById(R.id.input_layout_First_Name);
        input_layout_Last_Name = (TextInputLayout) findViewById(R.id.input_layout_Last_Name);
        input_layout_Phone = (TextInputLayout) findViewById(R.id.input_layout_Phone);

        Buttons = getIntent().getExtras().getString("Button");
        if(Buttons.equals("mail_admin")) {
            Title.setText(getString(R.string.Mail_Login_Title));
        }else if(Buttons.equals("forgot_pass")) {
            Title.setText(getString(R.string.forgot_Login_Title));
            Message.setVisibility(View.GONE);
        }else{
            Toast.makeText(getApplicationContext(), R.string.Error, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Login_mail.this, Login.class);
            startActivity(intent);
        }
    }

    public void No_User_Send_Message(View view){

        ConnectManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = ConnectManager.getActiveNetworkInfo();
        if (networkInfo!=null && networkInfo.isConnected()){
            submitForm();
        }else{
            Toast.makeText(Login_mail.this, R.string.Not_Internet, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Error_Internet.class);
            Bundle extras = new Bundle();
            extras.putString("activity_comes", "Login_mail");
            intent.putExtras(extras);
            startActivity(intent);
            finish();
        }
    }

    private void submitForm() {
        if (!validateEmail()) { return; }
        if (!validateAll()) { return; }

        Edit_email = Email.getText().toString();
        Edit_First = First_Name.getText().toString();
        Edit_Last = Last_Name.getText().toString();
        Edit_Phone = Phone.getText().toString();
        if(Buttons.equals("mail_admin")) {
            Title_Mail_No_User = "Mail To Admin";
            Edit_Message = Message.getText().toString();
            Type = "Error";
        }else if(Buttons.equals("forgot_pass")) {
            Title_Mail_No_User = "Forgot Pass";
            Edit_Message = "Please I forgot my password";
            Type = "Password";
        }
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method,Title_Mail_No_User,Type,Edit_email,Edit_First,Edit_Last,Edit_Phone,Edit_Message);
        Toast.makeText(getApplicationContext(), Title_Mail_No_User, Toast.LENGTH_LONG).show();
    }

    private boolean validateEmail() {
        Edit_email = Email.getText().toString().trim();
        if (Edit_email.isEmpty() || !isValidEmail(Edit_email)) {
            input_layout_email.setError(getString(R.string.err_msg_email));
            requestFocus(Email);
            return false;
        }else{
            input_layout_email.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateAll() {
        //First Name
        if (First_Name.getText().toString().trim().isEmpty()) {
            input_layout_First_Name.setError(getString(R.string.err_msg_First_Name));
            requestFocus(First_Name);
            return false;
        } else {
            input_layout_First_Name.setErrorEnabled(false);
        }
        //Last Name
        if (Last_Name.getText().toString().trim().isEmpty()) {
            input_layout_Last_Name.setError(getString(R.string.err_msg_Last_Name));
            requestFocus(Last_Name);
            return false;
        } else {
            input_layout_Last_Name.setErrorEnabled(false);
        }
        //Phone
        if (Phone.getText().toString().trim().isEmpty()) {
            input_layout_Phone.setError(getString(R.string.err_msg_Phone));
            requestFocus(Phone);
            return false;
        } else {
            input_layout_Phone.setErrorEnabled(false);
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
}