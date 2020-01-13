package com.example.azaad;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

public class HomeFragment  extends Fragment {
    View view;
   public  static ArrayList<ModelClass> data;
    public static ArrayList<ModelClass> data2;
    public static ModelClass chatdata;
    CurrentUserDataArray currentUserDataArray=new CurrentUserDataArray();
    //public static ArrayList<ModelClass> chatdata;
    public  static  ArrayList<ModelClass> data1;
    ArrayAdapter<ModelClass> adapter;
    ArrayAdapter<ModelClass> adapterr;
    UserCompleteDataArray userCompleteDataArray=new UserCompleteDataArray();
    //CurrrentUserDataArray currrentUserDataArray=new CurrrentUserDataArray();
    ListView lst;
    Toolbar toolbar;
    TextView filter;
    SessionManager manager;
    ProgressDialog dialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);
        toolbar=view.findViewById(R.id.homenavbar);
        //dataArray.Storedata.clear();
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        filter=toolbar.findViewById(R.id.filter);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Hello", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(getContext(),FilterActivity.class);
                startActivity(i);
            }
        });
        chatdata=new ModelClass();
        dialog=new ProgressDialog(getContext());
        dialog.setTitle("Please Wait...");
        dialog.setMessage("loading...");
        dialog.setCanceledOnTouchOutside(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        lst=view.findViewById(R.id.homelistview);
        TextView empty = new TextView(getContext());
        empty.setHeight(95);
        lst.addFooterView(empty);
userCompleteDataArray.Storedata.clear();

        getuserdata();
       //data.add(new ModelClass(Utility.bitmapToString(BitmapFactory.decodeResource(getResources(), R.drawable.background)),"Summerate summer","New york","USA","27 Years" ));
        /*data.add(new ModelClass(R.drawable.background,"Summerate summer","New york","USA","27 Years" ));
        data.add(new ModelClass(R.drawable.background,"Summerate summer","New york","USA","27 Years" ));
        data.add(new ModelClass(R.drawable.background,"Summerate summer","New york","USA","27 Years" ));
        data.add(new ModelClass(R.drawable.background,"Summerate summer","New york","USA","27 Years" ));
        data.add(new ModelClass(R.drawable.background,"Summerate summer","New york","USA","27 Years" ));
        data.add(new ModelClass(R.drawable.background,"Summerate summer","New york","USA","27 Years" ));
        data.add(new ModelClass(R.drawable.background,"Summerate summer","New york","USA","27 Years" ));
        data.add(new ModelClass(R.drawable.background,"Summerate summer","New york","USA","27 Years" ));*/
        /*adapter=new HomeItemAdapter(getContext(),data);
        TextView empty = new TextView(getContext());
        empty.setHeight(95);
        lst.addFooterView(empty);
        lst.setAdapter(adapter);*/
       /* lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(), "hello", Toast.LENGTH_SHORT).show();
            }
        });*/
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                //Toast.makeText(getActivity(), ""+UserCompleteDataArray.Storedata.get(position).getProfession(), Toast.LENGTH_SHORT).show();

                //Toast.makeText(getActivity(), "" + userCompleteDataArray.Storedata.get(0), Toast.LENGTH_SHORT).show();
            }
        });








        return view;
    }


    public void getuserdata(){

dialog.show();

        String url="http://circularbyte.com/Azaad_Matrimonial/GetUsers.php";
        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
manager=new SessionManager(getContext());
                  dialog.dismiss();
              // Toast.makeText(getContext(), "Metod called"+response, Toast.LENGTH_SHORT).show();

               try {
                    JSONObject object=new JSONObject(response);
                   data=new ArrayList<>();
                   data2=new ArrayList<>();
                   //data1=new ArrayList<ModelClass>();
                    JSONArray array=object.getJSONArray("result");
                  // Toast.makeText(getContext(), ""+array.length(), Toast.LENGTH_SHORT).show();
                        for(int i=0;i<=array.length();i++) {
                        JSONObject object1=array.getJSONObject(i);
                        String Imagea = object1.getString("imageone");
                            String Imageb = object1.getString("imagetwo");
                            String Imagec = object1.getString("imagethree");
                            String Imaged = object1.getString("imagefour");
                            String Imagee = object1.getString("imagefive");
                            String Imagef = object1.getString("imagesix");
                            String membership=object1.getString("membership");
                            String name = object1.getString("fullname");
                            String Userid = object1.getString("userid");
                            String country = object1.getString("country");
                            String age = object1.getString("age");
                            String caste = object1.getString("caste");
                            String education = object1.getString("education");
                            String profession = object1.getString("profession");
                            String income = object1.getString("income");
                           //String skintone = object1.getString("phone");
                            String height = object1.getString("height");
                           // String family = object1.getString("phone");
                            String bio = object1.getString("bio");
                           // int userid=object.getInt("userid");
                           String birth=object1.getString("dob");
                            String userkey=object1.getString("userkey");
                            if(!manager.getpassword().equals(Userid)){

                               // data.add(new ModelClass(Imagea,name,"",country,age));
                                data.add(new ModelClass(name,"",country,age,caste,education,profession,income,"fair",height,"Joint",bio,"Muslim","PHD","Developer","Fair","6.7",Userid,Imagea,Imageb,Imagec,Imaged,Imagee,Imagef));

                                // data2.add(new ModelClass(name,"",country,age,caste,education,profession,income,"fair",height,"Joint",bio,"Muslim","PHD","Developer","Fair","6.7",Userid,Imagea,Imageb,Imagec,Imaged,Imagee,Imagef));
                                userCompleteDataArray.Storedata.add(new ModelClass(name,"",country,age,caste,education,profession,income,"fair",height,"Joint",bio,"Muslim","PHD","Developer","Fair","6.7",Userid,Imagea,Imageb,Imagec,Imaged,Imagee,Imagef));
                                //chatdata.add(new ModelClass(name,"",country,age,caste,education,profession,income,"fair",height,"Joint",bio,"Muslim","PHD","Developer","Fair","6.7",Userid,Imagea,Imageb,Imagec,Imaged,Imagee,Imagef));
                            }
                            else {
                                currentUserDataArray.Storedata.add(name);
                                currentUserDataArray.Storedata.add(country);
                                currentUserDataArray.Storedata.add(age);
                                currentUserDataArray.Storedata.add(caste);
                                currentUserDataArray.Storedata.add(education);
                                currentUserDataArray.Storedata.add(profession);
                                currentUserDataArray.Storedata.add(income);
                                currentUserDataArray.Storedata.add(height);
                                currentUserDataArray.Storedata.add(Userid);
                                currentUserDataArray.Storedata.add(Imagea);
                                currentUserDataArray.Storedata.add(Imageb);
                                currentUserDataArray.Storedata.add(Imagec);
                                currentUserDataArray.Storedata.add(Imaged);
                                currentUserDataArray.Storedata.add(Imagee);
                                currentUserDataArray.Storedata.add(Imagef);
                                currentUserDataArray.Storedata.add(userkey);
                                currentUserDataArray.Storedata.add(birth);
                                currentUserDataArray.Storedata.add(bio);
                                currentUserDataArray.Storedata.add(membership);
                                //UserCompleteDataArray.Storedata.add(new ModelClass(name,"",country,age,caste,education,profession,income,"fair",height,"Joint",bio,"Muslim","PHD","Developer","Fair","6.7",Userid,Imagea,Imageb,Imagec,Imaged,Imagee,Imagef));

                            }

                           // data.add(new ModelClass(Imagea,name,"",country,age));
                            adapterr=new HomeItemsAdapters(getActivity(),data);
                            //adapter=new HomeItemAdapter(getContext(),data);
                            lst.setAdapter(adapterr);


                            //dataArray.Storedata.clear();

                           // dataArray.Storedata.add(new ModelClass(name,"",country,age,caste,education,profession,income,"fair",height,"Joint",bio,"Muslim","PHD","Developer","Fair","6.7",Userid,Imagea,Imageb,Imagec,Imaged,Imagee,Imagef));

                           Toast.makeText(getContext(), ""+Userid, Toast.LENGTH_LONG).show();

                            //adapter=new HomeItemAdapter(getContext(),data);




                        }




                } catch (JSONException e) {
                    e.printStackTrace();
                }


                /*JSONObject object=null;
                try {
                    object = new JSONObject(response);
                    JSONArray jsonArray=object.getJSONArray("result");
                    if (jsonArray != null) {

                        for (int i = 0; i < jsonArray.length(); i++) {
                          JSONObject  objecti = jsonArray.getJSONObject(i);
                            String Imagea = objecti.getString("imageone");
                            String name = objecti.getString("fullname");
                            String country = objecti.getString("country");
                            String age = objecti.getString("age");
                            data = new ArrayList<>();
                            data.add(new ModelClass(Imagea, name, "New York", country, age));
                            //data.add(new ModelClass(Imagea, name, "New York", country, age));
                            Toast.makeText(getContext(), "" + "\n" + name + "\n" + country + "\n" + age, Toast.LENGTH_SHORT).show();
                        }

                        adapter = new HomeItemAdapter(getContext(), data);
                        lst.setAdapter(adapter);

                    }
                    else {


                        Toast.makeText(getContext(), "null data", Toast.LENGTH_SHORT).show();
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }











*/

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Metod called"+error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }){
               public HashMap<String,String> getParams(){
                FilterSessionManager sessionManager=new FilterSessionManager(getContext());
                HashMap<String,String> p=new HashMap<>();
                if(!sessionManager.getCast().isEmpty()){

                    p.put("cast",sessionManager.getCast());
                    p.put("education",sessionManager.getEducation());
                    p.put("minage",sessionManager.getMinage());
                    p.put("maxage",sessionManager.getMaxage());
                    p.put("minheight",sessionManager.getMinheight());
                    p.put("maxheight",sessionManager.getMaxheight());

                }

                   return p;


            }







        };

        RequestQueue queue= Volley.newRequestQueue(getContext());
        queue.add(request);

    }







}
