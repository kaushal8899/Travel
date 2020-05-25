package com.example.travel.activities;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.drawerlayout.widget.DrawerLayout.SimpleDrawerListener;

import com.example.travel.MyReceiver;
import com.example.travel.R;
import com.example.travel.adapter.LocationsAdapter;
import com.example.travel.model.User;
import com.example.travel.netutil.Network;
import com.example.travel.netutil.NetworkTask;
import com.example.travel.util.SharedUtil;
import com.github.mzule.fantasyslide.SideBar;
import com.github.mzule.fantasyslide.SimpleFantasyListener;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Network {
    private DrawerLayout drawerLayout;
    Gson gson = new Gson();
    ArrayList<String> loc;
    GridView locs;
    ProgressBar progressBar;
    SharedUtil sharedUtil;

    /* renamed from: u */
    User f88u;
    TextView username;


    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_main2);
        this.sharedUtil = new SharedUtil(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle((CharSequence) "HOME");
        ProgressBar progressBar2 = (ProgressBar) findViewById(R.id.progress);
        this.progressBar = progressBar2;
        progressBar2.setVisibility(0);

        IntentFilter filter = new IntentFilter();
        filter.addAction("my.filter.speak");
        filter.addAction("my.filter.stop");
        registerReceiver(new MyReceiver(this), filter);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        User u = (User) this.gson.fromJson(this.sharedUtil.readdata("user_details", "null"), User.class);
        this.f88u = u;
        final DrawerArrowDrawable indicator = new DrawerArrowDrawable(this);
        indicator.setColor(ViewCompat.MEASURED_STATE_MASK);
        indicator.setSpinEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator((Drawable) indicator);
        setListener();
        this.drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        TextView textView = (TextView) findViewById(R.id.username);
        this.username = textView;
        StringBuilder sb = new StringBuilder();
        sb.append("Welcome ");
        sb.append(u.getName());
        textView.setText(sb.toString());
        this.drawerLayout.setScrimColor(0);
        this.drawerLayout.setSelected(true);
        this.drawerLayout.addDrawerListener(new SimpleDrawerListener() {
            public void onDrawerSlide(View drawerView, float slideOffset) {
                if (((ViewGroup) drawerView).getChildAt(1).getId() == R.id.leftSideBar) {
                    indicator.setProgress(slideOffset);
                }
            }
        });
        this.drawerLayout.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Swipe Screen from Left Edge to Open.", Toast.LENGTH_LONG).show();
            }
        });
        this.locs = (GridView) findViewById(R.id.locations);
        NetworkTask networkTask = new NetworkTask(this, this, this.progressBar);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(getResources().getString(R.string.ip));
        sb2.append("/getalllocations");
        networkTask.execute(new String[]{sb2.toString()});
    }

    public void postTask(String data) {
        this.loc = new ArrayList<>();
        for (String s : data.split(" ")) {
            this.loc.add(s);
        }
        LocationsAdapter adapter = new LocationsAdapter(this, this.loc);
        adapter.notifyDataSetChanged();
        this.locs.setAdapter(adapter);
        this.locs.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, LocationActivity.class).putExtra("location", (String) MainActivity.this.loc.get(position)));
            }
        });
    }

    private void setListener() {
        final TextView tipView = (TextView) findViewById(R.id.tipView);
        ((SideBar) findViewById(R.id.leftSideBar)).setFantasyListener(new SimpleFantasyListener() {
            public boolean onHover(View view, int index) {
                tipView.setVisibility(0);
                if (view instanceof TextView) {
                    tipView.setText(String.format("%s", new Object[]{((TextView) view).getText().toString()}));
                } else if (view == null || view.getId() != R.id.userInfo) {
                    tipView.setVisibility(4);
                } else {
                    tipView.setText(String.format("Userinfo", new Object[0]));
                }
                return false;
            }

            public boolean onSelect(View view, int index) {
                tipView.setVisibility(4);
                if (!(index == 0 || index == 1)) {
                    if (index == 2) {
                        MainActivity.this.startActivity(new Intent(MainActivity.this, ProfileActivity.class).putExtra("user", MainActivity.this.f88u));
                    } else if (index != 3 && index == 4) {
                        MainActivity.this.sharedUtil.writedata("islogin", "false");
                        MainActivity.this.sharedUtil.writedata("user_details", "null");
                        MainActivity.this.startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        MainActivity.this.overridePendingTransition(R.anim.slide_in_left, 17432579);
                        MainActivity.this.finish();
                    }
                }
                return false;
            }

            public void onCancel() {
                tipView.setVisibility(4);
            }
        });
    }
}
