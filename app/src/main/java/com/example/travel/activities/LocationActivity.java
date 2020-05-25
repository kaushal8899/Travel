package com.example.travel.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.example.travel.R;
import com.example.travel.adapter.InsideLOcationAdapter;
import com.example.travel.model.Location;
import com.example.travel.netutil.Network;
import com.example.travel.netutil.NetworkTask;
import com.example.travel.util.Justify;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LocationActivity extends AppCompatActivity implements Network {
    String disc;

    /* renamed from: g */
    Gson f87g = new Gson();
    ImageView img;
    ListView listView;
    ArrayList<Location> locations = new ArrayList<>();
    ProgressBar progressBar;
    ToggleButton speak;
    TextView textview;
    TextView title;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_location);
        this.progressBar = (ProgressBar) findViewById(R.id.progress);
        this.textview = (TextView) findViewById(R.id.loc);
        this.listView = (ListView) findViewById(R.id.inside_location);
        this.img = (ImageView) findViewById(R.id.location_inside_img);
        this.title = (TextView) findViewById(R.id.loc_name);
        String loc = getIntent().getExtras().getString("location");
        this.title.setText(loc);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        StringBuilder sb = new StringBuilder();
        sb.append("Locations in ");
        sb.append(loc);
        toolbar.setTitle((CharSequence) sb.toString());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Picasso picasso = Picasso.get();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(getResources().getString(R.string.ip));
        sb2.append("/getImgOld/old/");
        sb2.append(loc);
        picasso.load(sb2.toString()).into(this.img);
        NetworkTask networkTask = new NetworkTask(this, this, this.progressBar);
        StringBuilder sb3 = new StringBuilder();
        sb3.append(getResources().getString(R.string.ip));
        sb3.append("/locdetails/");
        sb3.append(loc);
        networkTask.execute(new String[]{sb3.toString()});
        NetworkTask networkTask2 = new NetworkTask(this, new Network() {
            public void postTask(String data) {
                LocationActivity.this.disc = data;
                LocationActivity.this.textview.setText(data);
                Justify.justify(LocationActivity.this.textview);
            }
        }, this.progressBar);
        StringBuilder sb4 = new StringBuilder();
        sb4.append(getResources().getString(R.string.ip));
        sb4.append("/getDisc/");
        sb4.append(loc);
        networkTask2.execute(new String[]{sb4.toString()});
    }

    public void postTask(String data) {
        JsonObject obj = (JsonObject) new JsonParser().parse(this.f87g.toJson((JsonElement) this.f87g.fromJson(data, JsonElement.class)));
        String str = "count";
        int count = obj.get(str).getAsInt();
        obj.remove(str);
        for (int i = 0; i < count; i++) {
            Location l = new Location();
            String str2 = "";
            String str3 = "\"";
            String str4 = ",";
            l.setSeason(obj.get("Season").getAsJsonArray().get(i).toString().replace(str3, str2).split(str4));
            l.setLocation(obj.get("Location").getAsJsonArray().get(i).toString().replace(str3, str2));
            l.setCityState(obj.get("City/State").getAsJsonArray().get(i).toString().replace(str3, str2));
            l.setStation(obj.get("Station").getAsJsonArray().get(i).toString().replace(str3, str2));
            l.setAirport(obj.get("Airport").getAsJsonArray().get(i).toString().replace(str3, str2));
            l.setNear_station(obj.get("Near_Station").getAsJsonArray().get(i).toString().replace(str3, str2));
            l.setNear_airport(obj.get("Near_Airport").getAsJsonArray().get(i).toString().replace(str3, str2));
            l.setSeason_start(obj.get("Season_Start").getAsJsonArray().get(i).toString().replace(str3, str2).split(str4));
            l.setSeason_end(obj.get("Season_End").getAsJsonArray().get(i).toString().replace(str3, str2).split(str4));
            l.setDay((int) obj.get("Day").getAsJsonArray().get(i).getAsDouble());
            l.setNight((int) obj.get("Night").getAsJsonArray().get(i).getAsDouble());
            l.setHasRail(obj.get("hasRail").getAsJsonArray().get(i).getAsString().replace(str3, str2));
            l.setHasFlight(obj.get("hasFlight").getAsJsonArray().get(i).getAsString().replace(str3, str2));
            this.locations.add(l);
        }
        this.listView.setAdapter(new InsideLOcationAdapter(this, this.locations));
        ToggleButton toggleButton = (ToggleButton) findViewById(R.id.speak);
        this.speak = toggleButton;
        toggleButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    LocationActivity.this.speak();
                } else {
                    LocationActivity.this.stop();
                }
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.location_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.transport) {
            String r = ((Location) this.locations.get(0)).getHasRail().equals("yes") ? ((Location) this.locations.get(0)).getStation() : ((Location) this.locations.get(0)).getNear_station();
            String r2 = ((Location) this.locations.get(0)).getHasFlight().equals("yes") ? ((Location) this.locations.get(0)).getAirport() : ((Location) this.locations.get(0)).getNear_airport();
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            sb.append("");
            sb2.append("");
            sb.append(r);
            sb2.append(r2);
            Toast.makeText(this, sb.toString(), Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, Transport.class).putExtra("dest", r).putExtra("dest_code",r2));
        }
        return super.onOptionsItemSelected(item);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        stop();
    }

    public void speak() {
        sendBroadcast(new Intent().setAction("my.filter.speak").putExtra("data", this.disc));
    }

    public void stop() {
        sendBroadcast(new Intent().setAction("my.filter.stop"));
    }
}
