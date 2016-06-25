package com.example.antrosgeor.skynotejob;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/** Created by ANTROS on 11/3/2016. */
public class CoontactAdapterss extends ArrayAdapter {

    String  Image_Admin_File = GlobalClass.Image_Admin_File;
    String  Image_Member_File = GlobalClass.Image_Member_File;
    String  Image_Contacts_File = GlobalClass.Image_Contacts_File;
    String  LH_Destination_images = GlobalClass.LH_Destination_images;
    String  status, image, Email, Mobile;
    String  imageUrl = LH_Destination_images+Image_Member_File+"no_avatar.png";
    List    list = new ArrayList();
    public CoontactAdapterss(Context context, int resource) {
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
        if(row == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row,parent,false);
            contactholder = new Contactholder();
            contactholder.tx_name = (ImageView) row.findViewById(R.id.tx_name);
            contactholder.tx_email = (TextView) row.findViewById(R.id.tx_email);
            contactholder.tx_mobile = (TextView) row.findViewById(R.id.tx_mobile);
            row.setTag(contactholder);
        }else
        {
            contactholder = (Contactholder)row.getTag();
        }

        Contacts contacts = (Contacts)this.getItem(position);
         status = contacts.getStatus();
         image = contacts.getName();
         Email = contacts.getEmail();
         Mobile = contacts.getMobile();
        if(image != null && !image.isEmpty()) {
            if (status.equals("Admin_select")) {
                imageUrl = LH_Destination_images+Image_Admin_File+image;
            } else if (status.equals("Member")) {
                imageUrl = LH_Destination_images+Image_Member_File+image;
            } else if (status.equals("Contact")) {
                imageUrl = LH_Destination_images+Image_Contacts_File+image;
            }
        }

        new DownloadImageTask((ImageView) row.findViewById(R.id.tx_name))
                .execute(imageUrl);
        contactholder.tx_email.setText(Email);
        contactholder.tx_mobile.setText(Mobile);
        return row;
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
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }
        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

    static class Contactholder{
        TextView tx_email,tx_mobile;
        ImageView tx_name;
    }
}
