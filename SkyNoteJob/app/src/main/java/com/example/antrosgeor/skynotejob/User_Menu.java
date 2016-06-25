package com.example.antrosgeor.skynotejob;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class User_Menu extends AppCompatActivity {
    ListView listView= null;
    Button pages,news;
    String url, select_Button = " ", json_string, json_url, JSON_STRING = null, User_first, User_last, User_email, User_password, User_avatar, User_mphone, User_wphone, User_address,
            User_status, User_type_job, User_name_job;
    int User_id, login_time, count = 0, i = 0;
    String LH_Destination = GlobalClass.LH_Destination;
    String LH_Destination_images = GlobalClass.LH_Destination_images;
    String Image_Member_File = GlobalClass.Image_Member_File;
    String  imageUrl = LH_Destination_images+Image_Member_File+"no_avatar.png";
    ImageView ImageAvatar;
    TextView UserName, UserJob;
    JSONObject jsonObject;
    JSONArray jsonArray;
    String id, date_write, title, message, date, time, lever, color, action, id_member, id_second, comment, member_write, members_add;

    final String[] tid = new String[20];
    final String[] tdate_write = new String[20];
    final String[] ttitle = new String[20];
    final String[] tmessage = new String[20];
    final String[] tdate = new String[20];
    final String[] ttime = new String[20];
    final String[] tlever = new String[20];
    final String[] tcolor = new String[20];
    final String[] taction = new String[20];
    final String[] tid_member = new String[20];
    final String[] tid_second = new String[20];
    final String[] tcomment = new String[20];
    final String[] tmember_write = new String[20];
    final String[] tmembers_add = new String[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user__menu);
        pages = (Button) findViewById(R.id.Add_notes);
        news = (Button) findViewById(R.id.Add_remember);
        UserName = (TextView) findViewById(R.id.UserName);
        UserJob = (TextView) findViewById(R.id.UserJob);
        ImageAvatar = (ImageView) findViewById(R.id.ImageAvatar);
/** GlobalClass **/
        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        login_time = extras.getInt("First_Login");
        if (login_time == 1) {
            User_id = extras.getInt("EXTRA_User_id");
            globalVariable.setUser_id(User_id);
            User_first = extras.getString("EXTRA_User_first");
            globalVariable.setUser_first(User_first);
            User_last = extras.getString("EXTRA_User_last");
            globalVariable.setUser_last(User_last);
            User_email = extras.getString("EXTRA_User_email");
            globalVariable.setUser_email(User_email);
            User_password = extras.getString("EXTRA_User_password");
            globalVariable.setUser_password(User_password);
            User_avatar = extras.getString("EXTRA_User_avatar");
            globalVariable.setUser_avatar(User_avatar);
            User_mphone = extras.getString("EXTRA_User_mphone");
            globalVariable.setUser_mphone(User_mphone);
            User_wphone = extras.getString("EXTRA_User_wphone");
            globalVariable.setUser_wphone(User_wphone);
            User_address = extras.getString("EXTRA_User_address");
            globalVariable.setUser_address(User_address);
            User_status = extras.getString("EXTRA_User_status");
            globalVariable.setUser_status(User_status);
            User_type_job = extras.getString("EXTRA_User_type_job");
            globalVariable.setUser_type_job(User_type_job);
            User_name_job = extras.getString("EXTRA_User_name_job");
            globalVariable.setUser_name_job(User_name_job);
            login_time = 2;

            url = LH_Destination+"?key=now_remember&User_id="+User_id;
            select_Button="now_remember";
            new BackgroudTask().execute();
        }else if(login_time == 2){
            User_id = globalVariable.getUser_id();
            User_first = globalVariable.getUser_first();
            User_last = globalVariable.getUser_last();
            User_email = globalVariable.getUser_email();
            User_password = globalVariable.getUser_password();
            User_avatar = globalVariable.getUser_avatar();
            User_mphone = globalVariable.getUser_mphone();
            User_wphone = globalVariable.getUser_wphone();
            User_address = globalVariable.getUser_address();
            User_status = globalVariable.getUser_status();
            User_type_job = globalVariable.getUser_type_job();
            User_name_job = globalVariable.getUser_name_job();
        }
/** User Avatar images **/
        if (User_avatar != null && !User_avatar.isEmpty()) {
            imageUrl = LH_Destination_images+Image_Member_File+User_avatar;
        }
        new DownloadImageTask((ImageView) findViewById(R.id.ImageAvatar))
                .execute(imageUrl);

        UserName.setText(getString(R.string.Name) + " : " + User_first + " " + User_last);
        UserJob.setText(getString(R.string.Job) + " : " + User_name_job);
    }

/** Pages **/
    public void getPages(View view) {
        url = LH_Destination+"?key=pages&forto="+User_type_job;
        select_Button="pages";
        new BackgroudTask().execute();
    }
/** news **/
    public void getNews(View view) {
        select_Button="news";
        url = LH_Destination+"?key=news&forto="+User_type_job;
        new BackgroudTask().execute();
    }
/** notes **/
    public void getNotes(View view) {
        select_Button="notes";
        url = LH_Destination+"?key=notes&id_member="+User_id;
        new BackgroudTask().execute();
    }
/** Remember **/
    public void getRemember(View view) {
        select_Button="remember";
        url = LH_Destination+"?key=remember&id_member="+User_id;
        new BackgroudTask().execute();
    }
/** contact **/
    public void seeContact(View view) {
        select_Button="contact";
        Intent intent = new Intent (User_Menu.this, ContactApp.class);
        startActivity(intent);
    }
/** Plus **/
    public void AddPlus(View view) {
        String[] items = {getResources().getString(R.string.Add_Notes),getResources().getString(R.string.Add_Remember),getResources().getString(R.string.Add_Contact),getResources().getString(R.string.Send_Message),getResources().getString(R.string.Send_Mail)};

        AlertDialog.Builder builder = new AlertDialog.Builder(User_Menu.this);
        builder.setCancelable(true);
        builder.setView(listView);
        listView = new ListView(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_addplus, R.id.txtitem, items);
        listView.setAdapter(adapter);
        builder.setPositiveButton(R.string.cancel,null);
        final AlertDialog dialog = builder.create();
        dialog.show();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ViewGroup vg = (ViewGroup) view;
                TextView txt = (TextView) vg.findViewById(R.id.txtitem);
                String select = txt.getText().toString();
                if(select.equals(String.valueOf(getResources().getString(R.string.Add_Notes)))){
                    Intent intent = new Intent(User_Menu.this, Change_NR.class);
                    Bundle extras = new Bundle();
                    extras.putString("EXTRA_Button", "Notes_add");
                    extras.putInt("EXTRA_My_ID", User_id);
                    extras.putString("EXTRA_My_first", User_first);
                    extras.putString("EXTRA_My_last", User_last);
                    intent.putExtras(extras);
                    startActivity(intent);
                    Toast.makeText(User_Menu.this, R.string.Add_Notes, Toast.LENGTH_LONG).show();
                }else if(select.equals(getResources().getString(R.string.Add_Remember))){
                    Intent intent = new Intent(User_Menu.this, Change_NR.class);
                    Bundle extras = new Bundle();
                    extras.putString("EXTRA_Button", "Remember_add");
                    extras.putInt("EXTRA_My_ID", User_id);
                    extras.putString("EXTRA_My_first", User_first);
                    extras.putString("EXTRA_My_last",   User_last);
                    extras.putString("EXTRA_send", "0");
                    intent.putExtras(extras);
                    startActivity(intent);
                    Toast.makeText(User_Menu.this, R.string.Add_Remember, Toast.LENGTH_LONG).show();
                }else if(select.equals(getResources().getString(R.string.Add_Contact))){
                    Intent intent = new Intent(getApplicationContext(), Change_profile.class);
                    Bundle extras = new Bundle();
                    extras.putString("EXTRA_Button", "Contact_Add");
                    intent.putExtras(extras);
                    startActivity(intent);
                    Toast.makeText(User_Menu.this, R.string.Add_Contact , Toast.LENGTH_LONG).show();
                }else  if(select.equals(String.valueOf(getResources().getString(R.string.Send_Message)))){
                    Intent intent = new Intent(User_Menu.this, Send_from_App.class);
                    Bundle extras = new Bundle();
                    extras.putString("EXTRA_Button", "Send_message");
                    intent.putExtras(extras);
                    startActivity(intent);
                    Toast.makeText(User_Menu.this, R.string.Send_Message, Toast.LENGTH_LONG).show();
                } else if(select.equals(getResources().getString(R.string.Send_Mail))){
                    Intent intent = new Intent(User_Menu.this, Send_from_App.class);
                    Bundle extras = new Bundle();
                    extras.putString("EXTRA_Button", "Send_mail");
                    intent.putExtras(extras);
                    startActivity(intent);
                    Toast.makeText(User_Menu.this, R.string.Send_Mail, Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(User_Menu.this, R.string.cancel, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    class BackgroudTask extends AsyncTask<Void,Void,String>
    {
        @Override
        protected void onPreExecute() {
            json_url = url;
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while ((JSON_STRING = bufferedReader.readLine()) != null)
                {
                    stringBuilder.append((JSON_STRING+"\n"));
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }

        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            json_string = result;
            if (json_string != null) {
                if(select_Button.equals("pages")) { //pages
                    Intent intent = new Intent(User_Menu.this, List.class);
                    intent.putExtra("json_data", json_string);
                    intent.putExtra("Button", select_Button);
                    startActivity(intent);
                }else if(select_Button.equals("news")){ //news
                    Intent intent = new Intent(User_Menu.this, List.class);
                    intent.putExtra("json_data", json_string);
                    intent.putExtra("Button", select_Button);
                    startActivity(intent);
                }else if(select_Button.equals("notes")){ //notes
                    Intent intent = new Intent(User_Menu.this, List.class);
                    intent.putExtra("json_data", json_string);
                    intent.putExtra("Button", select_Button);
                    startActivity(intent);
                }else if(select_Button.equals("remember")){ //notes
                    Intent intent = new Intent(User_Menu.this, List.class);
                    intent.putExtra("json_data", json_string);
                    intent.putExtra("Button", select_Button);
                    startActivity(intent);
                }else if(select_Button.equals("now_remember")){ //notes
                    String id, date_write, title, message, date, time, lever, color, action, id_member, id_second, comment, member_write, members_add;
                    if(json_string.isEmpty()){
                        Toast.makeText(User_Menu.this, R.string.No_remember, Toast.LENGTH_LONG).show();
                    }else {
                        try {
                            jsonObject = new JSONObject(json_string);
                            jsonArray = jsonObject.getJSONArray("data");
                            count = 0;
                            while (count < jsonArray.length()) {
                                JSONObject JO = jsonArray.getJSONObject(count);
                                id = JO.getString("id");
                                date_write = JO.getString("date_write");
                                title = JO.getString("title");
                                message = JO.getString("message");
                                date = JO.getString("date");
                                time = JO.getString("time");
                                lever = JO.getString("lever");
                                color = JO.getString("color");
                                action = JO.getString("action");
                                id_member = JO.getString("id_member");
                                id_second = JO.getString("id_second");
                                comment = JO.getString("comment");
                                member_write = JO.getString("member_write");
                                members_add = JO.getString("members_add");

                                tid[count] = id;
                                tdate_write[count] = date_write;
                                ttitle[count] = title;
                                tmessage[count] = message;
                                tdate[count] = date;
                                ttime[count] = time;
                                tlever[count] = lever;
                                tcolor[count] = color;
                                taction[count] = action;
                                tid_member[count] = id_member;
                                tid_second[count] = id_second;
                                tcomment[count] = comment;
                                tmember_write[count] = member_write;
                                tmembers_add[count] = members_add;

                                count++;
                            }
                            Toast.makeText(User_Menu.this, count+" "+getResources().getString(R.string.Yes_remember), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                if(count != 0 && (select_Button.equals("now_remember") || select_Button.isEmpty())){
                    Toast.makeText(User_Menu.this, " den einai 0 ", Toast.LENGTH_LONG).show();
                    while (i < count) {
                        time = ttime[i];
                        Calendar calendar = Calendar.getInstance();
                        DateFormat df = new SimpleDateFormat("HH:mm:ss");
                        String date = df.format(Calendar.getInstance().getTime());

                        String dateHour   = date.substring(0, 2);
                        String dateMinute = date.substring(3, 5);
                        String dateSecond = date.substring(6, 8);

                        String Hour   = time.substring(0, 2);
                        String Minute = time.substring(3, 5);
                        String Second = time.substring(6, 8);

                        int AnHour = Integer.parseInt(dateHour);
                        int AnMinut = Integer.parseInt(dateMinute);
                        int AnSecond = Integer.parseInt(dateSecond);
                        int NoHour = Integer.parseInt(Hour);
                        int NoMinut = Integer.parseInt(Minute);
                        int NoSecond = Integer.parseInt(Second);

                        calendar.set(Calendar.HOUR_OF_DAY, NoHour);
                        calendar.set(Calendar.MINUTE, NoMinut);
                        calendar.set(Calendar.SECOND, NoSecond);
                        Intent intent1 = new Intent(getApplicationContext(),Notification_reciever.class);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),100,intent1,PendingIntent.FLAG_CANCEL_CURRENT);
                        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
                            i++;
                        Toast.makeText(User_Menu.this, "i ora tou kinitou : "+AnHour+" : "+AnMinut +" \ntora\n "+NoHour+" : "+NoMinut+" : "+NoSecond, Toast.LENGTH_LONG).show();

                    }
                }else if(count == 0 && (select_Button.equals("now_remember") || select_Button.isEmpty())){
                    Toast.makeText(User_Menu.this, " eimai 0 leme ", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

/** Back Pressed **/
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.Uprgade));
        builder.setMessage(getString(R.string.exit_question))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
                        int User_id = -1 ;
                        globalVariable.setUser_id(User_id);
                        moveTaskToBack(true);
                        finish();
                    }
                })
                .setNegativeButton(getString(R.string.No), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                builder.setNeutralButton(getString(R.string.Log_Out), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
                        int User_id = -1 ;
                        globalVariable.setUser_id(User_id);
                        User_Menu.this.finish();
                        Intent intent = new Intent(User_Menu.this, Login.class);
                        startActivity(intent);
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_profile) {
            startActivity(new Intent(this, Profile.class));
            return true;
        }
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, Settings.class));
            return true;
        }
        if (id == R.id.action_help) {
            startActivity(new Intent(this, Help_my.class));
            return true;
        }
        if (id == R.id.action_logout) {
            startActivity(new Intent(this, Login.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}