package com.example.cams;

import static androidx.appcompat.app.AlertDialog.*;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class homepage extends AppCompatActivity {

    FloatingActionButton entry, link;
    TextView logout;
    FirebaseAuth mAuth;
    RecyclerView recyclerView;
    List<EntryClass> list;
    DatabaseReference reference;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_homepage);

        entry = findViewById(R.id.addEntry);
        logout = findViewById(R.id.logout);
        mAuth = FirebaseAuth.getInstance();
        recyclerView = findViewById(R.id.recyclerView);
        reference = FirebaseDatabase.getInstance().getReference("Entries");
        builder = new AlertDialog.Builder(this);
        link = findViewById(R.id.linksheets);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapter adapter = new myAdapter(homepage.this, list);
        recyclerView.setAdapter(adapter);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot itemSnapshot : snapshot.getChildren()){
                    EntryClass entryClass = itemSnapshot.getValue(EntryClass.class);
                    entryClass.setKey(itemSnapshot.getKey());
                    list.add(entryClass);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Google Sheets").setMessage("Go to Google Sheets?").setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                goToUrl("https://docs.google.com/spreadsheets/d/15O04jkw-QNJPAJL4OP9Egp0NNS8V8zcKMmDIhLPwMd0/edit#gid=1896555206");
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .show();
            }
        });


        entry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), addEntry.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Warning!").setMessage("Do you wish to proceed with the action?").setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mAuth.signOut();
                                signOutUser();
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .show();
            }
        });
    }

    private void signOutUser() {
        Intent mainActivity = new Intent(getApplicationContext(), Startup.class);
        mainActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainActivity);
        finish();
    }

    void goToUrl(String s){
        try{
            Uri uri = Uri.parse(s);
            startActivity(new Intent(Intent.ACTION_VIEW, uri));
        } catch (Exception e) {
            Toast.makeText(this, "No website linked.", Toast.LENGTH_SHORT).show();;
        }
    }

}