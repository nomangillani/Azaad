package com.example.azaad;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FilterActivity extends AppCompatActivity {
    String[] itemlist1;
    EditText minage,maxage,minhieht,maxheight,education,caste;
    int position=0;
    Button save;
   /* @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            this.finish();
        } else {
            getFragmentManager().popBackStack();
        }
    }
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        minage=findViewById(R.id.minage);
        maxage=findViewById(R.id.maxage);
        minhieht=findViewById(R.id.minheight);
        maxheight=findViewById(R.id.maxheight);
        caste=findViewById(R.id.cast);
        education=findViewById(R.id.education);
        save=findViewById(R.id.savefilter);
        minage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemlist1=getResources().getStringArray(R.array.minage);
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(FilterActivity.this);
                mBuilder.setTitle("Min age");
                mBuilder.setSingleChoiceItems(itemlist1, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        position=which;
                    }
                });
                mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        minage.setText(itemlist1[position]);

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



        maxage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemlist1=getResources().getStringArray(R.array.maxage);
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(FilterActivity.this);
                mBuilder.setTitle("Max age");
                mBuilder.setSingleChoiceItems(itemlist1, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        position=which;
                    }
                });
                mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        maxage.setText(itemlist1[position]);

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
        minhieht.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemlist1=getResources().getStringArray(R.array.minheight);
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(FilterActivity.this);
                mBuilder.setTitle("Min height");
                mBuilder.setSingleChoiceItems(itemlist1, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        position=which;
                    }
                });
                mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        minhieht.setText(itemlist1[position]);

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
        maxheight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemlist1=getResources().getStringArray(R.array.maxheight);
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(FilterActivity.this);
                mBuilder.setTitle("Max height");
                mBuilder.setSingleChoiceItems(itemlist1, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        position=which;
                    }
                });
                mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        minhieht.setText(itemlist1[position]);

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
        caste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemlist1=getResources().getStringArray(R.array.caste);
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(FilterActivity.this);
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
        education.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemlist1=getResources().getStringArray(R.array.education);
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(FilterActivity.this);
                mBuilder.setTitle("Education");
                mBuilder.setSingleChoiceItems(itemlist1, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        position=which;
                    }
                });
                mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        education.setText(itemlist1[position]);

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

save.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if(minage.getText().toString().equals("none")|maxage.getText().toString().equals("none")|minhieht.getText().toString().equals("none")|maxheight.getText().toString().equals("none")&&caste.getText().toString().equals("none")|education.getText().toString().equals("none")){


            Toast.makeText(FilterActivity.this, "Don't set filter, If your want to set please set all", Toast.LENGTH_SHORT).show();
//            Fragment fragment=null ;
//            fragment=new HomeFragment();
//            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//            ft.replace(R.id.container, fragment);
//            ft.commit();

           /* Fragment fragment=null;
            fragment=new HomeFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().add(R.id.container, fragment, TAG).addToBackStack().commit();;
            Bundle args = new Bundle();
            //args.putString("Position",""+position);
            fragment.setArguments(args);
            fragmentTransaction.replace(R.id.homecantainer, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();*/



           // getSupportFragmentManager().beginTransaction.add(R.id.container, fragment, TAG).addToBackStack().commit();

        }
        else{
            FilterSessionManager manager=new FilterSessionManager(getApplicationContext());
            manager.setfilter(minage.getText().toString(),maxage.getText().toString(),minhieht.getText().toString(),maxheight.getText().toString(),caste.getText().toString(),education.getText().toString());
            Intent i=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);


        }

       //SharedPreferences prefs = getSharedPreferences("filter", MODE_PRIVATE);
       // String name = prefs.getString("caste", "");//"No name defined" is the default value.
        //int idName = prefs.getInt("idName", 0); //0 is the default value.
        //Toast.makeText(FilterActivity.this, ""+name, Toast.LENGTH_SHORT).show();*/
    }
});



    }
}
