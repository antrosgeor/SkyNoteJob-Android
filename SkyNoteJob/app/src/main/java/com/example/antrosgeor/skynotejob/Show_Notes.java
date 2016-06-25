package com.example.antrosgeor.skynotejob;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Show_Notes extends AppCompatActivity {
    TextView TV_title, TV_body, TV_date, TV_lever, TV_color, TV_member, note_header;
    Button   change, delete;
    String   title, date, body, lever, color, member, sid, id_member, button, id_second, date_write, time,
             member_write, action, member_add, method;
    int      User_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_notes);
        note_header = (TextView)findViewById(R.id.note_header);
        TV_title = (TextView) findViewById(R.id.Text_Title);
        TV_body = (TextView) findViewById(R.id.body);
        TV_date = (TextView) findViewById(R.id.date);
        TV_lever = (TextView) findViewById(R.id.lever);
        TV_member = (TextView) findViewById(R.id.member);
        change = (Button) findViewById(R.id.change);
        delete = (Button) findViewById(R.id.delete);
        final View rootView = findViewById(android.R.id.content);

        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        change.setTypeface(font);
        delete.setTypeface(font);
        change.setText(getResources().getString(R.string.fa_cloud_download)+"  "+getResources().getString(R.string.change));
        delete.setText(getResources().getString(R.string.fa_trash_o)+"  "+getResources().getString(R.string.delete));


        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
        User_id = globalVariable.getUser_id();

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        button = extras.getString("EXTRA_Button");
        if(button.equals("notes")) {
            title = extras.getString("EXTRA_title");
            date = extras.getString("EXTRA_date");
            body = extras.getString("EXTRA_body");
            lever = extras.getString("EXTRA_lever");
            color = extras.getString("EXTRA_color");
            member = extras.getString("EXTRA_member");
            sid = extras.getString("EXTRA_id");
            id_member = extras.getString("EXTRA_id_member");
//show
            note_header.setText(getString(R.string.notes));
            TV_title.setText(title);
            TV_body.setText(body);
            TV_date.setText(date);
            TV_lever.setText(getString(R.string.lever) +":\n "+lever);
            TV_member.setVisibility(View.GONE);
        }else if(button.equals("remember")) {
            sid = extras.getString("EXTRA_id");
            id_member =extras.getString("EXTRA_id_member");
            id_second = extras.getString("EXTRA_id_second");
            title = extras.getString("EXTRA_title");
            date = extras.getString("EXTRA_date");
            body = extras.getString("EXTRA_message");
            lever = extras.getString("EXTRA_lever");
            color = extras.getString("EXTRA_color");
            date_write = extras.getString("EXTRA_date_write");
            time = extras.getString("EXTRA_time");
            action = extras.getString("EXTRA_action");
            member_write = extras.getString("EXTRA_member_write");
            member_add = extras.getString("EXTRA_members_add");
//show
            note_header.setText(getString(R.string.remember));
            TV_title.setText(title);
            TV_body.setText(getString(R.string.written)+" "+ date_write+"\n\n\t"+body+"\n\n\n"+getString(R.string.action)+" "+action);
            TV_date.setText(getString(R.string.For)+" "+date +"\n"+time);
            TV_lever.setText(getString(R.string.lever) +":\n "+lever);
            if(member_add.equals(member_write) || member_add == null) {
                TV_member.setVisibility(View.GONE);
            }else{
                TV_member.setText(member_add);
            }
        }

        //  info/success/warning//danger
        if(color.equals("info")){
            TV_title.setBackgroundResource(R.color.info);
            TV_body.setBackgroundResource(R.color.info);
            rootView.setBackgroundResource(R.color.info);
        }else if(color.equals("success")){
            TV_title.setBackgroundResource(R.color.success);
            TV_body.setBackgroundResource(R.color.success);
            rootView.setBackgroundResource(R.color.success);
        }else if(color.equals("warning")){
            TV_title.setBackgroundResource(R.color.warning);
            TV_body.setBackgroundResource(R.color.warning);
            rootView.setBackgroundResource(R.color.warning);
        }else if(color.equals("danger")){
            TV_title.setBackgroundResource(R.color.danger);
            TV_body.setBackgroundResource(R.color.danger);
            rootView.setBackgroundResource(R.color.danger);
        }else if(color.isEmpty()){
            TV_title.setBackgroundResource(R.color.primary);
            TV_body.setBackgroundResource(R.color.primary);
            rootView.setBackgroundResource(R.color.primary);
        }
    }

    public void DeleteNote(View view){
        //Notes, sid
        AlertDialog.Builder a_builder = new AlertDialog.Builder(
                Show_Notes.this);
        if(button.equals("notes")) {
            a_builder.setMessage(getString(R.string.Delete_Note_question))
                    .setCancelable(false)
                    .setPositiveButton(getString(R.string.yes),
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    // TODO Auto-generated method stub
                                    Delete_Note_Remember_Id();
                                    finish();
                                }
                            })
                    .setNegativeButton(getString(R.string.No),
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    // TODO Auto-generated method stub
                                    dialog.cancel();
                                }
                            });
            AlertDialog alert = a_builder.create();
            alert.setTitle(getString(R.string.Delete_Notes));
            alert.setIcon(R.drawable.delete_black);
            alert.show();
        }else if(button.equals("remember")){
            a_builder.setMessage(getString(R.string.Delete_Remember_question))
                    .setCancelable(false)
                    .setPositiveButton(getString(R.string.yes),
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    // TODO Auto-generated method stub
                                    Delete_Note_Remember_Id();
                                    finish();
                                }
                            })
                    .setNegativeButton(getString(R.string.No),
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    // TODO Auto-generated method stub
                                    dialog.cancel();
                                }
                            });
            AlertDialog alert = a_builder.create();
            alert.setTitle(getString(R.string.Delete_Remember));
            alert.setIcon(R.drawable.delete_black);
            alert.show();
        }
    }

    private void Delete_Note_Remember_Id() {
        method = button + "Delete";
            Toast.makeText(this, method, Toast.LENGTH_LONG).show();
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method, sid, String.valueOf(User_id));
    }

    public void ChangeNote(View view){
        //Notes, sid
        if (button.equals("notes")) {
            Intent intent = new Intent(Show_Notes.this, Change_NR.class);
            Bundle extras = new Bundle();
            extras.putString("EXTRA_Button", "Notes_change");
            extras.putString("EXTRA_Id", sid);
            extras.putString("EXTRA_title", title);
            extras.putString("EXTRA_date", date);
            extras.putString("EXTRA_body", body);
            extras.putString("EXTRA_lever", lever);
            extras.putString("EXTRA_color", color);
            extras.putString("EXTRA_member", member);
            extras.putString("EXTRA_id_member", id_member);
            intent.putExtras(extras);
            startActivity(intent);
        }else if(button.equals("remember")){
            Intent intent = new Intent(Show_Notes.this, Change_NR.class);
            Bundle extras = new Bundle();
            extras.putString("EXTRA_Button", "Remember_change");
            extras.putString("EXTRA_Id", sid);
            extras.putString("EXTRA_id_member", id_member);
            extras.putString("EXTRA_id_second", id_second);
            extras.putString("EXTRA_title", title);
            extras.putString("EXTRA_date", date);
            extras.putString("EXTRA_message", body);
            extras.putString("EXTRA_lever", lever);
            extras.putString("EXTRA_color", color);
            extras.putString("EXTRA_date_write", date_write);
            extras.putString("EXTRA_time", time);
            extras.putString("EXTRA_action", action);
            extras.putString("EXTRA_member_write", member_write);
            extras.putString("EXTRA_members_add", member_add);
            intent.putExtras(extras);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}