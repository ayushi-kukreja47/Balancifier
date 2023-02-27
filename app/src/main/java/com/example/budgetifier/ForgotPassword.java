package com.example.budgetifier;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity implements View.OnClickListener {
    private TextView reset,reset_login;
    private EditText emailVerify;
    private Button ResetButton ;
    private FirebaseAuth auth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        reset=(TextView) findViewById(R.id.reset);
        reset_login=(TextView) findViewById(R.id.reset_login);
        reset_login.setOnClickListener(this);
        emailVerify=(EditText) findViewById(R.id.emailVerify);
        ResetButton=(Button) findViewById(R.id.ResetButton);
        ResetButton.setOnClickListener(this);

        auth= FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.reset_login:
                startActivity(new Intent(this,LoginPage.class));
                break;
            case R.id.ResetButton:
                resetPassword();
                break;
        }

    }

    private void resetPassword() {
        String email=emailVerify.getText().toString().trim();

        if (email.isEmpty()){
            emailVerify.setError("Email is required");
            emailVerify.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailVerify.setError("Please provide the valid email");
            emailVerify.requestFocus();
            return;
        }
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ForgotPassword.this, "Check you email", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(ForgotPassword.this, "Try Again!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}