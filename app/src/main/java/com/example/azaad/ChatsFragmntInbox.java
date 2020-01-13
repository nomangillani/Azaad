package com.example.azaad;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChatsFragmntInbox extends Fragment {
    View view;
    private RecyclerView recyclerView;
   public static ArrayList<InboxDataClass> inboxDataClasses;
    private RecyclerView.Adapter mAdapter;
    UserCompleteDataArray userCompleteDataArray;

    SwipeRefreshLayout swiperefresh;
    SearchView searchView;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.chat, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        swiperefresh = view.findViewById(R.id.swiperefresh);
searchView=view.findViewById(R.id.search_view);
        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showinbox();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swiperefresh.setRefreshing(false);
                    }
                }, 2000);
            }
        });


       showinbox();
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                showinbox();
                return true;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filterdata(s);
                Toast.makeText(getContext(), "hello", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        return view;
    }

    public void showinbox()
    {
        inboxDataClasses = new ArrayList<InboxDataClass>();


        StringRequest request = new StringRequest(Request.Method.POST, "http://circularbyte.com/Azaad_Matrimonial/inb.php", new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                try {


                    Log.e("quesns","Executed"+response.toString());
                    JSONObject data = new JSONObject(response);

                    //  thedata[0] = data.getJSONArray("category");

                    JSONArray array  = data.getJSONArray("result");

                    if(array.length()<=0)
                    {
                        Toast.makeText(getContext(), "Inbox is empty", Toast.LENGTH_SHORT).show();
                    }

                    for(int i = 0; i < array.length(); i++) {
                        JSONObject obj = null;
                        try {
                            obj = array.getJSONObject(i);
                            String messagesid = obj.getString("messagesid").toString();
                            String receiverid = obj.getString("receiverid").toString();
                            String lastmessage = obj.getString("lastmessage").toString();
                            String senderid = obj.getString("senderid").toString();
                            String isdeleted = obj.getString("isdeleted").toString();
                            String created_date = obj.getString("created_date").toString();
                            String created_time = obj.getString("created_time").toString();
                            String fname = obj.getString("fname").toString();

                            String userimage = obj.getString("imageone").toString();
                            String status = obj.getString("status").toString();
                            String showuser = obj.getString("showuser").toString();
if(status.equalsIgnoreCase("1") && senderid.equalsIgnoreCase(new SessionManager(getContext()).getpassword())){

                                //messagesid,receiverid,lastmessage,senderid,isdeleted,user_name,bigliveid,userimage
                                inboxDataClasses.add(new InboxDataClass(messagesid, receiverid, lastmessage, senderid, isdeleted,
                                        created_date, fname, userimage, showuser));

                        }
                        }

                        catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //store your variable

                    }
                    mAdapter = new InboxAdapter(getContext(),getActivity(),inboxDataClasses);
                    recyclerView.setAdapter(mAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.setHasFixedSize(true);
                    //recyclerView.setAdapter(mAdapter);

//                    chatadapter=new CustomAdapterchat(MainActivity.this,thedata);
//                    listView.setAdapter(chatadapter);

                } catch (JSONException e) {
                    e.printStackTrace();

//                    new AlertDialog.Builder(ContactMessage.this).setMessage("An error Occured! Please try again!").setTitle("Alert!").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.dismiss();
//                        }
//                    }).show();

//                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    Log.e("jerror","Json error: " + e.getMessage());
                }
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {
//                Toast.makeText(Quiz.this, "Error: ", Toast.LENGTH_SHORT).show();
//                new AlertDialog.Builder(ContactMessage.this).setMessage("An error Occured! Please try again!").setTitle("Alert!").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                }).show();
                Log.e("verror","Error: "+volleyError.getMessage());
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                try
                {
                    Map<String,String> params = new HashMap<>();
                   /* params.put("userid",new SessionManager(getContext()).getUserKey());*/
                    params.put("userid",new SessionManager(getContext()).getpassword()+"");
                    return params;
                }
                catch (Exception ex){
                    Map<String,String> params = new HashMap<>();
                    params.put("userid","0");
                    return params;
                }

//                return super.getParams();
            }
        };

        RequestQueue rQueue = Volley.newRequestQueue(getContext());
        rQueue.add(request);
    }
    public void filterdata(String keyword)
    {

        ArrayList<InboxDataClass> temp = new ArrayList<>();

        for (InboxDataClass loanRequestDataClass:inboxDataClasses) {
            if(loanRequestDataClass.getUser_name().toString().trim().equalsIgnoreCase(""+keyword.toString().trim()) ||
                    loanRequestDataClass.getUser_name().toString().trim().toLowerCase().startsWith(""+keyword.toLowerCase().toString().trim()) ||
                    loanRequestDataClass.getUser_name().toString().trim().toLowerCase().endsWith(""+keyword.toLowerCase().toString().trim()))
            {
                temp.add(loanRequestDataClass);
            }

            // Toast.makeText(this, "o"+keyword+" and "+loanRequestDataClass.getLoanTable().getLoan_category()+"i", Toast.LENGTH_SHORT).show();

        }
        mAdapter = new InboxAdapter(getContext(),getActivity(),inboxDataClasses);
        recyclerView.setAdapter(mAdapter);
        recyclerView.hasPendingAdapterUpdates();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);


    }

}
