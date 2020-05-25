package com.example.travel.ui.flights;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travel.R;
import com.example.travel.adapter.FlightAdapter;
import com.example.travel.adapter.RAdapter;
import com.example.travel.model.Flight;
import com.example.travel.model.Train;
import com.example.travel.netutil.Network;
import com.example.travel.netutil.NetworkTask;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FlightFragment extends Fragment {

    ProgressBar progressBar;
    RecyclerView recyclerView;
    FlightAdapter adapter;
    ArrayList<Flight> data;
    Gson g = new Gson();
    String dest_code;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_flight, container, false);
        recyclerView= root.findViewById(R.id.flights);
        progressBar = root.findViewById(R.id.progress);
        data = new ArrayList<>();
        adapter = new FlightAdapter(data);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.left = 3;
                outRect.right = 3;
                outRect.top = 10;
            }
        });
        dest_code = getActivity().getIntent().getStringExtra("dest_code");

        new NetworkTask(getActivity().getApplicationContext(), new Network() {
            @Override
            public void postTask(String str) {
                try {
                    JSONArray array = new JSONArray(str);
                    for(int i=0;i<array.length();i++){
                        JSONObject o = array.getJSONObject(i);
                        data.add(g.fromJson(o.toString(),Flight.class));
                    }
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity().getApplicationContext() ,"Error", Toast.LENGTH_SHORT).show();
                }
            }
        },progressBar).execute(getResources().getString(R.string.ip)+"/findflight/s/"+dest_code+"/s");
        return root;
    }
}
