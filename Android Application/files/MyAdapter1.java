package com.example.test1;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
//import com.squareup.picasso.Picasso;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyAdapter1 extends RecyclerView.Adapter<MyAdapter1.MyViewHolder1> {

    Context context;
    ArrayList<Metrics1> metrics;


    public MyAdapter1(Context c , ArrayList<Metrics1> me)
    {
        context = c;
        metrics = me;
    }

    @NonNull
    @Override
    public MyViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder1(LayoutInflater.from(context).inflate(R.layout.cardview1,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder1 holder, int position) {
        holder.air_humidity_1.setText(metrics.get(position).getAir_humidity_1());
        holder.air_temp_1.setText(metrics.get(position).getAir_temp_1());
        holder.basil_moist_1.setText(metrics.get(position).getBasil_moist_1());
        holder.lettuce_moist_1.setText(metrics.get(position).getLettuce_moist_1());
        holder.onion_moist_1.setText(metrics.get(position).getOnion_moist_1());
        holder.water_ph_1.setText(metrics.get(position).getWater_ph_1());
        holder.water_temp_1.setText(metrics.get(position).getWater_temp_1());
    }

    @Override
    public int getItemCount() {
        return metrics.size();
    }

    class MyViewHolder1 extends RecyclerView.ViewHolder
    {
        TextView air_humidity_1,air_temp_1,basil_moist_1,lettuce_moist_1,onion_moist_1,water_ph_1,water_temp_1;
        //ImageView profilePic;
        //Button btn;

        public MyViewHolder1(View itemView) {
            super(itemView);
            air_humidity_1 = (TextView) itemView.findViewById(R.id.air_humidity_1);
            air_temp_1 = (TextView) itemView.findViewById(R.id.air_temp_1);
            basil_moist_1 = (TextView) itemView.findViewById(R.id.basil_moist_1);
            lettuce_moist_1 = (TextView) itemView.findViewById(R.id.lettuce_moist_1);
            onion_moist_1 = (TextView) itemView.findViewById(R.id.onion_moist_1);
            water_ph_1 = (TextView) itemView.findViewById(R.id.water_ph_1);
            water_temp_1 = (TextView) itemView.findViewById(R.id.water_temp_1);
            //profilePic = (ImageView) itemView.findViewById(R.id.profilePic);
            //btn = (Button) itemView.findViewById(R.id.checkDetails);
        }
        /*public void onClick(final int position)
        {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, position+" is clicked", Toast.LENGTH_SHORT).show();
                }
            });
        }   */
    }
}