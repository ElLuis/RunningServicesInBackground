package com.centennial.elluis.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;

public class MyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
// We want this service to continue running until it is explicitly
// stopped, so return sticky.
        // We want this service to continue running until it is explicitly
// stopped, so return sticky.
//Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        try {
            int result =
                    DownloadFile(new URL("http://www.amazon.com/somefile.pdf"));
            Toast.makeText(getBaseContext(),
                    "Downloaded " + result + " bytes",
                    Toast.LENGTH_LONG).show();
        } catch (MalformedURLException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        return START_STICKY;
    }
    private int DownloadFile(URL url) {
        try {
//---simulate taking some time to download a file---
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//---return an arbitrary number representing
// the size of the file downloaded---
        return 100;
        /*Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        return START_STICKY; */
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }
}
