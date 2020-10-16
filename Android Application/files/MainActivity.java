package com.example.test1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Toolbar;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;
//import android.content.Intent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<Metrics> list;
    ArrayList<Metrics1> list1;
    MyAdapter adapter;
    MyAdapter1 adapter1;
    private Button button,button1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        recyclerView = (RecyclerView) findViewById(R.id.myRecycler);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));


        button = (Button) findViewById(R.id.checkDetails);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference = FirebaseDatabase.getInstance().getReference().child("metrics1");
                reference.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        list1 = new ArrayList<Metrics1>();

                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            Metrics1 me = dataSnapshot1.getValue(Metrics1.class);
                            list1.add(me);
                        }
                        adapter1 = new MyAdapter1(MainActivity.this, list1);

                        recyclerView.setAdapter(adapter1);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(MainActivity.this, "Oοps.... Something is wrong", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        button1 = (Button) findViewById(R.id.checkDetails1);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                reference = FirebaseDatabase.getInstance().getReference().child("metrics");
                reference.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        list = new ArrayList<Metrics>();

                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            Metrics p = dataSnapshot1.getValue(Metrics.class);
                            list.add(p);
                        }
                        adapter = new MyAdapter(MainActivity.this, list);

                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(MainActivity.this, "Oοps.... Something is wrong", Toast.LENGTH_SHORT).show();
                    }
                });

            }});

    }

}