package com.example.travel.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.travel.R;
import com.example.travel.model.Train;


import java.util.ArrayList;

public class RAdapter extends Adapter<RAdapter.Myholder> {

    /* renamed from: ar */
    ArrayList<Train> f95ar;
    final char[] ddddd = {'S', 'M', 'T', 'W', 'T', 'F', 'S'};
    String classes_name[] = {"SL","CC","AC3","AC2","AC1","First","2S"};
    Context context;
    class Myholder extends ViewHolder {

        /* renamed from: b1 */
        Button f96b1;

        /* renamed from: b2 */
        Button f97b2;

        /* renamed from: b3 */
        Button f98b3;
        TextView train_days;
        TextView train_name;
        TextView train_duration,time_dest,time_dept;
        LinearLayout classes;

        Myholder(View view) {
            super(view);
            this.train_name = (TextView) view.findViewById(R.id.textView2);
            this.train_days = (TextView) view.findViewById(R.id.textView4);
            this.train_duration = (TextView) view.findViewById(R.id.textView3);
            this.time_dept = (TextView) view.findViewById(R.id.textView5);
            this.time_dest = (TextView) view.findViewById(R.id.textView6);
            classes = view.findViewById(R.id.classes);
        }
    }

    public RAdapter(ArrayList<Train> ar) {
        this.f95ar = ar;
    }

    public Myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new Myholder(LayoutInflater.from(context).inflate(R.layout.card_view, null));
    }

    public void onBindViewHolder(Myholder holder, int position) {
        Train t = f95ar.get(position);
        holder.train_name.setText(t.getTrain_num()+" "+t.getTrain_name().trim());
        holder.time_dept.setText(t.getArr_time()+"\t"+t.getSrc());
        holder.time_dest.setText(t.getDest_time()+"\t"+t.getDest());
        String days = t.getDays();
        String text_days = "";
        for(int i=0;i<days.length();i++){
            if(days.charAt(i)=='y'){
                text_days+=ddddd[i]+" ";
            }else{
                text_days+="_ ";
            }
        }
        holder.train_days.setText(text_days);
        int duration = t.getDuration();

        int hour = 0;
        int min = 0;
        while (duration >= 60) {
            min += duration % 60;
            duration = duration / 60;
            hour += duration;
        }
        if (hour != 0)
            if (hour < 9)
                holder.train_duration.setText("0" + hour + "h " + min + "m");
            else
                holder.train_duration.setText(hour + "h " + min + "m");
        else if (hour == 0)
            holder.train_duration.setText(min + "m");

        for(int i=0;i<t.getClasses().size();i++){
            if(t.getClasses().get(i)!=0){
                int style = R.style.Widget_AppCompat_Button_Colored;
                Button b = new Button(new ContextThemeWrapper(context, style),null, style);
                b.setTextSize(TypedValue.COMPLEX_UNIT_DIP,18.0f);
                LinearLayout.LayoutParams params =new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.setMargins(0,0,0,0);
                b.setLayoutParams(params);
                b.setMaxLines(2);
                b.setText(classes_name[i]+"\n\\u20B9 "+t.getClasses().get(i));
                b.setGravity(View.TEXT_ALIGNMENT_CENTER);
                holder.classes.addView(b);
            }
        }
    }

    public int getItemCount() {
        return this.f95ar.size();
    }
}
