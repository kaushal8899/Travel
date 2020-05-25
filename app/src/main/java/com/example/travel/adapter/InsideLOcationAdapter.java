package com.example.travel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.travel.R;
import com.example.travel.model.Location;


import java.util.ArrayList;

public class InsideLOcationAdapter extends BaseAdapter {
    Context context;
    ArrayList<Location> list;

    public InsideLOcationAdapter(Context context2, ArrayList<Location> l) {
        this.context = context2;
        this.list = l;
    }

    public int getCount() {
        return this.list.size();
    }

    public Object getItem(int position) {
        return this.list.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(this.context).inflate(R.layout.content_inside_location, null);
        TextView t2 = (TextView) v.findViewById(R.id.loc2);
        String str = "";
        String str2 = "\"";
        ((TextView) v.findViewById(R.id.loc1)).setText(((Location) this.list.get(position)).getLocation().replace(str2, str));
        t2.setText(((Location) this.list.get(position)).getCityState().replace(str2, str));
        return v;
    }
}
