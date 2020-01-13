package com.example.azaad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class PendingItemAdapter extends ArrayAdapter<ModelClass> {
    RegisterDataArray dataArray;
    SessionManager manager;
    ArrayList<ModelClass> data;
    Context context;

    public  String favid;
  public   String id;
    UserCompleteDataArray userCompleteDataArray=new UserCompleteDataArray();
    PendingItemAdapter(Context context, ArrayList<ModelClass> data) {
        super(context, R.layout.pendingitem, data);

        this.data = data;
        this.context = context;


    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.pendingitem, null);
        CircleImageView imageView;
        TextView name;
        imageView=view.findViewById(R.id.image);
        name=view.findViewById(R.id.name);
        Button cancel;
        cancel=view.findViewById(R.id.accept);
        imageView.setImageBitmap(Utility.stringToBitmap(data.get(position).getImage()));
        name.setText(data.get(position).getName());
        return view;
    }

}
