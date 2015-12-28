package com.example.provenlogic1.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

import com.lacronicus.easydatastorelib.DatastoreBuilder;

public class SplashActivity extends Activity {
    LoginPrefrence  datastore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splasg);
        datastore =  new DatastoreBuilder(PreferenceManager.getDefaultSharedPreferences(this)).create(LoginPrefrence.class);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(datastore.user_name().get() == null || datastore.user_name().get().equals("")){

                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                }else {
                    startActivity(new Intent(SplashActivity.this, ChatActivity.class));
                }
            }
        }, 2000);


    }


}
