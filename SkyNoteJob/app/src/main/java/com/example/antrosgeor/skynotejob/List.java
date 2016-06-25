package com.example.antrosgeor.skynotejob;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class List extends AppCompatActivity {

    JSONObject jsonObject;
    JSONArray jsonArray;
    ContactAdapter contactAdapter;
    ListView listView;
    final int[] iid = new int[20];
    final int[] iid_member = new int[20];
    final int[] iid_second = new int[20];
    final String[] ititle = new String[20];
    final String[] idate = new String[20];
    final String[] ibody = new String[20];
    final String[] iauthor = new String[20];
    final String[] itype_job = new String[20];
    final String[] iheader = new String[20];
    final String[] ilabel = new String[20];
    final String[] islug = new String[20];
    final String[] itype = new String[20];
    final String[] iuser = new String[20];
    final String[] iforto = new String[20];
    final String[] ilever = new String[20];
    final String[] icolor = new String[20];
    final String[] imember = new String[20];
    final String[] idate_write = new String[20];
    final String[] imessage = new String[20];
    final String[] itime = new String[20];
    final String[] iaction = new String[20];
    final String[] imembers_add = new String[20];
    final String[] imember_write = new String[10];

    int     count = 0;
    String  json_string, Button, header, label, slug, type, user, author, type_job, forto, body, member, id, id_member,
            title, date, lever, color, date_write, message, time, action, id_second, member_write, members_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        listView = (ListView) findViewById(R.id.listview);

        contactAdapter = new ContactAdapter(this, R.layout.row_layout);
        listView.setAdapter(contactAdapter);
        listView.setOnItemClickListener(new NewsItemClickListener());

        json_string = getIntent().getExtras().getString("json_data");
        Button = getIntent().getExtras().getString("Button");

        if(Button.equals("pages")) {
            try {
                jsonObject = new JSONObject(json_string);
                jsonArray = jsonObject.getJSONArray("data");

                while (count<jsonArray.length())
                {
                    JSONObject JO = jsonArray.getJSONObject(count);
                    id = JO.getString("id");
                    title = JO.getString("title");
                    header = JO.getString("header");
                    body = JO.getString("body");
                    label = JO.getString("label");
                    slug = JO.getString("slug");
                    type = JO.getString("type");
                    forto = JO.getString("forto");
                    user = JO.getString("user");

                    ititle[count] = JO.getString("title");
                    iheader[count] = JO.getString("header");
                    ibody[count] = JO.getString("body");
                    ilabel[count] = JO.getString("label");
                    islug[count] = JO.getString("slug");
                    itype[count] = JO.getString("type");
                    iforto[count] = JO.getString("forto");
                    iuser[count] = JO.getString("user");
                    iid[count] = Integer.parseInt(JO.getString("id"));

                    Contacts contacts = new Contacts(id,title,forto,Button);
                    contactAdapter.add(contacts);
                    count++;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else if(Button.equals("news")) {
            try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("data");

            while (count<jsonArray.length())
            {
                JSONObject JO = jsonArray.getJSONObject(count);
                id = JO.getString("id");
                title = JO.getString("title");
                date = JO.getString("date");
                body = JO.getString("body");
                author = JO.getString("author");
                type_job = JO.getString("type_job");
                forto = JO.getString("forto");

                iid[count] = Integer.parseInt(JO.getString("id"));
                ititle[count] = JO.getString("title");
                idate[count] = JO.getString("date");
                ibody[count] = JO.getString("body");
                iauthor[count] = JO.getString("author");
                itype_job[count] = JO.getString("type_job");
                iforto[count] = JO.getString("forto");

                Contacts contacts = new Contacts(title,date,forto, Button);
                contactAdapter.add(contacts);
                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }else if(Button.equals("notes")) {
            try {
                jsonObject = new JSONObject(json_string);
                jsonArray = jsonObject.getJSONArray("data");

                while (count<jsonArray.length())
                {
                    JSONObject JO = jsonArray.getJSONObject(count);
                    id = JO.getString("id");
                    title = JO.getString("title");
                    date = JO.getString("date");
                    body = JO.getString("body");
                    id_member = JO.getString("id_member");
                    lever = JO.getString("lever");
                    color = JO.getString("color");
                    member = JO.getString("member");

                    iid[count] = Integer.parseInt(JO.getString("id"));
                    iid_member[count] = Integer.parseInt(JO.getString("id_member"));
                    ititle[count] = JO.getString("title");
                    idate[count] = JO.getString("date");
                    ibody[count] = JO.getString("body");
                    ilever[count] = JO.getString("lever");
                    icolor[count] = JO.getString("color");
                    imember[count] = JO.getString("member");

                    Contacts contacts = new Contacts(title,date,color, Button);
                    contactAdapter.add(contacts);
                    count++;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else if(Button.equals("remember")) {
            try {
                jsonObject = new JSONObject(json_string);
                jsonArray = jsonObject.getJSONArray("data");

                while (count<jsonArray.length())
                {
                    JSONObject JO = jsonArray.getJSONObject(count);
                    id = JO.getString("id");
                    id_member = JO.getString("id_member");
                    id_second = JO.getString("id_second");
                    title = JO.getString("title");
                    date_write = JO.getString("date_write");
                    date = JO.getString("date");
                    time = JO.getString("time");
                    lever = JO.getString("lever");
                    color = JO.getString("color");
                    message = JO.getString("message");
                    action = JO.getString("action");
                    member_write = JO.getString("member_write");
                    members_add = JO.getString("members_add");

                    iid[count] = Integer.parseInt(JO.getString("id"));
                    iid_member[count] = Integer.parseInt(JO.getString("id_member"));
                    iid_second[count] = Integer.parseInt(JO.getString("id_second"));
                    ititle[count] = JO.getString("title");
                    idate_write[count] = JO.getString("date_write");
                    idate[count] = JO.getString("date");
                    itime[count] = JO.getString("time");
                    ilever[count] = JO.getString("lever");
                    icolor[count] = JO.getString("color");
                    imessage[count] = JO.getString("message");
                    iaction[count] = JO.getString("action");
                    imember_write[count] = JO.getString("member_write");
                    imembers_add[count] = JO.getString("members_add");

                    Contacts contacts = new Contacts(title,date,color, Button);
                    contactAdapter.add(contacts);
                    count++;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /* The click listner for ListView in the navigation drawer */
    private class NewsItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            String  ttitle, theader, tbody, tlabel, tauthor, tslug, ttype_job, ttype, tforto, tuser, ttid, tdate,
                    tlever,tcolor,tmember,ttid_member,tmessage,tdate_write,ttime,taction,tmember_write,tmembers_add,ttid_second;
            int     tid, tid_member, tid_second;

            if(Button.equals("pages")) {
                tid = iid[position];
                ttitle = ititle[position];
                theader = iheader[position];
                tbody = ibody[position];
                tlabel = ilabel[position];
                tslug = islug[position];
                ttype = itype[position];
                tforto = iforto[position];
                tuser = iuser[position];
                ttid = Integer.toString(tid);

                Toast.makeText(List.this , ttid,Toast.LENGTH_LONG).show();

                Intent intent = new Intent(List.this, Show.class);
                Bundle extras = new Bundle();
                extras.putString("EXTRA_Button","pages");
                extras.putString("EXTRA_title",ttitle);
                extras.putString("EXTRA_header",theader);
                extras.putString("EXTRA_body",tbody);
                extras.putString("EXTRA_label",tlabel);
                extras.putString("EXTRA_slug",tslug);
                extras.putString("EXTRA_type",ttype);
                extras.putString("EXTRA_forto",tforto);
                extras.putString("EXTRA_user",tuser);
                extras.putString("EXTRA_id",ttid);
                intent.putExtras(extras);
                startActivity(intent);
            } else if(Button.equals("news")) {
                tid = iid[position];
                ttitle = ititle[position];
                tdate = idate[position];
                tbody = ibody[position];
                tauthor = iauthor[position];
                ttype_job = itype_job[position];
                tforto = iforto[position];
                ttid = Integer.toString(tid);

                Toast.makeText(List.this , ttid,Toast.LENGTH_LONG).show();

                Intent intent = new Intent(List.this, Show.class);
                Bundle extras = new Bundle();
                extras.putString("EXTRA_Button","news");
                extras.putString("EXTRA_title",ttitle);
                extras.putString("EXTRA_date",tdate);
                extras.putString("EXTRA_body",tbody);
                extras.putString("EXTRA_author",tauthor);
                extras.putString("EXTRA_type_job",ttype_job);
                extras.putString("EXTRA_forto",tforto);
                extras.putString("EXTRA_id",ttid);
                intent.putExtras(extras);
                startActivity(intent);
            } else if(Button.equals("notes")) {
                tid_member = iid_member[position];
                tid = iid[position];
                ttitle = ititle[position];
                tdate = idate[position];
                tbody = ibody[position];
                tlever = ilever[position];
                tcolor = icolor[position];
                tmember = imember[position];
                ttid = Integer.toString(tid);
                ttid_member = Integer.toString(tid_member);

                Toast.makeText(List.this , ttid,Toast.LENGTH_LONG).show();
                if(tcolor.equals("info")){
                    // parent.setBackgroundResource(R.color.info);
                    parent.getChildAt(position).setBackgroundColor(getResources().getColor(R.color.info));
                }else if(tcolor.equals("success")){
                    //parent.setBackgroundResource(R.color.success);
                    parent.getChildAt(position).setBackgroundColor(getResources().getColor(R.color.success));
                }else if(tcolor.equals("warning")){
                    //parent.setBackgroundResource(R.color.warning);
                    parent.getChildAt(position).setBackgroundColor(getResources().getColor(R.color.warning));
                }else if(tcolor.equals("danger")){
                    //parent.setBackgroundResource(R.color.danger);
                    parent.getChildAt(position).setBackgroundColor(getResources().getColor(R.color.danger));
                }else if(tcolor.isEmpty()){
                    //parent.setBackgroundResource(R.color.ColorWhite);
                    parent.getChildAt(position).setBackgroundColor(getResources().getColor(R.color.ColorWhite));
                }

                Intent intent = new Intent(List.this, Show_Notes.class);
                Bundle extras = new Bundle();
                extras.putString("EXTRA_Button","notes");
                extras.putString("EXTRA_title",ttitle);
                extras.putString("EXTRA_date",tdate);
                extras.putString("EXTRA_body",tbody);
                extras.putString("EXTRA_lever",tlever);
                extras.putString("EXTRA_color",tcolor);
                extras.putString("EXTRA_member",tmember);
                extras.putString("EXTRA_id",ttid);
                extras.putString("EXTRA_id_member",ttid_member);
                intent.putExtras(extras);
                startActivity(intent);
            }else if(Button.equals("remember")) {
                tid_member = iid_member[position];
                tid_second = iid_second[position];
                tid = iid[position];
                ttitle = ititle[position];
                tdate = idate[position];
                tmessage = imessage[position];
                tlever = ilever[position];
                tcolor = icolor[position];
                tdate_write = idate_write[position];
                ttime = itime[position];
                taction = iaction[position];
                tmember_write = imember_write[position];
                tmembers_add = imembers_add[position];

                ttid = Integer.toString(tid);
                ttid_member = Integer.toString(tid_member);
                ttid_second = Integer.toString(tid_second);

                Toast.makeText(List.this , ttid,Toast.LENGTH_LONG).show();
                if(tcolor.equals("info")){
                   // parent.setBackgroundResource(R.color.info);
                    parent.getChildAt(position).setBackgroundColor(getResources().getColor(R.color.info));
                }else if(tcolor.equals("success")){
                    //parent.setBackgroundResource(R.color.success);
                    parent.getChildAt(position).setBackgroundColor(getResources().getColor(R.color.success));
                }else if(tcolor.equals("warning")){
                    //parent.setBackgroundResource(R.color.warning);
                    parent.getChildAt(position).setBackgroundColor(getResources().getColor(R.color.warning));
                }else if(tcolor.equals("danger")){
                    //parent.setBackgroundResource(R.color.danger);
                    parent.getChildAt(position).setBackgroundColor(getResources().getColor(R.color.danger));
                }else if(tcolor.isEmpty()){
                    //parent.setBackgroundResource(R.color.ColorWhite);
                    parent.getChildAt(position).setBackgroundColor(getResources().getColor(R.color.ColorWhite));
                }

                Intent intent = new Intent(List.this, Show_Notes.class);
                Bundle extras = new Bundle();
                extras.putString("EXTRA_Button","remember");
                extras.putString("EXTRA_id",ttid);
                extras.putString("EXTRA_id_member",ttid_member);
                extras.putString("EXTRA_id_second",ttid_second);
                extras.putString("EXTRA_title",ttitle);
                extras.putString("EXTRA_date",tdate);
                extras.putString("EXTRA_message",tmessage);
                extras.putString("EXTRA_lever", tlever);
                extras.putString("EXTRA_color", tcolor);
                extras.putString("EXTRA_date_write", tdate_write);
                extras.putString("EXTRA_time",ttime);
                extras.putString("EXTRA_action",taction);
                extras.putString("EXTRA_member_write", tmember_write);
                extras.putString("EXTRA_members_add", tmembers_add);
                intent.putExtras(extras);
                startActivity(intent);
            }
        }
    }
}