package com.example.travel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.travel.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LocationsAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> locs;

    public LocationsAdapter(Context context2, ArrayList<String> locs2) {
        this.locs = locs2;
        this.context = context2;
    }

    public int getCount() {
        return this.locs.size();
    }

    public Object getItem(int position) {
        return this.locs.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(this.context).inflate(R.layout.content_location, null);
        ImageView imag = (ImageView) v.findViewById(R.id.imag);
        ((TextView) v.findViewById(R.id.name)).setText((CharSequence) this.locs.get(position));
        Picasso picasso = Picasso.get();
        StringBuilder sb = new StringBuilder();
        sb.append(this.context.getResources().getString(R.string.ip));
        sb.append("/getImg/");
        sb.append((String) this.locs.get(position));
        picasso.load(sb.toString()).into(imag);
        return v;
    }
}
