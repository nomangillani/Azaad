package com.example.azaad;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;

public class CompleteYourProfile extends AppCompatActivity {



    EditText fullname,height,dateofbirth,caste,country,heightstqualificaton,profession,monthlyincome;
    AutoCompleteTextView gender;
    Button Continue;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    String[] itemlist1;
    int position=0;
    String ageS;
    ProgressDialog dialog;
    RegisterDataArray dataArray=new RegisterDataArray();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_your_profile);

        String[] language ={"Male","Female"};
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item,language);





        fullname=findViewById(R.id.fullname);
        height=findViewById(R.id.height);
        dateofbirth=findViewById(R.id.dateofbirth);
        caste=findViewById(R.id.caste);
        country=findViewById(R.id.country);
        heightstqualificaton=findViewById(R.id.heighesteducation);
        profession=findViewById(R.id.profession);
        gender=findViewById(R.id.gender);
        Continue=findViewById(R.id.Continue);
        monthlyincome=findViewById(R.id.monthlyincome);





        dialog=new ProgressDialog(CompleteYourProfile.this);
        dialog.setTitle("Please Wait...");
        dialog.setMessage("loading...");
        dialog.setCanceledOnTouchOutside(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);



        dateofbirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        CompleteYourProfile.this,
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
                dateofbirth.setText(date);
                getAge(year,month,day);
            }
        };




        gender.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        gender.setThreshold(1);
        gender.setAdapter(adapter);
        gender.showDropDown();
    }
});




        caste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemlist1=getResources().getStringArray(R.array.caste);
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(CompleteYourProfile.this);
                mBuilder.setTitle("Caste");
                mBuilder.setSingleChoiceItems(itemlist1, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        position=which;
                    }
                });
                mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        caste.setText(itemlist1[position]);

                    }
                });
                mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();

            }
        });





        country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemlist1=getResources().getStringArray(R.array.country);
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(CompleteYourProfile.this);
                mBuilder.setTitle("Country");
                mBuilder.setSingleChoiceItems(itemlist1, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        position=which;
                    }
                });
                mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        country.setText(itemlist1[position]);

                    }
                });
                mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();

            }
        });





        heightstqualificaton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemlist1=getResources().getStringArray(R.array.education);
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(CompleteYourProfile.this);
                mBuilder.setTitle("Heighest Educational Qualification");
                mBuilder.setSingleChoiceItems(itemlist1, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        position=which;
                    }
                });
                mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        heightstqualificaton.setText(itemlist1[position]);

                    }
                });
                mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();

            }
        });



        profession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemlist1=getResources().getStringArray(R.array.profession);
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(CompleteYourProfile.this);
                mBuilder.setTitle("Profession");
                mBuilder.setSingleChoiceItems(itemlist1, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        position=which;
                    }
                });
                mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        profession.setText(itemlist1[position]);

                    }
                });
                mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();

            }
        });



Continue.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        //Toast.makeText(CompleteYourProfile.this, ""+ageS, Toast.LENGTH_SHORT).show();
        CompleteProfile();

    }
});
    }


    public boolean validatename(){
        String emailPattern = "^[A-Z][a-zA-Z]{3,}(?: [A-Z][a-zA-Z]*){0,2}$";
        String input_login_email=fullname.getText().toString().trim();
          if(input_login_email.isEmpty()){
              fullname.setText("");
            fullname.setError("Please fill the field");
            return false;
        }
          else if (!input_login_email.matches(emailPattern)){
            fullname.setText("");
            fullname.setError("Starting letter should be capital");
            return false;
        }

        else
        {
            fullname.setError(null);
            return true;
        }


    }
    public boolean validateheight(){
        String emailPattern = "\\d+(\\.\\d{1,3})?";
        String input_login_email=height.getText().toString().trim();
        if(input_login_email.isEmpty()){
            height.setText("");
            height.setError("Please fill the field");
            return false;
        }
        else if (!input_login_email.matches(emailPattern)){

            height.setText("");
            height.setError("Height should be like 5.66 please correct write");
            return false;
        }

        else
        {
            fullname.setError(null);
            return true;
        }


    }
    public boolean validatecaste(){
        String input_login_email=caste.getText().toString().trim();
        if(input_login_email.isEmpty()){
            caste.setText("");
            caste.setError("Please Select atleast one option");
            return false;
        }
        else
        {
            caste.setError(null);
            return true;
        }


    }
    public boolean dateofbirth(){
        String input_login_email=dateofbirth.getText().toString().trim();
        if(input_login_email.isEmpty()){
            dateofbirth.setText("");
            dateofbirth.setError("Please Select atleast one option");
            return false;
        }
        else
        {
            dateofbirth.setError(null);
            return true;
        }


    }
    public boolean gender(){
        String input_login_email=gender.getText().toString().trim();
        if(input_login_email.isEmpty()){
            gender.setText("");
            gender.setError("Please Select atleast one option");
            return false;
        }
        else
        {
            gender.setError(null);
            return true;
        }


    }
    public boolean country(){
        String input_login_email=country.getText().toString().trim();
        if(input_login_email.isEmpty()){
            country.setText("");
            country.setError("Please Select atleast one option");
            return false;
        }
        else
        {
            country.setError(null);
            return true;
        }


    }
    public boolean heighestqualification(){
        String input_login_email=heightstqualificaton.getText().toString().trim();
        if(input_login_email.isEmpty()){
            heightstqualificaton.setText("");
            heightstqualificaton.setError("Please Select atleast one option");
            return false;
        }
        else
        {
            heightstqualificaton.setError(null);
            return true;
        }


    }
    public boolean monthlyincome(){
        String input_login_email=monthlyincome.getText().toString().trim();
        if(input_login_email.isEmpty()){
            monthlyincome.setText("");
            monthlyincome.setError("please fill the field");
            return false;
        }
        else
        {
            monthlyincome.setError(null);
            return true;
        }


    }
    public boolean profession(){
        String input_login_email=profession.getText().toString().trim();
        if(input_login_email.isEmpty()){
            profession.setText("");
            profession.setError("please fill the field");
            return false;
        }
        else
        {
            profession.setError(null);
            return true;
        }


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





public  void CompleteProfile(){

    if(!validatename()|!validateheight()|!validatecaste()|!dateofbirth()|!gender()|!country()|!heighestqualification()|!monthlyincome()|!profession()){
        return;
    }


    dataArray.Storedata.add(fullname.getText().toString());
    dataArray.Storedata.add(gender.getText().toString());
    dataArray.Storedata.add(height.getText().toString());
    dataArray.Storedata.add(dateofbirth.getText().toString());
    dataArray.Storedata.add(ageS+" years");
    dataArray.Storedata.add(caste.getText().toString());
    dataArray.Storedata.add(country.getText().toString());
    dataArray.Storedata.add(heightstqualificaton.getText().toString());
    dataArray.Storedata.add(monthlyincome.getText().toString());
    dataArray.Storedata.add(profession.getText().toString());
    Toast.makeText(this, ""+dataArray.Storedata.get(6), Toast.LENGTH_SHORT).show();
    Intent intentt=new Intent(CompleteYourProfile.this,UploadPictures.class);
    startActivity(intentt);




















}
}
