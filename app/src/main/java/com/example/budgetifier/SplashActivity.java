package com.example.budgetifier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences=getSharedPreferences(LoginPage.PREFS_NAME,0);
                Boolean hasLoggedin=sharedPreferences.getBoolean("Has logged in",false);

                if (hasLoggedin){
                    Intent iHome=new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(iHome);
                }else {
                    Intent ilogin=new Intent(SplashActivity.this,LoginPage.class);
                    startActivity(ilogin);
                }
                finish();
            }
        },5000);
    }
}