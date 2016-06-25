package com.example.antrosgeor.skynotejob;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ANTROS on 11/3/2016.
 */
public class ContactAdapter extends ArrayAdapter {
    List list = new ArrayList();
    public ContactAdapter(Context context, int resource) {
        super(context, resource);
    }
    public void add(Contacts object) {
        super.add(object);
        list.add(object);
    }
    @Override
    public int getCount() {
        return list.size();
    }
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row =convertView;
        Contactholder contactholder;
        if(row == null) {
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout,parent,false);
            contactholder = new Contactholder();
            contactholder.tx_name = (TextView) row.findViewById(R.id.tx_name);
            contactholder.tx_email = (TextView) row.findViewById(R.id.tx_email);
            contactholder.tx_mobile = (TextView) row.findViewById(R.id.tx_mobile);
            row.setTag(contactholder);
        }else{
            contactholder = (Contactholder)row.getTag();
        }

        Contacts contacts = (Contacts)this.getItem(position);
        contactholder.tx_name.setText((contacts.getName()));
        contactholder.tx_email.setText((contacts.getEmail()));
        contactholder.tx_mobile.setText((contacts.getMobile()));
        return row;
    }

    static class Contactholder{
        TextView tx_name,tx_email,tx_mobile;
    }
}