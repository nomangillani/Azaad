package com.example.azaad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.theartofdev.edmodo.cropper.CropImage;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class UploadPictures extends AppCompatActivity {
    Toolbar toolbar;
    TextView textView;
    ImageView img1,img2,img3,img4,img5,img6;
    int count=0;
    EditText bio;
    Button save;
    ProgressDialog dialog;
    RegisterDataArray dataArray=new RegisterDataArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_pictures);
        toolbar=findViewById(R.id.navbar);
        setSupportActionBar(toolbar);
        textView=toolbar.findViewById(R.id.navtittle);
        textView.setText("");
        Drawable backarrow=getApplicationContext().getResources().getDrawable(R.drawable.backarrow_icon);
        getSupportActionBar().setHomeAsUpIndicator(backarrow);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        dialog=new ProgressDialog(UploadPictures.this);
        dialog.setTitle("Please Wait...");
        dialog.setMessage("loading...");
        dialog.setCanceledOnTouchOutside(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);




        bio=findViewById(R.id.biofield);
        save=findViewById(R.id.save);
        img1=findViewById(R.id.image1);
        img2=findViewById(R.id.image2);
        img3=findViewById(R.id.image3);
        img4=findViewById(R.id.image4);
        img5=findViewById(R.id.image5);
        img6=findViewById(R.id.image6);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


               uploadepicture();


            }
        });
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CropImage.activity().start(UploadPictures.this);
                count=1;


            }
        });





        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CropImage.activity().start(UploadPictures.this);
                count=2;


            }
        });




        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CropImage.activity().start(UploadPictures.this);
                count=3;


            }
        });


        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CropImage.activity().start(UploadPictures.this);
                count=4;


            }
        });



        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CropImage.activity().start(UploadPictures.this);
                count=5;


            }
        });



        img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CropImage.activity().start(UploadPictures.this);
                count=6;


            }
        });




    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK&& count==1) {
                Uri resultUri = result.getUri();
                img1.setImageURI(resultUri);
            }

            else if (resultCode == RESULT_OK&& count==2) {
                Uri resultUri = result.getUri();
                img2.setImageURI(resultUri);
            }
            else if (resultCode == RESULT_OK&& count==3) {
                Uri resultUri = result.getUri();
                img3.setImageURI(resultUri);
            }

            else if (resultCode == RESULT_OK&& count==4) {
                Uri resultUri = result.getUri();
                img4.setImageURI(resultUri);
            }
            else if (resultCode == RESULT_OK&& count==5) {
                Uri resultUri = result.getUri();
                img5.setImageURI(resultUri);
            }
            else if (resultCode == RESULT_OK&& count==6) {
                Uri resultUri = result.getUri();
                img6.setImageURI(resultUri);
            }


            else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

    public boolean validatebio(){

        String input_login_email=bio.getText().toString().trim();
        if(input_login_email.isEmpty()){
            bio.requestFocus();
            bio.setError("Please fill the fields ");
            return false;
        }
        else
        {
            bio.setError(null);
            return true;
        }


    }


public  void uploadepicture(){


    if(!validatebio()){
        return;
    }
    dataArray.Storedata.add(bio.getText().toString());
    dataArray.Storedata.add(Utility.bitmapToString(((BitmapDrawable) img1.getDrawable()).getBitmap()));
    dataArray.Storedata.add(Utility.bitmapToString(((BitmapDrawable) img2.getDrawable()).getBitmap()));
    dataArray.Storedata.add(Utility.bitmapToString(((BitmapDrawable) img3.getDrawable()).getBitmap()));
    dataArray.Storedata.add(Utility.bitmapToString(((BitmapDrawable) img4.getDrawable()).getBitmap()));
    dataArray.Storedata.add(Utility.bitmapToString(((BitmapDrawable) img5.getDrawable()).getBitmap()));
    dataArray.Storedata.add(Utility.bitmapToString(((BitmapDrawable) img6.getDrawable()).getBitmap()));

  dialog.show();
    String url="http://circularbyte.com/Azaad_Matrimonial/user.php";
    StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            dialog.dismiss();
            //Toast.makeText(getApplicationContext(),""+response,Toast.LENGTH_LONG).show();
            try {
                JSONObject object= new JSONObject(response);
                int status=object.getInt("success");
                if (status==1){

                    Intent i=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                    i.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);




                }
                Toast.makeText(UploadPictures.this, ""+status, Toast.LENGTH_SHORT).show();
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
            SessionManager manager;
            p.put("method",dataArray.Storedata.get(0));
            p.put("email",dataArray.Storedata.get(1));
            p.put("password",dataArray.Storedata.get(2));
            p.put("phone",dataArray.Storedata.get(3));
            p.put("status",dataArray.Storedata.get(4));
            p.put("membership",dataArray.Storedata.get(5));
            p.put("expiration","12-11-2019");
            p.put("bio",dataArray.Storedata.get(17));
            p.put("userkey",dataArray.Storedata.get(6));
            p.put("fullname",dataArray.Storedata.get(7));
            p.put("gender",dataArray.Storedata.get(8));
            p.put("height",dataArray.Storedata.get(9));
            p.put("dob",dataArray.Storedata.get(10));
            p.put("age",dataArray.Storedata.get(11));
            p.put("caste",dataArray.Storedata.get(12));
            p.put("country",dataArray.Storedata.get(13));
            p.put("education",dataArray.Storedata.get(14));
            p.put("income",dataArray.Storedata.get(15));
            p.put("profession",dataArray.Storedata.get(16));
            p.put("imageone",dataArray.Storedata.get(18));
            p.put("imagetwo",dataArray.Storedata.get(19));
            p.put("imagethree",dataArray.Storedata.get(20));
            p.put("imagefour",dataArray.Storedata.get(21));
            p.put("imagefive",dataArray.Storedata.get(22));
            p.put("imagesix",dataArray.Storedata.get(23));

            return p;

        }


    };
    RequestQueue queue= Volley.newRequestQueue(UploadPictures.this);
    queue.add(request);










}


}
