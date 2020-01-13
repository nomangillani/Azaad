package com.example.azaad;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class ForgetPassword extends AppCompatActivity {
    Toolbar toolbar;
    TextView tittle;
    EditText email;
    Button forgorpassword;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        dialog=new ProgressDialog(ForgetPassword.this);
        dialog.setTitle("Please Wait...");
        dialog.setMessage("loading...");
        dialog.setCanceledOnTouchOutside(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        toolbar=findViewById(R.id.navbar);
        setSupportActionBar(toolbar);
        tittle=toolbar.findViewById(R.id.navtittle);
        tittle.setText("Forget password");
        Drawable backarrow=getApplicationContext().getResources().getDrawable(R.drawable.backarrow);
        getSupportActionBar().setHomeAsUpIndicator(backarrow);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        email=findViewById(R.id.email);
        forgorpassword=findViewById(R.id.forgotpassword);
        forgorpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Forgotpassword();

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
    public void Forgotpassword(){


        if(!validate_login_email()){
            return;
        }
        dialog.show();
        String url="http://circularbyte.com/Azaad_Matrimonial/user.php";
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dialog.dismiss();
                //Toast.makeText(getApplicationContext(),""+response,Toast.LENGTH_LONG).show();


               try {
                    JSONObject object=new JSONObject(response);
                    int status=object.getInt("success");
                    if(status==1){
                        Toast.makeText(getApplicationContext(),"Valid",Toast.LENGTH_LONG).show();


                    }
                    else if(status==0){

                        email.setError("Invalid Email");



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
                p.put("method","forgetPassword");
                p.put("email",email.getText().toString());

                return p;

            }


        };
        RequestQueue queue= Volley.newRequestQueue(ForgetPassword.this);
        queue.add(request);



    }
}
