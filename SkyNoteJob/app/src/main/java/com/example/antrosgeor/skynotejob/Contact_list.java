package com.example.antrosgeor.skynotejob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Contact_list extends AppCompatActivity {
/** Values */
    String  json_string, button, id, first, last, email, avatar, type_job, mphone, wphone, address, job, note, tfirst,
            tlast, temail, tavatar, ttid, ttype_job, tmphone, twphone, taddress, tjob, tnote;
    int tid,count = 0;

    JSONObject jsonObject;
    JSONArray jsonArray;
    CoontactAdapterss coontactAdapterss;
    ListView listView;
    final int[] iid = new int[10];
    final String[] ifirst = new String[20];
    final String[] ilast = new String[20];
    final String[] iavatar = new String[20];
    final String[] iemail = new String[20];
    final String[] itype_job = new String[20];
    final String[] imphone = new String[20];
    final String[] iwphone = new String[20];
    final String[] iaddress = new String[20];
    final String[] inotes = new String[20];
    final String[] ijob = new String[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_list);
        coontactAdapterss = new CoontactAdapterss(this, R.layout.row);
        listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(coontactAdapterss);
        listView.setOnItemClickListener(new NewsItemClickListener());

        json_string = getIntent().getExtras().getString("json_data");
        button = getIntent().getExtras().getString("button");
        if (button.equals("Admin_select")) {
            try {
                jsonObject = new JSONObject(json_string);
                jsonArray = jsonObject.getJSONArray("data");
                count = 0;

                while (count < jsonArray.length()) {
                    JSONObject JO = jsonArray.getJSONObject(count);
                    id = JO.getString("id");
                    first = JO.getString("first");
                    last = JO.getString("last");
                    email = JO.getString("email");
                    avatar = JO.getString("avatar");

                    iid[count] = Integer.parseInt(JO.getString("id"));
                    ifirst[count] = JO.getString("first");
                    ilast[count] = JO.getString("last");
                    iemail[count] = JO.getString("email");
                    iavatar[count] = JO.getString("avatar");

                    Contacts contacts = new Contacts(avatar, first, last, button);
                    coontactAdapterss.add(contacts);
                    count++;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (button.equals("Member")) {
            try {
                jsonObject = new JSONObject(json_string);
                jsonArray = jsonObject.getJSONArray("data");
                count = 0;

                while (count < jsonArray.length()) {
                    JSONObject JO = jsonArray.getJSONObject(count);
                    id = JO.getString("id");
                    first = JO.getString("first");
                    last = JO.getString("last");
                    email = JO.getString("email");
                    avatar = JO.getString("avatar");
                    type_job = JO.getString("type_job");
                    mphone = JO.getString("mphone");
                    wphone = JO.getString("wphone");
                    address = JO.getString("address");
                    job =  JO.getString("name_job");

                    iid[count] = Integer.parseInt(JO.getString("id"));
                    ifirst[count] = JO.getString("first");
                    ilast[count] = JO.getString("last");
                    iemail[count] = JO.getString("email");
                    iavatar[count] = JO.getString("avatar");
                    itype_job[count] = JO.getString("type_job");
                    imphone[count] = JO.getString("mphone");
                    iwphone[count] = JO.getString("wphone");
                    iaddress[count] = JO.getString("address");
                    ijob[count]= JO.getString("name_job");

                    Contacts contacts = new Contacts(avatar, first, last, button);
                    coontactAdapterss.add(contacts);
                    count++;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (button.equals("Contact")) {
            try {
                jsonObject = new JSONObject(json_string);
                jsonArray = jsonObject.getJSONArray("data");
                count = 0;

                while (count < jsonArray.length()) {
                    JSONObject JO = jsonArray.getJSONObject(count);
                    id = JO.getString("id");
                    first = JO.getString("first");
                    last = JO.getString("last");
                    email = JO.getString("email");
                    avatar = JO.getString("avatar");
                    note = JO.getString("note");
                    mphone = JO.getString("mphone");
                    wphone = JO.getString("wphone");
                    address = JO.getString("address");

                    iid[count] = Integer.parseInt(JO.getString("id"));
                    ifirst[count] = JO.getString("first");
                    ilast[count] = JO.getString("last");
                    iemail[count] = JO.getString("email");
                    iavatar[count] = JO.getString("avatar");
                    inotes[count] = JO.getString("note");
                    imphone[count] = JO.getString("mphone");
                    iwphone[count] = JO.getString("wphone");
                    iaddress[count] = JO.getString("address");

                    Contacts contacts = new Contacts(avatar, first, last ,button);
                    coontactAdapterss.add(contacts);
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
            if (button.equals("Admin_select")) {
                tid = iid[position];
                tfirst = ifirst[position];
                tlast = ilast[position];
                temail = iemail[position];
                tavatar = iavatar[position];
                ttid = Integer.toString(tid);

                Toast.makeText(Contact_list.this, ttid, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(Contact_list.this, ShowContact.class);
                Bundle extras = new Bundle();
                extras.putString("EXTRA_id", ttid);
                extras.putString("EXTRA_first", tfirst);
                extras.putString("EXTRA_last", tlast);
                extras.putString("EXTRA_email", temail);
                extras.putString("EXTRA_avatar", tavatar);
                extras.putString("EXTRA_button", "Admin_select");
                intent.putExtras(extras);
                startActivity(intent);
            } else if (button.equals("Member")) {
                tid = iid[position];
                tfirst = ifirst[position];
                tlast = ilast[position];
                temail = iemail[position];
                tavatar = iavatar[position];
                ttype_job = itype_job[position];
                tmphone = imphone[position];
                twphone = iwphone[position];
                taddress = iaddress[position];
                tjob =  ijob[position];
                ttid = Integer.toString(tid);

                Toast.makeText(Contact_list.this, ttid, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(Contact_list.this, ShowContact.class);
                Bundle extras = new Bundle();
                extras.putString("EXTRA_id", ttid);
                extras.putString("EXTRA_first", tfirst);
                extras.putString("EXTRA_last", tlast);
                extras.putString("EXTRA_email", temail);
                extras.putString("EXTRA_avatar", tavatar);
                extras.putString("EXTRA_type_job", ttype_job);
                extras.putString("EXTRA_mphone", tmphone);
                extras.putString("EXTRA_wphone", twphone);
                extras.putString("EXTRA_address", taddress);
                extras.putString("EXTRA_job", tjob);
                extras.putString("EXTRA_button", "Member");
                intent.putExtras(extras);
                startActivity(intent);
            } else if (button.equals("Contact")) {
                tid = iid[position];
                tfirst = ifirst[position];
                tlast = ilast[position];
                temail = iemail[position];
                tavatar = iavatar[position];
                tnote = inotes[position];
                tmphone = imphone[position];
                twphone = iwphone[position];
                taddress = iaddress[position];
                ttid = Integer.toString(tid);

                Toast.makeText(Contact_list.this, ttid, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(Contact_list.this, ShowContact.class);
                Bundle extras = new Bundle();
                extras.putString("EXTRA_id", ttid);
                extras.putString("EXTRA_first", tfirst);
                extras.putString("EXTRA_last", tlast);
                extras.putString("EXTRA_email", temail);
                extras.putString("EXTRA_avatar", tavatar);
                extras.putString("EXTRA_note", tnote);
                extras.putString("EXTRA_mphone", tmphone);
                extras.putString("EXTRA_wphone", twphone);
                extras.putString("EXTRA_address", taddress);
                extras.putString("EXTRA_button", "Contact");
                intent.putExtras(extras);
                startActivity(intent);
            }
        }
    }
}