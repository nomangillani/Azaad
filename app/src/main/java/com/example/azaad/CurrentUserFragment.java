package com.example.azaad;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class CurrentUserFragment  extends Fragment {
    Toolbar toolbar;
    TextView tittle,filter;
    CurrentUserDataArray dataArray;
    CircleImageView image1;
    CardView cardView;

    TextView name,Plain;
    Button viewprofile;
View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.userfragment, container, false);
        toolbar=view.findViewById(R.id.homenavbar);
      toolbar=view.findViewById(R.id.homenavbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        cardView=view.findViewById(R.id.sub_plain);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getContext(),Subsripton.class);
                startActivity(i);
            }
        });
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        filter=toolbar.findViewById(R.id.filter);
        viewprofile=view.findViewById(R.id.viewprofile);
        viewprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getContext(),ViewProfileActivity.class);
                startActivity(i);


            }
        });
        Plain=view.findViewById(R.id.plain);
        Plain.setText(dataArray.Storedata.get(18));
        image1=view.findViewById(R.id.image1);
        name=view.findViewById(R.id.name);


        Glide.with(getContext())
                .load("http://circularbyte.com/Azaad_Matrimonial/"+dataArray.Storedata.get(9))
                .into(image1);

        name.setText(dataArray.Storedata.get(0));
        tittle=toolbar.findViewById(R.id.navtittle);
        tittle.setText("Profile");
        filter.setText("Logout");
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SessionManager manager=new SessionManager(getContext());
                manager.logoutUser();
            }
        });
        return view;
    }
}



