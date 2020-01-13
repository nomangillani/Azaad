package com.example.azaad;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.theartofdev.edmodo.cropper.CropImage;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewProfileActivity extends AppCompatActivity {
    CircleImageView imagea,imageb,imagec,imaged,imagee,imagef;
    EditText id,age,height,birthdate,cast,address,bio,education,profession,income;
    LinearLayout profile,prefessional;
    ImageView editprofile,editpersonal;
    CurrentUserDataArray currentUserDataArray;
    String ageS;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    Button updateprofile;
    ProgressDialog dialog;


int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        profile=findViewById(R.id.linearlayoutpersonal);
        prefessional=findViewById(R.id.linearlayoutprofessional);
        editprofile=findViewById(R.id.personaledit);
        dialog=new ProgressDialog(ViewProfileActivity.this);
        dialog.setTitle("Please Wait...");
        dialog.setMessage("loading...");
        dialog.setCanceledOnTouchOutside(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
       /* for (View view : profile.getTouchables()){
            if (view instanceof EditText){
                EditText editText = (EditText) view;
                editText.setEnabled(false);
                editText.setFocusable(false);
                editText.setFocusableInTouchMode(false);
            }
        }*/







cast=findViewById(R.id.cast);
updateprofile=findViewById(R.id.update_profile);
        updateprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
            }
        });

        imagea=findViewById(R.id.image1);
        imageb=findViewById(R.id.image2);
        imagec=findViewById(R.id.image3);
        imaged=findViewById(R.id.image4);
        imagee=findViewById(R.id.image5);
        imagef=findViewById(R.id.image6);
        id=findViewById(R.id.userid);
        age=findViewById(R.id.age);
        height=findViewById(R.id.height);
        birthdate=findViewById(R.id.dateofbirth);
        address=findViewById(R.id.address);
        bio=findViewById(R.id.bio);
        education=findViewById(R.id.education);
        profession=findViewById(R.id.profession);
        income=findViewById(R.id.income);
        //String one=currentUserDataArray.Storedata.get(9);
        Glide.with(getApplicationContext()).load("http://circularbyte.com/Azaad_Matrimonial/"+currentUserDataArray.Storedata.get(9)).into(imagea);
        Glide.with(getApplicationContext()).load("http://circularbyte.com/Azaad_Matrimonial/"+currentUserDataArray.Storedata.get(10)).into(imageb);
        Glide.with(getApplicationContext()).load("http://circularbyte.com/Azaad_Matrimonial/"+currentUserDataArray.Storedata.get(11)).into(imagec);
        Glide.with(getApplicationContext()).load("http://circularbyte.com/Azaad_Matrimonial/"+currentUserDataArray.Storedata.get(12)).into(imaged);
        Glide.with(getApplicationContext()).load("http://circularbyte.com/Azaad_Matrimonial/"+currentUserDataArray.Storedata.get(13)).into(imagee);
        Glide.with(getApplicationContext()).load("http://circularbyte.com/Azaad_Matrimonial/"+currentUserDataArray.Storedata.get(14)).into(imagef);

        //imagea.setImageBitmap(Utility.stringToBitmap(currentUserDataArray.Storedata.get(9)));
       /* imageb.setImageBitmap(Utility.stringToBitmap(currentUserDataArray.Storedata.get(10)));
        imagec.setImageBitmap(Utility.stringToBitmap(currentUserDataArray.Storedata.get(11)));
        imaged.setImageBitmap(Utility.stringToBitmap(currentUserDataArray.Storedata.get(12)));
        imagee.setImageBitmap(Utility.stringToBitmap(currentUserDataArray.Storedata.get(13)));
        imagef.setImageBitmap(Utility.stringToBitmap(currentUserDataArray.Storedata.get(14)));*/
        education.setText(currentUserDataArray.Storedata.get(4));
        cast.setText(currentUserDataArray.Storedata.get(3));
        profession.setText(currentUserDataArray.Storedata.get(5));
        height.setText(currentUserDataArray.Storedata.get(7));
        income.setText(currentUserDataArray.Storedata.get(6));
        bio.setText(currentUserDataArray.Storedata.get(17));
        age.setText(currentUserDataArray.Storedata.get(2));
        address.setText(currentUserDataArray.Storedata.get(1));


        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });
        id.setText(currentUserDataArray.Storedata.get(15));
        birthdate.setText(currentUserDataArray.Storedata.get(16));




        imagea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CropImage.activity().start(ViewProfileActivity.this);
                count=1;


            }
        });





        imageb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CropImage.activity().start(ViewProfileActivity.this);
                count=2;


            }
        });




        imagec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CropImage.activity().start(ViewProfileActivity.this);
                count=3;


            }
        });


        imaged.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CropImage.activity().start(ViewProfileActivity.this);
                count=4;


            }
        });



        imagee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CropImage.activity().start(ViewProfileActivity.this);
                count=5;


            }
        });



        imagef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CropImage.activity().start(ViewProfileActivity.this);
                count=6;


            }
        });
        birthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        ViewProfileActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                Window window = dialog.getWindow();
                WindowManager.LayoutParams wlp = window.getAttributes();
                wlp.gravity = Gravity.CENTER;
                wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                window.setAttributes(wlp);
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = month + "-" + day + "-" + year;
                birthdate.setText(date);
                getAge(year,month,day);
                age.setText(ageS);
            }
        };






    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK&& count==1) {
                Uri resultUri = result.getUri();
                imagea.setImageURI(resultUri);
            }

            else if (resultCode == RESULT_OK&& count==2) {
                Uri resultUri = result.getUri();
                imageb.setImageURI(resultUri);
            }
            else if (resultCode == RESULT_OK&& count==3) {
                Uri resultUri = result.getUri();
                imagec.setImageURI(resultUri);
            }

            else if (resultCode == RESULT_OK&& count==4) {
                Uri resultUri = result.getUri();
                imaged.setImageURI(resultUri);
            }
            else if (resultCode == RESULT_OK&& count==5) {
                Uri resultUri = result.getUri();
                imaged.setImageURI(resultUri);
            }
            else if (resultCode == RESULT_OK&& count==6) {
                Uri resultUri = result.getUri();
                imagef.setImageURI(resultUri);
            }


            else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }

    }
    public void update(){

        currentUserDataArray.Storedata.clear();
        dialog.show();
            String url="http://circularbyte.com/Azaad_Matrimonial/UpdateUserInfo.php";
            StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                   dialog.dismiss();
                    Toast.makeText(getApplicationContext(),""+response,Toast.LENGTH_LONG).show();
                    JSONObject object=null;

                    try {
                        object=new JSONObject(response);
                        int status=object.getInt("success");
                        String key;
                        key=object.getString("uid");
                        if(status==1){

                            // Toast.makeText(getApplicationContext(),""+response,Toast.LENGTH_LONG).show();


                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),""+error.getMessage(),Toast.LENGTH_LONG).show();

                }
            }){

                public HashMap<String,String> getParams(){
                    HashMap<String,String> p=new HashMap<>();
                    p.put("imageone",Utility.bitmapToString(((BitmapDrawable) imagea.getDrawable()).getBitmap()));
                    p.put("imagetwo",Utility.bitmapToString(((BitmapDrawable) imageb.getDrawable()).getBitmap()));
                    p.put("imagethree",Utility.bitmapToString(((BitmapDrawable) imagec.getDrawable()).getBitmap()));
                    p.put("imagefour",Utility.bitmapToString(((BitmapDrawable) imaged.getDrawable()).getBitmap()));
                    p.put("imagefive",Utility.bitmapToString(((BitmapDrawable) imagee.getDrawable()).getBitmap()));
                    p.put("imagesix",Utility.bitmapToString(((BitmapDrawable) imagef.getDrawable()).getBitmap()));
                    p.put("bio",bio.getText().toString());
                    p.put("age",age.getText().toString());
                    p.put("height",height.getText().toString());
                    p.put("dob",birthdate.getText().toString());
                    p.put("cast",cast.getText().toString());
                    p.put("country",address.getText().toString());
                    p.put("education",education.getText().toString());
                    p.put("profession",profession.getText().toString());
                    p.put("income",income.getText().toString());
                    p.put("uid",new SessionManager(getApplicationContext()).getpassword());
                    p.put("userkey",new SessionManager(getApplicationContext()).getUserKey());
                    return p;

                }


            };
            RequestQueue queue= Volley.newRequestQueue(ViewProfileActivity.this);
            queue.add(request);



        }


    private String getAge(int year, int month, int day){
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }

        Integer ageInt = new Integer(age);
        ageS = ageInt.toString();
        Toast.makeText(getApplicationContext(), ""+ageS, Toast.LENGTH_SHORT).show();

        return ageS;
    }


}
