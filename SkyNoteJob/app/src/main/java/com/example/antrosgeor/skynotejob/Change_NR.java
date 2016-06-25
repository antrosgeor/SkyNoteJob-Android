package com.example.antrosgeor.skynotejob;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class Change_NR extends AppCompatActivity {
/** Values */
    String  method, Id_Note, selectLever, selectTitle, selectBody, selectColor, My_last, My_first, comment, Member_avatar,
            title_change, body_change, date_change, lever_change, color_change, member_change, id_member_change,
            id_second_change, date_write_change, time_change, action_change, member_write_change, member_add_change,
            selectDate, selectTime, Member_id, Member_first, Member_last, FinalTime = "", FinalDate = "";
    int selectedIdlever, selectedIdColor, My_ID, User_id,My_send, year_x, month_x, day_x, hout_x, minute_x;
    TextView Header, Text_Title, TextBody, TextLever, TextColor, TextDate, TextTime;
    EditText editTitle, editBody, editDate, editTime;
    RadioGroup radioLever, radioColor;
    RadioButton radioButtonLever, radioButtonColor;
    Button FinalButton;
    static final int DIALOG_ID = 0;
    static final int DIALOG_ID_day = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_nr);
        radioLever = (RadioGroup) findViewById(R.id.radioLever);
        radioColor = (RadioGroup) findViewById(R.id.radioColor);
        Header = (TextView) findViewById(R.id.Header);
        Text_Title = (TextView) findViewById(R.id.Text_Title);
        TextBody = (TextView) findViewById(R.id.TextBody);
        TextLever = (TextView) findViewById(R.id.TextLever);
        TextColor = (TextView) findViewById(R.id.TextColor);
        TextDate = (TextView) findViewById(R.id.TextDate);
        TextTime = (TextView) findViewById(R.id.TextTime);
        FinalButton = (Button) findViewById(R.id.FinalButton);
        editTitle = (EditText) findViewById(R.id.editTitle);
        editBody = (EditText) findViewById(R.id.editBody);
        editDate = (EditText) findViewById(R.id.editDate);
        editTime = (EditText) findViewById(R.id.editTime);
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        TextDate.setTypeface(font);
        TextTime.setTypeface(font);
        FinalButton.setTypeface(font);

        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
        User_id = globalVariable.getUser_id();

        TextDate.setText(getString(R.string.fa_calendar)+"  "+getString(R.string.date));
        TextTime.setText(getString(R.string.fa_clock_o)+"  "+getString(R.string.time));
        showTimePickerDialoog();
        showDialoogDay();

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        method = extras.getString("EXTRA_Button");
        if(method.equals("Notes_change")){
// get Data from from another activity
            Id_Note = extras.getString("EXTRA_Id");
            title_change= extras.getString("EXTRA_title");
            date_change = extras.getString("EXTRA_date");
            body_change = extras.getString("EXTRA_body");
            lever_change = extras.getString("EXTRA_lever");
            color_change = extras.getString("EXTRA_color");
            member_change =  extras.getString("EXTRA_member");
            id_member_change = extras.getString("EXTRA_id_member");
// Visibility
            TextDate.setVisibility(View.GONE);
            TextTime.setVisibility(View.GONE);
            editDate.setVisibility(View.GONE);
            editTime.setVisibility(View.GONE);
// Show
            Header.setText(getString(R.string.Change_Note));
            FinalButton.setText(getString(R.string.fa_cloud_upload)+"   "+getString(R.string.Notes_Change));
            editTitle.setText(title_change);
            editBody.setText(body_change);
        }else  if(method.equals("Remember_change")){
// get Data from from another activity
            Id_Note = extras.getString("EXTRA_Id");
            id_member_change = extras.getString("EXTRA_id_member");
            id_second_change = extras.getString("EXTRA_id_second");
            title_change = extras.getString("EXTRA_title");
            date_change =  extras.getString("EXTRA_date");
            body_change = extras.getString("EXTRA_message");
            lever_change = extras.getString("EXTRA_lever");
            color_change = extras.getString("EXTRA_color");
            date_write_change = extras.getString("EXTRA_date_write");
            time_change = extras.getString("EXTRA_time");
            action_change = extras.getString("EXTRA_action");
            member_write_change = extras.getString("EXTRA_member_write");
            member_add_change = extras.getString("EXTRA_members_add");
// Show
            Header.setText(getString(R.string.Change_Remember));
            FinalButton.setText(getString(R.string.fa_cloud_upload)+"   "+getString(R.string.Change_Remember));
            editTitle.setText(title_change);
            editBody.setText(body_change);
            editTime.setText(time_change);
            editDate.setText(date_change);
        }else if(method.equals("Notes_add")){
// get Data from from another activity
            My_ID = extras.getInt("EXTRA_My_ID");
            My_first = extras.getString("EXTRA_My_first");
            My_last = extras.getString("EXTRA_My_last");
// Visibility
            TextDate.setVisibility(View.GONE);
            TextTime.setVisibility(View.GONE);
            editDate.setVisibility(View.GONE);
            editTime.setVisibility(View.GONE);
// Show
            Header.setText(getString(R.string.Add_New_Note));
            FinalButton.setText(getString(R.string.fa_cloud_upload)+"   "+getString(R.string.Add_Notes));
        }else if(method.equals("Remember_add")){
// get Data from from another activity
            My_ID = extras.getInt("EXTRA_My_ID");
            My_first = extras.getString("EXTRA_My_first");
            My_last = extras.getString("EXTRA_My_last");
            My_send = extras.getInt("EXTRA_My_send");
// Show
            Header.setText(getString(R.string.Add_New_Remember));
            FinalButton.setText(getString(R.string.fa_cloud_upload)+"   "+getString(R.string.Add_Remember));
        }else if(method.equals("Remember_Member")) {
// get Data from from another activity
            Member_id =extras.getString("EXTRA__Member_Id");
            Member_first =  extras.getString("EXTRA__Member_first");
            Member_last = extras.getString("EXTRA__Member_last");
            Member_avatar = extras.getString("EXTRA__Member_avatar");
// Show
            Header.setText(getString(R.string.remember)+" "+Member_first +" "+Member_last);
            FinalButton.setText(getString(R.string.fa_cloud_upload)+"   "+getString(R.string.Remember_to)+" "+Member_first +" "+Member_last);
        }else{
// Show
            FinalButton.setText(getString(R.string.fa_times)+"   "+getString(R.string.Error));
        }
    }

    /** Date */
    public void showDialoogDay (){
        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_ID_day);
            }
        });
        TextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_ID_day);
            }
        });
    }
    /** Time */
    public void showTimePickerDialoog (){
        editTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_ID);
            }
        });
        TextTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_ID);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id){
        if(id == DIALOG_ID) {
            return new TimePickerDialog(Change_NR.this, kTimePickerListner, hout_x, minute_x, false);
        }else if (id == DIALOG_ID_day){
            return new DatePickerDialog(this, dpickerListner,year_x,month_x,day_x);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener dpickerListner =new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfYear){
            year_x = year;
            month_x = monthOfYear + 1;
            day_x = dayOfYear;
            Toast.makeText(Change_NR.this, year_x + "/"+month_x+ "/"+day_x,Toast.LENGTH_LONG).show();
            FinalDate = year_x + "/"+month_x+ "/"+day_x;
            editDate.setText(FinalDate);
        }
    };

    private TimePickerDialog.OnTimeSetListener kTimePickerListner = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute){
            hout_x = hourOfDay;
            minute_x = minute;
            Toast.makeText(Change_NR.this,hout_x + " : " +minute_x,Toast.LENGTH_LONG).show();
            FinalTime = hout_x + " : " +minute_x+" : 00";
            editTime.setText(FinalTime);
        }
    };

    public void FinalButton(View view)    {
        if(method.equals("Notes_change")){
// Get String for this Activity
            selectBody = editBody.getText().toString();
            selectTitle = editTitle.getText().toString();

            selectedIdlever = radioLever.getCheckedRadioButtonId();
            radioButtonLever = (RadioButton) findViewById(selectedIdlever);
            selectLever = radioButtonLever.getText().toString();

            selectedIdColor = radioColor.getCheckedRadioButtonId();
            radioButtonColor = (RadioButton) findViewById(selectedIdColor);
            selectColor = radioButtonColor.getText().toString();
// Send data tou BackgroundTask
            BackgroundTask backgroundTask = new BackgroundTask(this);
            backgroundTask.execute(method, selectTitle, selectBody, selectLever, selectColor, String.valueOf(My_ID), Id_Note);
// Show to Toast
            Toast.makeText(Change_NR.this, "Change Notes\n\n"+"title : "+selectTitle+"\n Body : "+
                            selectBody+"\n color : "+selectColor+"\n lever : "+selectLever+"\n\n :"+Id_Note,
                    Toast.LENGTH_SHORT).show();
        }else  if(method.equals("Remember_change")){
// Get String for this Activity
            selectBody = editBody.getText().toString();
            selectTitle = editTitle.getText().toString();
            selectDate = editDate.getText().toString();
            selectTime = editTime.getText().toString();

            selectedIdlever = radioLever.getCheckedRadioButtonId();
            radioButtonLever = (RadioButton) findViewById(selectedIdlever);
            selectLever = radioButtonLever.getText().toString();

            selectedIdColor = radioColor.getCheckedRadioButtonId();
            radioButtonColor = (RadioButton) findViewById(selectedIdColor);
            selectColor = radioButtonColor.getText().toString();
// Send data tou BackgroundTask
            if(FinalTime.equals("") || FinalDate.equals("")) {
                Toast.makeText(Change_NR.this,  R.string.error_null_time_date, Toast.LENGTH_SHORT).show();
            }else {
                BackgroundTask backgroundTask = new BackgroundTask(this);
                backgroundTask.execute(method, selectTitle, selectBody, selectDate, selectTime, selectLever, selectColor, String.valueOf(User_id), Id_Note);
// Show to Toast
                Toast.makeText(Change_NR.this, "Change Remember\n\n" + "title : " + selectTitle + "\n Body : " +
                                selectBody + "\n color : " + selectColor + "\n lever : " + selectLever +"\n time : " + selectTime +"\n  : " + selectDate + "\n\n :" + Id_Note,
                        Toast.LENGTH_SHORT).show();
            }
        }else if(method.equals("Notes_add")){
// Get String for this Activity
            selectTitle = editTitle.getText().toString();
            selectBody = editBody.getText().toString();

            selectedIdlever = radioLever.getCheckedRadioButtonId();
            radioButtonLever = (RadioButton) findViewById(selectedIdlever);
            selectLever = radioButtonLever.getText().toString();

            selectedIdColor = radioColor.getCheckedRadioButtonId();
            radioButtonColor = (RadioButton) findViewById(selectedIdColor);
            selectColor = radioButtonColor.getText().toString();
// Send data tou BackgroundTask
            BackgroundTask backgroundTask = new BackgroundTask(this);
            backgroundTask.execute(method, selectTitle, selectBody, selectLever, selectColor, String.valueOf(My_ID));
// Show to Toast
            Toast.makeText(Change_NR.this, "ADD Notes\n\n"+"title : "+selectTitle+"\n Body : "+
                            selectBody+"\n color : "+selectColor+"\n lever : "+selectLever+"\n\n ID :"+Id_Note,
                    Toast.LENGTH_SHORT).show();
        }else if(method.equals("Remember_add")){
// Get String for this Activity
            selectBody = editBody.getText().toString();
            selectTitle = editTitle.getText().toString();
            selectDate = editDate.getText().toString();
            selectTime = editTime.getText().toString();

            selectedIdlever = radioLever.getCheckedRadioButtonId();
            radioButtonLever = (RadioButton) findViewById(selectedIdlever);
            selectLever = radioButtonLever.getText().toString();

            selectedIdColor = radioColor.getCheckedRadioButtonId();
            radioButtonColor = (RadioButton) findViewById(selectedIdColor);
            selectColor = radioButtonColor.getText().toString();
            comment = My_first +" "+My_last;

            if(FinalTime.equals("") || FinalDate.equals("")) {
                Toast.makeText(Change_NR.this,  R.string.error_null_time_date, Toast.LENGTH_SHORT).show();
            }else{
// Send data tou BackgroundTask
                BackgroundTask backgroundTask = new BackgroundTask(this);
                backgroundTask.execute(method, selectTitle, selectBody, selectDate, selectTime, selectLever, selectColor, String.valueOf(My_ID), String.valueOf(My_send), comment);
// Show to Toast
                Toast.makeText(Change_NR.this, "ADD Remember\n" + Member_id + " \n" + "title : " + selectTitle + "\n Body : " +
                                selectBody + "\n color : " + selectColor + "\n lever : " + selectLever + "\ndate :" + selectDate +
                                "\ntime :" + selectTime + "\n\n :" + Id_Note + User_id + "\ntime = "+FinalTime+ "\ndate = "+FinalDate,
                        Toast.LENGTH_SHORT).show();
            }
        }else if(method.equals("Remember_Member")){
// Get String for this Activity
            selectBody = editBody.getText().toString();
            selectTitle = editTitle.getText().toString();
            selectDate = editDate.getText().toString();
            selectTime = editTime.getText().toString();

            selectedIdlever = radioLever.getCheckedRadioButtonId();
            radioButtonLever = (RadioButton) findViewById(selectedIdlever);
            selectLever = radioButtonLever.getText().toString();

            selectedIdColor = radioColor.getCheckedRadioButtonId();
            radioButtonColor = (RadioButton) findViewById(selectedIdColor);
            selectColor = radioButtonColor.getText().toString();
            if(FinalTime.equals("") || FinalDate.equals("")) {
                Toast.makeText(Change_NR.this,  R.string.error_null_time_date, Toast.LENGTH_SHORT).show();
            }else{
// Send data tou BackgroundTask
                BackgroundTask backgroundTask = new BackgroundTask(this);
                backgroundTask.execute(method, selectTitle, selectBody, selectLever, selectColor, FinalDate, FinalTime, String.valueOf(User_id), Member_id );
// Show to Toast
                Toast.makeText(Change_NR.this, "ADD Remember\n" + Member_id + " \n" + "title : " + selectTitle + "\n Body : " +
                                selectBody + "\n color : " + selectColor + "\n lever : " + selectLever + "\ndate :" + selectDate +
                                "\ntime :" + selectTime + "\n\n :" + Id_Note + User_id + "\ntime = "+FinalTime+ "\ndate = "+FinalDate,
                        Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(Change_NR.this, getString(R.string.Error), Toast.LENGTH_SHORT).show();
        }
    }
}