package com.centennial.elluis.services;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
        public void startService(View view) {
            startService(new Intent(getBaseContext(), MyService.class));
            //From another app
            /*startService(new Intent("net.learn2develop.MyService"));*/
        }
        public void stopService(View view) {
            stopService(new Intent(getBaseContext(), MyService.class));
        }
    }
