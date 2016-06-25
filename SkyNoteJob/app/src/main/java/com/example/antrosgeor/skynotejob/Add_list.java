package com.example.antrosgeor.skynotejob;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Add_list extends AppCompatActivity {
/** Values */
    int     My_ID;
    String  My_first, My_last;
    Intent intent;
    Bundle extras;
    Button Plus,Admin,Member,PlusContact,Send_Message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_list);
        Plus = (Button) findViewById(R.id.Plus);
        Admin = (Button) findViewById(R.id.Admin);
        Member = (Button) findViewById(R.id.Member);
        PlusContact = (Button) findViewById(R.id.PlusContact);
        Send_Message = (Button) findViewById(R.id.Send_Message);
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        Plus.setTypeface(font);
        Admin.setTypeface(font);
        Member.setTypeface(font);
        Send_Message.setTypeface(font);
        PlusContact.setTypeface(font);
        // B_Login.setText(    @string/Login);
        Plus.setText(getString(R.string.fa_plus)+"  "+getString(R.string.Add_Contact));
        Admin.setText(getString(R.string.fa_user_secret)+"  "+getString(R.string.Admin));
        Member.setText(getString(R.string.fa_users)+"  "+getString(R.string.Member));
        PlusContact.setText(getString(R.string.fa_user_plus)+"  "+getString(R.string.Plus_Contact));
        Send_Message.setText(getString(R.string.fa_comments)+"  "+getString(R.string.Send_Message));

        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
        My_ID =  globalVariable.getUser_id();
        My_first = globalVariable.getUser_first();
        My_last = globalVariable.getUser_last();
    }

    public void AddNotes(View view) {
        intent = new Intent(Add_list.this, Change_NR.class);
        extras = new Bundle();
        extras.putString("EXTRA_Button", "Notes_add");
        extras.putInt("EXTRA_My_ID", My_ID);
        extras.putString("EXTRA_My_first", My_first);
        extras.putString("EXTRA_My_last", My_last);
        intent.putExtras(extras);
        startActivity(intent);
    }

    public void AddRemember(View view) {
        intent = new Intent(Add_list.this, Change_NR.class);
        extras = new Bundle();
        extras.putString("EXTRA_Button", "Remember_add");
        extras.putInt("EXTRA_My_ID", My_ID);
        extras.putString("EXTRA_My_first", My_first);
        extras.putString("EXTRA_My_last", My_last);
        extras.putInt("EXTRA_My_send", My_ID);
        intent.putExtras(extras);
        startActivity(intent);
    }

    public void AddContact(View view) {
        intent = new Intent(getApplicationContext(), Change_profile.class);
        extras = new Bundle();
        extras.putString("EXTRA_Button", "Contact_Add");
        intent.putExtras(extras);
        startActivity(intent);
    }

    public void MailSendAdmin(View view) {
        intent = new Intent(Add_list.this, User_Menu.class);
        startActivity(intent);
    }
}