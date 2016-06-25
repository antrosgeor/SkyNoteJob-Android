package com.example.antrosgeor.skynotejob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Repeating_activity extends AppCompatActivity {
    TextView Remember_title,Remember_text,name_company;
    Button Remember_button,close_remember;
    String company;
    int User_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repeating_activity);
        Remember_button = (Button) findViewById(R.id.Remember_button);
        close_remember = (Button) findViewById(R.id.close_remember);
        Remember_title = (TextView) findViewById(R.id.remember_title);
        Remember_text = (TextView) findViewById(R.id.Remember_text);
        name_company = (TextView) findViewById(R.id.name_company);

        company = GlobalClass.Company_Name;
        name_company.setText(company);


        Remember_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
                User_id = globalVariable.getUser_id();
                if (User_id == -1 ){
                    Intent intent = new Intent(Repeating_activity.this, Login.class);
                    startActivity(intent);
                    Repeating_activity.this.finish();
                }else{
                    Intent intent = new Intent(Repeating_activity.this, User_Menu.class);
                    Bundle extras = new Bundle();
                    extras.putInt("First_Login", 2);
                    Repeating_activity.this.finish();
                    intent.putExtras(extras);
                    startActivity(intent);
                }
            }
        });

        close_remember.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
                int User_id = -1 ;
                globalVariable.setUser_id(User_id);
                moveTaskToBack(true);
                Repeating_activity.this.finish();
                finish();
            }
        });
    }
}
