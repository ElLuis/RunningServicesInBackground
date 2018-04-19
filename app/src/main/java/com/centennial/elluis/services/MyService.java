package com.centennial.elluis.services;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {
    int counter = 0;
    static final int UPDATE_INTERVAL = 1000;
    private Timer timer = new Timer();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //Running multiple apps
        /*// We want this service to continue running until it is explicitly
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
        *//*Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        return START_STICKY; *//*
    }*/

        //Performing Tasks in a Service Asynchronously
        // We want this service to continue running until it is explicitly
// stopped, so return sticky.
//Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        /*try {
            new DoBackgroundTask().execute(
                    new URL("http://www.amazon.com/somefiles.pdf"),
                    new URL("http://www.wrox.com/somefiles.pdf"),
                    new URL("http://www.google.com/somefiles.pdf"),
                    new URL("http://www.learn2develop.net/somefiles.pdf"));
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
    }
    private class DoBackgroundTask extends AsyncTask<URL, Integer, Long> {
        protected Long doInBackground(URL... urls) {
            int count = urls.length;
            long totalBytesDownloaded = 0;
            for (int i = 0; i < count; i++) {
                totalBytesDownloaded += DownloadFile(urls[i]);
//---calculate percentage downloaded and
// report its progress---
                publishProgress((int) (((i+1) / (float) count) * 100));
            }
            return totalBytesDownloaded;
        }
        protected void onProgressUpdate(Integer... progress) { //report the progress of the background task to the user
            Log.d("Downloading files",
                    String.valueOf(progress[0]) + "% downloaded");
            Toast.makeText(getBaseContext(),
                    String.valueOf(progress[0]) + "% downloaded",
                    Toast.LENGTH_LONG).show();
        }
        protected void onPostExecute(Long result) { //Called when doInBackground method has finished
            Toast.makeText(getBaseContext(),
                    "Downloaded " + result + " bytes",
                    Toast.LENGTH_LONG).show();
            stopSelf();
        }
    }*/

        //Running repeated tasks using the Timer class
        // We want this service to continue running until it is explicitly
// stopped, so return sticky.
//Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        doSomethingRepeatedly();
        try {
            new DoBackgroundTask().execute(
                    new URL("http://www.amazon.com/somefiles.pdf"),
                    new URL("http://www.wrox.com/somefiles.pdf"),
                    new URL("http://www.google.com/somefiles.pdf"),
                    new URL("http://www.learn2develop.net/somefiles.pdf"));
        } catch (MalformedURLException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        return START_STICKY;
    }
    private void doSomethingRepeatedly() {
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                Log.d("MyService", String.valueOf(++counter));
            }
        }, 0, UPDATE_INTERVAL);
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
    }
    private class DoBackgroundTask extends AsyncTask<URL, Integer, Long> {
        protected Long doInBackground(URL... urls) {
            int count = urls.length;
            long totalBytesDownloaded = 0;
            for (int i = 0; i < count; i++) {
                totalBytesDownloaded += DownloadFile(urls[i]);
//---calculate percentage downloaded and
// report its progress---
                publishProgress((int) (((i+1) / (float) count) * 100));
            }
            return totalBytesDownloaded;
        }
        protected void onProgressUpdate(Integer... progress) {
            Log.d("Downloading files",
                    String.valueOf(progress[0]) + "% downloaded");
            Toast.makeText(getBaseContext(),
                    String.valueOf(progress[0]) + "% downloaded",
                    Toast.LENGTH_LONG).show();
        }
        protected void onPostExecute(Long result) {
            Toast.makeText(getBaseContext(),
                    "Downloaded " + result + " bytes",
                    Toast.LENGTH_LONG).show();
            stopSelf();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null){
            timer.cancel();
        }
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }
}
