package com.centennial.elluis.services;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    IntentFilter intentFilter;

    //For Binding
    MyService serviceBinder;
    Intent i;
    private ServiceConnection connection = new ServiceConnection() {
        public void onServiceConnected(
                ComponentName className, IBinder service) {
//—-called when the connection is made—-
            serviceBinder = ((MyService.MyBinder)service).getService();
            try {
                URL[] urls = new URL[] {
                        new URL("http://www.amazon.com/somefiles.pdf"),
                        new URL("http://www.wrox.com/somefiles.pdf"),
                        new URL("http://www.google.com/somefiles.pdf"),
                        new URL("http://www.learn2develop.net/somefiles.pdf")};
//---assign the URLs to the service through the
// serviceBinder object---
                serviceBinder.urls = urls;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            startService(i);
        }
        public void onServiceDisconnected(ComponentName className) {
//---called when the service disconnects---
            serviceBinder = null;
        }
    };

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

            //startService(new Intent(getBaseContext(), MyIntentService.class)); //terminates itself OR Communicating service with activity

            //Binding Activity Properties
            i = new Intent(MainActivity.this, MyService.class);
            bindService(i, connection, Context.BIND_AUTO_CREATE);
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
