package com.example.azaad;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

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
import java.util.prefs.PreferenceChangeEvent;

public class FaverouteFragment  extends Fragment {
    public ArrayList<ModelClass> data;
    public  static  ArrayList<ModelClass> data1;
    ArrayAdapter<ModelClass> adapter;
    ListView lst;
    String userid;
    Toolbar toolbar;
    TextView filter,tittle;
    SessionManager manager;
    UserCompleteDataArray dataArray;
    ProgressDialog dialog;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.faveroutefragment, container, false);
        toolbar=view.findViewById(R.id.homenavbar);
        dataArray.Storedata.clear();
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        tittle=toolbar.findViewById(R.id.navtittle);
        tittle.setText("Favorites");
        filter=toolbar.findViewById(R.id.filter);
        filter.setText("");
        lst=view.findViewById(R.id.faveroutelistview);
        manager=new SessionManager(getContext());
        userid=manager.getUserKey();
        dataArray.Storedata.clear();
       dialog=new ProgressDialog(getContext());
        dialog.setTitle("Please Wait...");
        dialog.setMessage("loading...");
        dialog.setCanceledOnTouchOutside(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        lst=view.findViewById(R.id.faveroutelistview);
        TextView empty = new TextView(getContext());
        empty.setHeight(95);
        lst.addFooterView(empty);


getfaveroutedata();

        return view;
    }
    public  void getfaveroutedata(){

dialog.show();
        StringRequest request=new StringRequest(Request.Method.POST, "http://circularbyte.com/Azaad_Matrimonial/GetFavourites.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               // Toast.makeText(getContext(), "methodcall", Toast.LENGTH_SHORT).show();

dialog.dismiss();
                try {
                    JSONObject object=new JSONObject(response);
                    data=new ArrayList<>();
                    //data1=new ArrayList<ModelClass>();
                    JSONArray array=object.getJSONArray("result");
                    data=new ArrayList<>();
                    // Toast.makeText(getContext(), ""+array.length(), Toast.LENGTH_SHORT).show();
                    for(int i=0;i<=array.length();i++) {
                        JSONObject object1=array.getJSONObject(i);

                        String Imagea = object1.getString("imageone");
                        String Imageb = object1.getString("imagetwo");
                        String Imagec = object1.getString("imagethree");
                        String Imaged = object1.getString("imagefour");
                        String Imagee = object1.getString("imagefive");
                        String Imagef = object1.getString("imagesix");


                        String phone = object1.getString("phone");
                        String name = object1.getString("fullname");
                        String gender=object1.getString("gender");
                        String height = object1.getString("height");
                        String dob=object1.getString("dob");
                        String age = object1.getString("age");
                        String caste = object1.getString("caste");

                        String country = object1.getString("country");
                        String education = object1.getString("education");
                        String income = object1.getString("income");
                        String profession = object1.getString("profession");
                        String bio = object1.getString("bio");
                        String Userid = object1.getString("userid");
                       dataArray=new UserCompleteDataArray();
                        dataArray.Storedata.add(new ModelClass(name,"",country,age,caste,education,profession,income,"fair",height,"Joint",bio,"Muslim","PHD","Developer","Fair","6.7",Userid,Imagea,Imageb,Imagec,Imaged,Imagee,Imagef));


                        // data=new ArrayList<>();
                        data.add(new ModelClass(Imagea,name,"",country,age));
                        adapter=new FaverouteItemAdapters(getActivity(),data);
                        lst.setAdapter(adapter);

                        //String skintone = object1.getString("phone");
                        //

                       /* ;
                        // int userid=object.getInt("userid");*/
                        //Toast.makeText(getContext(), ""+family, Toast.LENGTH_SHORT).show();

                       /* data.add(new ModelClass(Imagea,name,"",country,age));
                        adapter=new HomeItemAdapter(getContext(),data);
                        lst.setAdapter(adapter);


                        //dataArray.Storedata.clear();

                        dataArray.Storedata.add(new ModelClass(name,"",country,age,caste,education,profession,income,"fair",height,"Joint",bio,"Muslim","PHD","Developer","Fair","6.7",Userid,Imagea,Imageb,Imagec,Imaged,Imagee,Imagef));

                        Toast.makeText(getContext(), ""+Userid, Toast.LENGTH_LONG).show();*/

                        //adapter=new HomeItemAdapter(getContext(),data);




                    }




                } catch (JSONException e) {
                    e.printStackTrace();
                }






















            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();

            }
        }){

            public HashMap<String,String> getParams(){
                HashMap<String,String> p=new HashMap<>();
                p.put("userid",new SessionManager(getContext()).getpassword());
                return p;

            }


        };
        RequestQueue queue= Volley.newRequestQueue(getContext());
        queue.add(request);



    }
}
