package com.example.antrosgeor.skynotejob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Show extends AppCompatActivity {
    TextView    TextTitle, TextDate,  TextForto, TextUser, TextBody_message;
    String      ButtonSelect, title, header, body, label, slug, type, forto, user, id, date, author, type_job;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show);
        TextTitle = (TextView) findViewById(R.id.TextTitle);
        TextDate = (TextView) findViewById(R.id.TextDate);
        TextForto = (TextView) findViewById(R.id.TextForto);
        TextUser = (TextView) findViewById(R.id.TextUser);
        TextBody_message = (TextView) findViewById(R.id.TextBody);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        ButtonSelect = extras.getString("EXTRA_Button");
        if(ButtonSelect.equals("pages")) {
            title  = extras.getString("EXTRA_title");
            header = extras.getString("EXTRA_header");
            body = extras.getString("EXTRA_body");
            label = extras.getString("EXTRA_label");
            slug = extras.getString("EXTRA_slug");
            type = extras.getString("EXTRA_type");
            forto = extras.getString("EXTRA_forto");
            user = extras.getString("EXTRA_user");
            id = extras.getString("EXTRA_id");
//show
            TextTitle.setText(title);
            TextDate.setVisibility(View.GONE);
            TextUser.setText(getString(R.string.by)+" : " + user);
            TextForto.setText(getString(R.string.For)+" : " + forto );
            TextBody_message.setText(body);
        }else if(ButtonSelect.equals("news")) {
            title  = extras.getString("EXTRA_title");
            body = extras.getString("EXTRA_body");
            date = extras.getString("EXTRA_date");
            author = extras.getString("EXTRA_author");
            type_job = extras.getString("EXTRA_type_job");
            forto = extras.getString("EXTRA_forto");
            id = extras.getString("EXTRA_id");
//show
            TextTitle.setText(title);
            TextDate.setText(date);
            TextUser.setText(getString(R.string.by)+" : " + author);
            TextForto.setText(getString(R.string.For)+" : " + forto);
            TextBody_message.setText(body);
        }else if(ButtonSelect.isEmpty()){
            Intent error = new Intent(Show.this, User_Menu.class);
            Toast.makeText(Show.this , getString(R.string.Error),Toast.LENGTH_LONG).show();
            startActivity(error);
        }
    }
}