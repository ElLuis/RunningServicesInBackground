package com.centennial.elluis.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onResume() {
        super.onResume();
//---intent to filter for file downloaded intent---
        intentFilter = new IntentFilter();
        intentFilter.addAction("FILE_DOWNLOADED_ACTION");
//---register the receiver---
        registerReceiver(intentReceiver, intentFilter);
    }
    @Override
    public void onPause() {
        super.onPause();
//---unregister the receiver---
        unregisterReceiver(intentReceiver);
    }

        public void startService(View view) {
            //startService(new Intent(getBaseContext(), MyService.class));
            //From another app
            /*startService(new Intent("net.learn2develop.MyService"));*/

            startService(new Intent(getBaseContext(), MyIntentService.class)); //terminates itself
        }
        public void stopService(View view) {
            stopService(new Intent(getBaseContext(), MyService.class)); //Also used for communicating service with activity

            //stopService(new Intent(MainActivity.this, MyIntentService.class));
        }

    private BroadcastReceiver intentReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(getBaseContext(), "File downloaded!",
                    Toast.LENGTH_LONG).show();
        }
    };
    }
