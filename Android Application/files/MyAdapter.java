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

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Metrics> metrics;


    public MyAdapter(Context c , ArrayList<Metrics> me)
    {
        context = c;
        metrics = me;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.air_humidity.setText(metrics.get(position).getAir_humidity());
        holder.air_temp.setText(metrics.get(position).getAir_temp());
        holder.basil_moist.setText(metrics.get(position).getBasil_moist());
        holder.lettuce_moist.setText(metrics.get(position).getLettuce_moist());
        holder.onion_moist.setText(metrics.get(position).getOnion_moist());
        holder.water_ph.setText(metrics.get(position).getWater_ph());
        holder.water_temp.setText(metrics.get(position).getWater_temp());
        //Picasso.get().load(profiles.get(position).getProfilePic()).into(holder.profilePic);
        /*if(metrics.get(position).getPermission()) {
            holder.btn.setVisibility(View.VISIBLE);
            holder.onClick(position);
        } */
    }

    @Override
    public int getItemCount() {
        return metrics.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView air_humidity,air_temp,basil_moist,lettuce_moist,onion_moist,water_ph,water_temp;
        //ImageView profilePic;
        //Button btn;

        public MyViewHolder(View itemView) {
            super(itemView);
            air_humidity = (TextView) itemView.findViewById(R.id.air_humidity);
            air_temp = (TextView) itemView.findViewById(R.id.air_temp);
            basil_moist = (TextView) itemView.findViewById(R.id.basil_moist);
            lettuce_moist = (TextView) itemView.findViewById(R.id.lettuce_moist);
            onion_moist = (TextView) itemView.findViewById(R.id.onion_moist);
            water_ph = (TextView) itemView.findViewById(R.id.water_ph);
            water_temp = (TextView) itemView.findViewById(R.id.water_temp);
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