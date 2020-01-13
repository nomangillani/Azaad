package com.example.azaad;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Random;

public class RegisterActivity extends AppCompatActivity {
    EditText email,phone,password,confirm_password;
    Button register;
    TextView login_here;
    int userkey;

    RegisterDataArray dataArray=new RegisterDataArray();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);






        email=findViewById(R.id.email);
        phone=findViewById(R.id.phonenumber);
        password=findViewById(R.id.password);
        confirm_password=findViewById(R.id.confirm_password);
        register=findViewById(R.id.register);
        login_here=findViewById(R.id.login_here);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });
    }
    public boolean validate_login_email(){
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String input_login_email=email.getText().toString().trim();
        if(!input_login_email.matches(emailPattern)){
            email.requestFocus();
            email.setText("");
            email.setError("Invalid email address ");
            return false;
        }
        else
        {
            email.setError(null);
            return true;
        }




    }




    public boolean validate_number(){
        String emailPattern = "^[+]?[0-9]{10,13}$";
        String phonenumber=phone.getText().toString().trim();
        if(!phonenumber.matches(emailPattern)){
            phone.requestFocus();
            phone.setText("");
            phone.setError("Please enter correct number");
            return false;
        }
        else
        {
            phone.setError(null);
            return true;
        }


    }




    public boolean validate_password(){
        String input_login_password=password.getText().toString().trim();
        String cpassword=confirm_password.getText().toString().trim();
        if(input_login_password.isEmpty()){
            password.setError("Field can't be empty ");
            password.requestFocus();
            return false;
        }

        else if(input_login_password.length()<6){
            password.setError("More then 6 character");
            password.requestFocus();
            return false;
        }
        else if(!input_login_password.equals(cpassword)){
            password.setError("Password not match");
            password.requestFocus();
            return false;

        }
        else
        {
            password.setError(null);
            return true;
        }
    }


public void  Register(){

    if(!validate_login_email()|!validate_number()|!validate_password()){
        return;
    }

    gen();
    dataArray.Storedata.add("register");
    dataArray.Storedata.add(email.getText().toString());
    dataArray.Storedata.add(password.getText().toString());
    dataArray.Storedata.add(phone.getText().toString());
    dataArray.Storedata.add("active");
    dataArray.Storedata.add("free");
    dataArray.Storedata.add("AZD"+userkey);
    Intent i=new Intent(getApplicationContext(),CompleteYourProfile.class);
    startActivity(i);

}
    public int gen() {
        Random r = new Random( System.currentTimeMillis() );
        userkey=((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
        return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
    }
}
