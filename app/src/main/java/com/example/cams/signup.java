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

public class signup extends AppCompatActivity {

    Button signup;
    EditText email, password;
    TextView LoginRedirect, BackStartup;
    FirebaseAuth mAuth;
    ProgressBar progress;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(getApplicationContext(), homepage.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_signup);
        mAuth = FirebaseAuth.getInstance();

        signup = findViewById(R.id.signupsu);
        email = findViewById(R.id.emailsu);
        password = findViewById(R.id.passwordsu);
        progress = findViewById(R.id.progressBar);
        LoginRedirect = findViewById(R.id.redirectli);
        BackStartup = findViewById(R.id.backsu);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress.setVisibility(View.VISIBLE);

                String emailsu = email.getText().toString();
                String passwordsu = password.getText().toString();

                if (emailsu.isEmpty()){
                    Toast.makeText(signup.this, "Please enter all required fields.", Toast.LENGTH_SHORT).show();
                    email.requestFocus();
                }
                else if (passwordsu.isEmpty()) {
                    Toast.makeText(signup.this, "Please enter all required fields.", Toast.LENGTH_SHORT).show();
                    password.requestFocus();
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), successsu.class);
                    startActivity(intent);
                    finish();
                }

                mAuth.createUserWithEmailAndPassword(emailsu, passwordsu)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progress.setVisibility(View.GONE);
                                if(task.isSuccessful()) {

                                } else {
                                    Toast.makeText(signup.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });

        LoginRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), login.class);
                startActivity(intent);
                finish();
            }
        });

        BackStartup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Startup.class);
                startActivity(intent);
                finish();
            }
        });
    }
}