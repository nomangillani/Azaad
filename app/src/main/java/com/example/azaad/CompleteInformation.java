package com.example.azaad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;

import java.util.ArrayList;

import static com.example.azaad.InboxDataClass2.obj;

public class CompleteInformation extends AppCompatActivity {
    SliderLayout sliderLayout;
    Button personalinfo,expectation;
    LinearLayout personalifnfolayout,expectaitonlayout;
    UserCompleteDataArray userCompleteDataArray;
   public ArrayList<ModelClass> data;
    public static ModelClass userdata;
    ModelClass modelClass;
    int positon=0;

    TextView name,country,ageheight,bio,cast,education,profession,income,e_cast,e_education,e_profession,e_skintone,e_height,e_family;
Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.completeinfowithslider);
        positon=getIntent().getIntExtra("Position",0);
        Toast.makeText(getApplicationContext(), ""+positon, Toast.LENGTH_SHORT).show();


btn=findViewById(R.id.connect);
btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(getApplicationContext(),ChatActivity.class);

        obj.setReceiverid(userCompleteDataArray.Storedata.get(positon).getId());
        obj.setUserimage(userCompleteDataArray.Storedata.get(positon).getImagea());
        obj.setUser_name(userCompleteDataArray.Storedata.get(positon).getName());


        Toast.makeText(CompleteInformation.this, ""+obj.getReceiverid(), Toast.LENGTH_SHORT).show();
        startActivity(intent);



    }
});
        String Profile= userCompleteDataArray.Storedata.get(positon).getProfession();
        userdata = new ModelClass();
        name=findViewById(R.id.name);
        country=findViewById(R.id.country);
        ageheight=findViewById(R.id.ageandheight);
        // height=view.findViewById(R.id.height);
        bio=findViewById(R.id.bio);
        cast=findViewById(R.id.caste);
        education=findViewById(R.id.educatoin);
        profession=findViewById(R.id.profession);
        income=findViewById(R.id.income);
        e_cast=findViewById(R.id.e_cast);
        e_education=findViewById(R.id.e_education);
        e_profession=findViewById(R.id.e_profession);
        e_skintone=findViewById(R.id.e_skintone);
        e_height=findViewById(R.id.e_height);
        e_family=findViewById(R.id.e_family);




        name.setText(userCompleteDataArray.Storedata.get(positon).getName());
        country.setText(userCompleteDataArray.Storedata.get(positon).getCountry());
        ageheight.setText(userCompleteDataArray.Storedata.get(positon).getAge()+" | "+userCompleteDataArray.Storedata.get(positon).getHeight()+" Feet");
        // height.setText(userCompleteDataArray.Storedata.get(positon).getHeight());
        bio.setText(userCompleteDataArray.Storedata.get(positon).getBio());
        cast.setText(userCompleteDataArray.Storedata.get(positon).getCaste());
        education.setText(userCompleteDataArray.Storedata.get(positon).getEducation());
        profession.setText(userCompleteDataArray.Storedata.get(positon).getProfession());
        income.setText(userCompleteDataArray.Storedata.get(positon).getIncome());
        e_cast.setText(userCompleteDataArray.Storedata.get(positon).getE_cast());
        e_education.setText(userCompleteDataArray.Storedata.get(positon).getE_education());
        e_profession.setText(userCompleteDataArray.Storedata.get(positon).getE_profession());
        e_skintone.setText(userCompleteDataArray.Storedata.get(positon).getE_skintone());
        e_height.setText(userCompleteDataArray.Storedata.get(positon).getE_height());
        personalinfo=findViewById(R.id.personalinfo);
        expectation=findViewById(R.id.expectation);
        personalifnfolayout=findViewById(R.id.personalinfolayout);
        expectaitonlayout=findViewById(R.id.expectationlayout);
        expectaitonlayout.setVisibility(View.GONE);
        expectation.setBackgroundResource(R.drawable.homeitemwithoutbackgorun);
        expectation.setTextColor(Color.parseColor("#EF6121"));

        expectation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                personalifnfolayout.setVisibility(View.GONE);
                expectaitonlayout.setVisibility(View.VISIBLE);
                personalinfo.setBackgroundResource(R.drawable.homeitemwithoutbackgorun);
                personalinfo.setTextColor(Color.parseColor("#EF6121"));
                expectation.setBackgroundResource(R.drawable.homeitembutton);
                expectation.setTextColor(Color.parseColor("#FFFFFF"));


            }
        });




        personalinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                personalifnfolayout.setVisibility(View.VISIBLE);
                expectaitonlayout.setVisibility(View.GONE);

                expectation.setBackgroundResource(R.drawable.homeitemwithoutbackgorun);
                expectation.setTextColor(Color.parseColor("#EF6121"));
                personalinfo.setBackgroundResource(R.drawable.homeitembutton);
                personalinfo.setTextColor(Color.parseColor("#FFFFFF"));


            }
        });






        sliderLayout=findViewById(R.id.imageSlider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.SWAP);
        sliderLayout.setSliderTransformAnimation(SliderAnimations.FADETRANSFORMATION);
        sliderLayout.setScrollTimeInSec(4);
        setSliderViews();



    }
    private void setSliderViews() {
        int positon=getIntent().getIntExtra("Position",0);
       // int positon;
       // Toast.makeText(getApplicationContext(), ""+positon, Toast.LENGTH_SHORT).show();
       // positon = Integer.parseInt(value);
       /* String imageone=userCompleteDataArray.Storedata.get(positon).getImagea();
        byte[] data = Base64.decode(imageone, Base64.DEFAULT);
        String imagetwo=userCompleteDataArray.Storedata.get(positon).getImageb();
        byte[] data1 = Base64.decode(imagetwo, Base64.DEFAULT);
        String imagethree=userCompleteDataArray.Storedata.get(positon).getImagec();
        byte[] data2 = Base64.decode(imagethree, Base64.DEFAULT);
        String image4=userCompleteDataArray.Storedata.get(positon).getImaged();
        byte[] data3 = Base64.decode(image4, Base64.DEFAULT);
        String image5=userCompleteDataArray.Storedata.get(positon).getImagee();
        byte[] data4 = Base64.decode(image5, Base64.DEFAULT);
        String image6=userCompleteDataArray.Storedata.get(positon).getImagef();
        byte[] data5 = Base64.decode(image6, Base64.DEFAULT);
*/
        for (int i = 0; i <=5; i++) {

            DefaultSliderView sliderView = new DefaultSliderView(getApplicationContext());

            switch (i) {
                case 0:


                    //sliderView.setImageByte(data);
                    sliderView.setImageUrl("http://circularbyte.com/Azaad_Matrimonial/"+userCompleteDataArray.Storedata.get(positon).getImagea());

                    /*String imagetwo=userCompleteDataArray.Storedata.get(positon).getImageb();
                    String uri=Uri.decode(imageone);
                    Uri uria=Uri.parse(uri);
                    Uri urib=Uri.parse(imagetwo);
                    sliderView.setImageUrl("uria");
*/
                    //sliderView.setImageUrl("https://images.pexels.com/photos/218983/pexels-photo-218983.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
                    break;
                case 1:
                    sliderView.setImageUrl("http://circularbyte.com/Azaad_Matrimonial/"+userCompleteDataArray.Storedata.get(positon).getImageb());
                   // sliderView.setImageByte(data1);
                    // sliderView.setImageUrl("https://images.pexels.com/photos/218983/pexels-photo-218983.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
                    break;
                case 2:
                    sliderView.setImageUrl("http://circularbyte.com/Azaad_Matrimonial/"+userCompleteDataArray.Storedata.get(positon).getImagec());
                    //sliderView.setImageByte(data2);
                    //sliderView.setImageUrl("https://images.pexels.com/photos/747964/pexels-photo-747964.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260");
                    break;
                case 3:
                    sliderView.setImageUrl("http://circularbyte.com/Azaad_Matrimonial/"+userCompleteDataArray.Storedata.get(positon).getImaged());
                    //sliderView.setImageByte(data3);
                    //sliderView.setImageUrl("https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
                    break;
                case 4:
                    sliderView.setImageUrl("http://circularbyte.com/Azaad_Matrimonial/"+userCompleteDataArray.Storedata.get(positon).getImagee());
                    //sliderView.setImageByte(data4);
                    //sliderView.setImageUrl("https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
                    break;
                case 5:
                    sliderView.setImageUrl("http://circularbyte.com/Azaad_Matrimonial/"+userCompleteDataArray.Storedata.get(positon).getImagef());
                    //sliderView.setImageByte(data5);
                    //sliderView.setImageUrl("https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
                    break;


            }

            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            /*sliderView.setDescription("Name\n" +
                    "26 year old  " + (i + 1));
            final int finalI = i;

            sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(SliderView sliderView) {
                    Toast.makeText(getContext(), "This is slider " + (finalI + 1), Toast.LENGTH_SHORT).show();

                }
            });*/

            //at last add this view in your layout :
            sliderLayout.addSliderView(sliderView);
        }

    }
}
