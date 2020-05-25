package com.example.travel.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travel.R;
import com.example.travel.model.Flight;

import java.util.ArrayList;

public class FlightAdapter extends RecyclerView.Adapter<FlightAdapter.MyHolder> {
    ArrayList<Flight> data;

    public FlightAdapter(ArrayList<Flight> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_flight, null));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        Flight f = data.get(position);
        holder.flight_name.setText(f.getAirline());
        holder.flight_from.setText(f.getSource());
        holder.flight_to.setText(f.getDestination());
        holder.time_arri.setText(f.getTakeoffTime());
        holder.time_dept.setText(f.getLandingTime());
        int duration = f.getDuration();

        int hour = 0;
        int min = 0;
        while (duration >= 60) {
            min += duration % 60;
            duration = duration / 60;
            hour += duration;
        }
        if (hour != 0)
            if (hour < 9)
                holder.flight_duration.setText("0" + hour + "h " + min + "m");
            else
                holder.flight_duration.setText(hour + "h " + min + "m");
        else if (hour == 0)
            holder.flight_duration.setText(min + "m");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView flight_name, flight_from, flight_to, flight_duration, flight_stop, time_dept, time_arri;

        public MyHolder(@NonNull View view) {
            super(view);
            flight_name = view.findViewById(R.id.airline_name);
            flight_from = view.findViewById(R.id.airline_from);
            flight_to = view.findViewById(R.id.airline_to);
            flight_duration = view.findViewById(R.id.airline_duration);
            flight_stop = view.findViewById(R.id.airline_stop);
            time_dept = view.findViewById(R.id.airline_dept);
            time_arri = view.findViewById(R.id.airline_arri);
        }

    }
}
