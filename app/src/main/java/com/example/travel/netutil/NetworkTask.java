package com.example.travel.netutil;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetworkTask extends AsyncTask<String, String, String> {
    Context context;
    Network network;
    private ProgressBar progressbar;

    public NetworkTask(Context context2, Network network2, ProgressBar progressBar) {
        this.context = context2;
        this.network = network2;
        this.progressbar = progressBar;
    }

    /* access modifiers changed from: protected */
    public void onPreExecute() {
        super.onPreExecute();
        ProgressBar progressBar = this.progressbar;
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    /* access modifiers changed from: protected */
    public String doInBackground(String... args) {
        try {
            String data = "";
            HttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet(args[0]);
            BufferedReader reader;
            HttpResponse res;
            if(args.length >=2){
                HttpPost post = new HttpPost(args[0]);
                post.setHeader("Content-Type", "application/json");
                post.setHeader("Accept", "application/json");
                post.setEntity(new StringEntity(args[2]));
                res = client.execute(post);
            }
            else
                res = client.execute(get);
            reader = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
            try {
                String line = reader.readLine();
                while (line != null) {
                    data += line;
                    line = reader.readLine();
                }
            } catch (Exception e) {

            }
            return data;
        } catch (MalformedURLException e2) {
            Log.e("E2",""+e2);
            return "NetworkError(-1)";
        } catch (IOException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(String s) {
        super.onPostExecute(s);
        ProgressBar progressBar = this.progressbar;
        if (progressBar != null) {
            progressBar.setVisibility(View.INVISIBLE);
        }
        this.network.postTask(s);
    }
}
