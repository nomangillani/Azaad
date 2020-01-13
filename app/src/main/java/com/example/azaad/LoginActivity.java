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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
import dmax.dialog.SpotsDialog;

public class LoginActivity extends AppCompatActivity {
EditText email,password;
SessionManager manager;
TextView forgot_password,register_here;
Button login;
ProgressDialog dialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        manager=new SessionManager(getApplicationContext());

        dialog=new ProgressDialog(LoginActivity.this);
        dialog.setTitle("Please Wait...");
        dialog.setMessage("loading...");
        dialog.setCanceledOnTouchOutside(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        forgot_password=findViewById(R.id.forgot_password);
        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),ForgetPassword.class);
                startActivity(i);
            }
        });

        register_here=findViewById(R.id.register_here);
        register_here.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(i);
            }
        });
        login=findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login();
            }
        });

    }
    public void login(){


        if(!validate_login_email()|!validate_login_password()){
            return;
        }
        dialog.show();
        String url="http://circularbyte.com/Azaad_Matrimonial/user.php";
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dialog.dismiss();
              Toast.makeText(getApplicationContext(),""+response,Toast.LENGTH_LONG).show();
                JSONObject object=null;

          try {
                     object=new JSONObject(response);
                    int status=object.getInt("success");
                    String userkey=object.getString("name");
                    String userid;
                     userid=object.getString("uid");
                    if(status==1){
                        manager.createLoginSession(email.getText().toString(),userid,userkey);
                       // Toast.makeText(LoginActivity.this, ""+key, Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(i);
                       //Toast.makeText(getApplicationContext(),""+key+userkey,Toast.LENGTH_LONG).show();


                    }
                    else {
                        AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
                        alertDialog.setTitle("Alert");
                        alertDialog.setMessage("Invalid email or password");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();

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
                p.put("method","login");
                p.put("email",email.getText().toString());
                p.put("password",password.getText().toString());
                return p;

            }


        };
        RequestQueue queue= Volley.newRequestQueue(LoginActivity.this);
        queue.add(request);



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
    public boolean validate_login_password(){
        String input_login_password=password.getText().toString().trim();
        if(input_login_password.isEmpty()){
            password.setError("Field can't be empty ");
            password.requestFocus();
            return false;
        }

        else if(input_login_password.length()<3){
            password.setError("More then 6 character");
            password.requestFocus();
            return false;
        }
        else
        {
            password.setError(null);
            return true;
        }
    }
}
