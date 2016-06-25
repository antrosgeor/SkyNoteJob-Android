package com.example.antrosgeor.skynotejob;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Error_Internet extends AppCompatActivity {
    TextView Name_Company, connection_lost, retry_Button, back_Button;
    ImageView imageView;
    ConnectivityManager ConnectManager;
    NetworkInfo networkInfo;
    String activity_comes, myClass;
    Intent intent;
    Bundle extras;
    Class<?> clazz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.error__internet);
        Name_Company = (TextView) findViewById(R.id.Name_Company);
        connection_lost = (TextView) findViewById(R.id.connection_lost);
        back_Button = (TextView) findViewById(R.id.back);
        retry_Button = (TextView) findViewById(R.id.tap_to_retry);
        imageView = (ImageView) findViewById(R.id.imageView);

        Name_Company.setText(GlobalClass.Company_Name);

        intent = getIntent();
        extras = intent.getExtras();
        activity_comes = extras.getString("activity_comes");

        myClass = activity_comes+".class";

        try {
            clazz = Class.forName(myClass);
        } catch (ClassNotFoundException e) {
            clazz = Login.class;
            e.printStackTrace();
        }

        retry();
        back();
    }
    public void retry(){
        //retry
        retry_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                networkInfo = ConnectManager.getActiveNetworkInfo();
                if (networkInfo!=null && networkInfo.isConnected()){
                    intent = new Intent(Error_Internet.this, clazz);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.Not_Internet, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void back(){
        //retry
        back_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Error_Internet.this, Login.class);
                startActivity(intent);
            }
        });
    }
}
