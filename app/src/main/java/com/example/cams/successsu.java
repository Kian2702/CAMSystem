package com.example.cams;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class successsu extends AppCompatActivity {

    Button homepage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_successsu);

        homepage = findViewById(R.id.getstarted);

        homepage.setOnClickListener(view -> {
            Intent intent = new Intent(successsu.this, homepage.class);
            startActivity(intent);
        });
    }
}