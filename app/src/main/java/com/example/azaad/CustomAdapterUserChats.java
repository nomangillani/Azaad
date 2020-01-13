package com.example.azaad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.azaad.InboxDataClass2.obj;


public class CustomAdapterUserChats extends ArrayAdapter<DataClassUserChatMessage> {
    CurrentUserDataArray dataArray;
    private int resourceLayout;
    private Context mContext;
   ArrayList<DataClassUserChatMessage> thedatachat;
    public CustomAdapterUserChats(Context context, ArrayList<DataClassUserChatMessage> thedatachat) {
        super(context, R.layout.item_message_sent, thedatachat);
        this.thedatachat=thedatachat;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater layoutInflater = LayoutInflater.from(getContext());

        if(thedatachat.get(position).getSenderid().equalsIgnoreCase(new SessionManager(getContext()).getpassword()+""))
        {
            convertView = layoutInflater.inflate(R.layout.item_message_sent, null);
        }
        else
        {
            convertView = layoutInflater.inflate(R.layout.item_message_received, null);
        }

        TextView text_message_body = convertView.findViewById(R.id.text_message_body);
        TextView text_message_time = convertView.findViewById(R.id.text_message_time);

        CircleImageView image_message_profile = convertView.findViewById(R.id.image_message_profile);
        TextView text_message_name = convertView.findViewById(R.id.text_message_name);

        text_message_body.setText(thedatachat.get(position).getMessagetext());
        text_message_time.setText(thedatachat.get(position).getCreated_date());


       if(thedatachat.get(position).getSenderid().equalsIgnoreCase(new SessionManager(getContext()).getUserKey()))
        {
           text_message_name.setText("");
            Glide.with(mContext).load("http://circularbyte.com/Azaad_Matrimonial/"+dataArray.Storedata.get(9)).into(image_message_profile);

        }
        else
        {
            text_message_name.setText("");
            Glide.with(mContext).load("http://circularbyte.com/Azaad_Matrimonial/"+obj.getUserimage()).into(image_message_profile);

        }


return convertView;

    }

}