package com.example.azaad;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;
    int Count=0;
    SessionManager manager;
    public  static  Activity fa;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    replaceFragment(R.id.navigation_home);
                    return true;
                case R.id.navigation_favourate:
                    replaceFragment(R.id.navigation_favourate);
                    // mTextMessage.setText(R.string.title_notifications);
                    return true;
                case R.id.navigation_dashboard:
                   // mTextMessage.setText(R.string.title_dashboard);
                    replaceFragment(R.id.navigation_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    replaceFragment(R.id.navigation_notifications);
                    return true;

            }
            return false;
        }
    };



    @Override
    public void onBackPressed() {
        BottomNavigationView mBottomNavigationView = findViewById(R.id.nav_view);
       // replaceFragment(R.id.navigation_home);

        if (mBottomNavigationView.getSelectedItemId() == R.id.navigation_home)
        {
            super.onBackPressed();
            finish();
        }
        else
        {
            mBottomNavigationView.setSelectedItemId(R.id.navigation_home);
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager=new SessionManager(getApplicationContext());
        fa=MainActivity.this;
        manager.checkLogin();
       // manager.logoutUser();
        BottomNavigationView navView = findViewById(R.id.nav_view);
        replaceFragment(R.id.navigation_home);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    public  void replaceFragment(int id){
        Fragment fragment=null;
        if (id == R.id.navigation_home) {
            fragment = new HomeFragment();
        }
        else  if (id == R.id.navigation_favourate) {
            fragment=new FaverouteFragment();
            //fragment = new ChatFragment();
        }
        else  if (id == R.id.navigation_dashboard) {
           fragment=new ChatFragment();
        }
        else  if (id == R.id.navigation_notifications) {
            fragment = new CurrentUserFragment();

        }

        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.homecantainer, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        }
    }

}
