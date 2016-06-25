package com.example.antrosgeor.skynotejob;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.TypefaceProvider;

/** Created by ANTROS on 21/3/2016.
 * Login.class   -> activity_login
 */

public class Login extends AppCompatActivity {

    EditText Edit_Email, Edit_Pass;
    TextView LoginTextView;
    Button   B_Login;
    String   login_email, login_pass, method, email;
    ImageView ImageAvatar;
    TextInputLayout inputLayoutEmail, inputLayoutPassword;
    CheckBox checkBox_password;
    ConnectivityManager ConnectManager;
    NetworkInfo networkInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TypefaceProvider.registerDefaultIconSets();
        setContentView(R.layout.activity_login);
        Edit_Email = (EditText) findViewById(R.id.user_email);
        Edit_Pass = (EditText) findViewById(R.id.user_pass);
        LoginTextView = (TextView) findViewById(R.id.loginTextView);
        B_Login = (Button) findViewById(R.id.button_login);
        ImageAvatar = (ImageView) findViewById(R.id.ImageAvatar);
        checkBox_password = (CheckBox)findViewById(R.id.checkBox_password);

        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
        int User_id = -1 ;
        globalVariable.setUser_id(User_id);

        LoginTextView.setText(getString(R.string.Login) +" "+ GlobalClass.Company_Name);

        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_pass);

        show_password();
    }

    /** Show Password **/
    public void show_password() {
        checkBox_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked) {
                    Edit_Pass.setInputType(129); //129 is the input type set when setting android:inputType="textPassword"
                } else {
                    Edit_Pass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
    }

    /** Button Login. **/
    public void userLogin(View view) {
        ConnectManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = ConnectManager.getActiveNetworkInfo();
        if (networkInfo!=null && networkInfo.isConnected()){
            submitForm();
        } else {
            Toast.makeText(Login.this, R.string.Not_Internet, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Error_Internet.class);
            Bundle extras = new Bundle();
            extras.putString("activity_comes", "Login");
            intent.putExtras(extras);
            startActivity(intent);
            finish();
        }
    }

    /** Check value from EditText. **/
    private void submitForm() {
        if (!validateEmail()) { return; }
        if (!validatePassword()) { return; }

            final ProgressDialog pd = new ProgressDialog(this);
            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pd.setMessage(getString(R.string.Just_Wait));
            pd.setIndeterminate(true);
            pd.setCancelable(true);
            pd.show();
            login_email = Edit_Email.getText().toString();
            login_pass = Edit_Pass.getText().toString();
            method = "login";
            // Run class. BackgroundTask.class.
            BackgroundTask backgroundTask = new BackgroundTask(this);
            //Send String to BackgroundTask.class
            backgroundTask.execute(method,login_email,login_pass);
    }

    /** Email Check value EditText. **/
    private boolean validateEmail() {
        email = Edit_Email.getText().toString().trim();
        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            requestFocus(Edit_Email);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }
        return true;
    }

    /** Password Check value EditText. **/
    private boolean validatePassword() {
        if (Edit_Pass.getText().toString().trim().isEmpty()) {
            inputLayoutPassword.setError(getString(R.string.err_msg_password));
            requestFocus(Edit_Pass);
            return false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            startActivity(new Intent(this, Settings.class));
            return true;
        }
        if (id == R.id.action_help) {
            startActivity(new Intent(this, Help_my.class));
            return true;
        }
        if (id == R.id.action_Mail_admin) {
            Intent intent = new Intent(Login.this, Login_mail.class);
            intent.putExtra("Button", "mail_admin");
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_forgot_password) {
            Intent intent = new Intent(Login.this, Login_mail.class);
            intent.putExtra("Button", "forgot_pass");
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /** Back Pressed **/
    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.Exit_from_the_app));
        builder.setMessage(getString(R.string.exit_question))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        moveTaskToBack(true);
                        finish();
                    }
                })
                .setNegativeButton(getString(R.string.No), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}