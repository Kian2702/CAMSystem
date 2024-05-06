package com.example.cams;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class login extends AppCompatActivity {

    Button login;
    EditText password, email;
    TextView SignupRedirect, BackStartup;
    FirebaseAuth mAuth;
    ProgressBar progress;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), homepage.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.emailli);
        password = findViewById(R.id.passwordli);
        SignupRedirect = findViewById(R.id.redirectsu);
        BackStartup = findViewById(R.id.backli);
        progress = findViewById(R.id.progressBar);
        login = findViewById(R.id.loginli);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.setVisibility(View.VISIBLE);
                String emailli = email.getText().toString();
                String passwordli = password.getText().toString();
                mAuth.signInWithEmailAndPassword(emailli, passwordli)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progress.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), homepage.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(login.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });

        SignupRedirect.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), signup.class);
            startActivity(intent);
            finish();
        });

        BackStartup.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Startup.class);
            startActivity(intent);
            finish();
        });
    }
}