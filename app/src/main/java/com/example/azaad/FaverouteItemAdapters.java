package com.example.azaad;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Malik A Pasha on 2/12/2019.
 */

public class FaverouteItemAdapters extends ArrayAdapter<ModelClass> {

    static Activity thecontext;
    ArrayList<ModelClass> thedata;
    RegisterDataArray dataArray;
    SessionManager manager;
    ArrayList<ModelClass> data;
    public  String favid;
    public   String id;
    UserCompleteDataArray userCompleteDataArray=new UserCompleteDataArray();


    public static int flag = 0;

    ViewHolder viewHolder;


    public FaverouteItemAdapters(Activity thecontext, ArrayList<ModelClass> thedata)
    {
        super(thecontext, R.layout.faverouteitem,thedata);
        this.thecontext = thecontext;
        this.thedata = thedata;


        viewHolder = new ViewHolder();
    }

    public View getView(final int position, View rowview, ViewGroup viewGroup) {

        if(rowview == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = thecontext.getLayoutInflater();
            rowview = inflater.inflate(R.layout.faverouteitem,null,false);

            viewHolder.image = (CircleImageView) rowview.findViewById(R.id.image);
            viewHolder.name =rowview.findViewById(R.id.name);
            viewHolder.age =rowview.findViewById(R.id.age);
            viewHolder.country = rowview.findViewById(R.id.city_country);
            viewHolder.imageView = rowview.findViewById(R.id.add_to_favorat);
            viewHolder.connect = rowview.findViewById(R.id.connect);
            viewHolder.connect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(getContext(),CompleteInformation.class);
                    i.removeExtra("position");
                    i.putExtra("Position",position);
                    getContext().startActivity(i);
                }
            });



            viewHolder.position = position;
            rowview.setTag(viewHolder);
        }
        else
        {
            viewHolder=(ViewHolder)rowview.getTag();
        }




        // Toast.makeText(thecontext, ""+thedata.get(position).getLiked(), Toast.LENGTH_SHORT).show();



        new ImageLoadTask(viewHolder,position,thecontext).execute(thedata.get(position));

        return rowview;
    }


    static class ViewHolder {
        CircleImageView image;
        int position;
        ProgressBar progress;

        Button faveroute,connect;
        ImageView imageView;

        TextView name,country,age;
    }
    private static class ImageLoadTask extends AsyncTask<ModelClass, Void, String> {

        ViewHolder viewHolder;
        int position;
        Activity thecontext;

        private ImageLoadTask(ViewHolder viewHolder,int position,Activity thecontext) {
            this.viewHolder = viewHolder;
            this.position = position;
            // CustomProgress.show(thecontext);
            this.thecontext = thecontext;

        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }


        @Override
        protected String doInBackground(final ModelClass... teamData) {
            //  CustomProgress.hide();

            try {
                thecontext.runOnUiThread(new Runnable() {
                    public void run() {

                        viewHolder.name.setText(teamData[0].getName()+"");
                        viewHolder.age.setText(teamData[0].getAge()+"");
                        viewHolder.country.setText(teamData[0].getCountry()+"");




                        //     viewHolder.backconst.setBackgroundResource(R.drawable.kkgradient);


                        //    Glide.with(thecontext).load(teamData[0].getShirtpath().toString()).placeholder(R.drawable.msgradient).into(viewHolder.imageView);


                        Glide.with(thecontext).load("http://circularbyte.com/Azaad_Matrimonial/"+teamData[0].getImagea()).into(viewHolder.image);



                        //        Toast.makeText(thecontext, "Executed Background", Toast.LENGTH_SHORT).show();
                    }
                });

            }
            catch (Exception e)
            {
                thecontext.runOnUiThread(new Runnable() {

                    public void run() {
                        //   Toast.makeText(thecontext, "Exception Occured", Toast.LENGTH_SHORT).show();
                    }
                });
            }



            return "done";
        }

        @Override
        protected void onPostExecute(String string) {
            // myMethod(rowview);

            super.onPostExecute(string);
        }


    }

}
