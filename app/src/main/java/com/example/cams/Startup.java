package com.example.cams;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Startup extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_startup);

        Button login, signup;

        login = findViewById(R.id.loginbtn);
        signup = findViewById(R.id.signupbtn);

        login.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), login.class);
            startActivity(intent);
        });

        signup.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), signup.class);
            startActivity(intent);
        });
    }
}