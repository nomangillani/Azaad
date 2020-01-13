package com.example.azaad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.azaad.InboxDataClass2.obj;

public class ChatActivity extends AppCompatActivity {
    ArrayList<DataClassUserChatMessage> thedata;

    private RecyclerView.Adapter mAdapter;

    private Handler mHandler;
    private Runnable mRunnable;

    FloatingActionButton butSend;
    EditText inputText;
CircleImageView image;
TextView name,time;
 int messagesize;
Toolbar toolbar;
    ArrayAdapter<DataClassUserChatMessage> chatadapter;

    ListView listView;
    UserCompleteDataArray userCompleteDataArray=new UserCompleteDataArray();
int positon=0;
String receverid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        toolbar=findViewById(R.id.usertoolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        image=toolbar.findViewById(R.id.profile_image);
        name=toolbar.findViewById(R.id.toolbar_logput);
        time=toolbar.findViewById(R.id.time);
        positon=getIntent().getIntExtra("position",0);
        //positon=getIntent().getIntExtra("posi",0);

        Glide.with(getApplicationContext())
                .load("http://circularbyte.com/Azaad_Matrimonial/"+obj.getUserimage())
                .into(image);
        Toast.makeText(getApplicationContext(), ""+obj.getUserimage(), Toast.LENGTH_SHORT).show();

        //image.setImageBitmap(Utility.stringToBitmap(userCompleteDataArray.Storedata.get(positon).getImagea()));
        name.setText(obj.getUser_name());
        time.setText("active");
        //receverid=userCompleteDataArray.Storedata.get(positon).getId());

       // positon=getIntent().getIntExtra("Position",0);

        listView=(ListView)findViewById(R.id.listviewmessages);
        thedata = new ArrayList<>();
        messagesize = thedata.size();
        inputText = findViewById(R.id.inputText);
        butSend = findViewById(R.id.butSend);
        butSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), ""+positon, Toast.LENGTH_SHORT).show();
                sendmessage();
            }
        });
        mHandler = new Handler();

        mRunnable = new Runnable() {
            @Override
            public void run() {
                new LoadContact().execute();
                mHandler.postDelayed(mRunnable,5000);
            }
        };

        mHandler.postDelayed(mRunnable,5000);




    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        finish();
    }

    public void sendmessage()
    {

        StringRequest requestinsertsession = new StringRequest(Request.Method.POST, "http://circularbyte.com/Azaad_Matrimonial/InsertMessagen.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                JSONObject json = null;    // create JSON obj from string

                //Log.e("Resultis", "Image: "+Utility.bitmapToString(((BitmapDrawable)imageView.getDrawable()).getBitmap()));
                Log.e("Resultis", "\n"+s + " ");
                try {
                    json = new JSONObject(s);
                    String success = json.get("success").toString();
                    String msg = json.get("msg").toString();


                    Toast.makeText(ChatActivity.this, " "+msg, Toast.LENGTH_SHORT).show();

                    inputText.setText("");


                }catch (JSONException e) {



                    Log.e("basicinfo",e.getMessage()+" ");

                    Toast.makeText(ChatActivity.this, "An Error Occurred Try Again!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {


                Toast.makeText(ChatActivity.this, "An Error Occurred Try Again!", Toast.LENGTH_LONG).show();

                Log.e("basicinfo",volleyError.getMessage()+" ");

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();


                Time today = new Time(Time.getCurrentTimezone());
                today.setToNow();
                String year, month, day;
                year = today.year+"";
                int monthis;
                monthis = today.month + 1;
                month = monthis+"";
                day = today.monthDay+"";
                String created_date = day+"-"+month+"-"+year+" ";
                String created_time = today.format("%k:%M:%S");


               /* parameters.put("receiverid",8+"");
                parameters.put("senderid",22+"");*/


                parameters.put("receiverid",obj.getReceiverid());
                parameters.put("senderid",new SessionManager(getApplicationContext()).getpassword()+"") ;


                parameters.put("messagetext",inputText.getText().toString()+"");
                parameters.put("created_date",created_date+"");
                parameters.put("created_time",created_time+"");

                parameters.put("deletedby","0");
                parameters.put("isdeleted","0");
                parameters.put("status","0");
                return parameters;
            }
        };


        if(!inputText.getText().toString().trim().equalsIgnoreCase(""))
        {
            RequestQueue rQueue = Volley.newRequestQueue(ChatActivity.this);
            rQueue.add(requestinsertsession);
        }

    }


    public void showmessages()
    {

        final ArrayList<DataClassUserChatMessage> themessagedata = new ArrayList<>();

        StringRequest request = new StringRequest(Request.Method.POST, "http://circularbyte.com/Azaad_Matrimonial/GetAllMessagesn.php", new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                try {

                    Log.e("quesns","Executed"+response.toString());
                    JSONObject data = new JSONObject(response);

                    //  thedata[0] = data.getJSONArray("category");

                    JSONArray array  = data.getJSONArray("result");


                    for(int i = 0; i < array.length(); i++) {
                        JSONObject obj = null;
                        try {
                            obj = array.getJSONObject(i);

                            String allmessagesid = obj.getString("allmessagesid").toString();
                            String receiverid = obj.getString("receiverid").toString();
                            String senderid = obj.getString("senderid").toString();

                            String messagetext = obj.getString("messagetext").toString();
                            String created_date = obj.getString("created_date").toString();

                            String status = obj.getString("status").toString();
                            String deletedby = obj.getString("deletedby").toString();
                            String isdeleted = obj.getString("isdeleted").toString();

                            themessagedata.add(new DataClassUserChatMessage(allmessagesid,receiverid,senderid,messagetext,created_date,status,deletedby,isdeleted));



                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                        //store your variable

                    }


                    Parcelable state = listView.onSaveInstanceState();


                    if(messagesize<themessagedata.size())
                    {
                        chatadapter=new CustomAdapterUserChats(ChatActivity.this,themessagedata);
                        listView.setAdapter(chatadapter);

                        listView.onRestoreInstanceState(state);
                        messagesize = themessagedata.size();
                    }



                    mHandler.postDelayed(mRunnable,5000);





                } catch (JSONException e) {
                    e.printStackTrace();



                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    Log.e("jerror","Json error: " + e.getMessage());

                }
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {
//                Toast.makeText(Quiz.this, "Error: ", Toast.LENGTH_SHORT).show();
                new AlertDialog.Builder(ChatActivity.this).setMessage("An error Occured! Please try again!").setTitle("Alert!").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
                Log.e("verror","Error: "+volleyError.getMessage());
            }
        })
        {

            public HashMap<String,String> getParams(){
                HashMap<String,String> p=new HashMap<>();

                p.put("receiverid",obj.getReceiverid());
                p.put("senderid",new SessionManager(getApplicationContext()).getpassword()+"") ;
                return p;

            }




            /*@Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("receiverid",19+"");
                params.put("senderid",18+"");

                return params;
            }*/
        };

        RequestQueue rQueue = Volley.newRequestQueue(ChatActivity.this);
        rQueue.add(request);
    }

    class LoadContact extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            // Get Contact list from Phone

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //    adapter = new SelectUserAdapter(selectUsers, FindContact.this);
            //  listView.setAdapter(adapter);

            // Select item on listclick
            mHandler.removeCallbacks(mRunnable);
            showmessages();

        }
    }


    @Override
    protected void onDestroy() {
        mHandler.removeCallbacks(mRunnable);
        super.onDestroy();
    }


}
