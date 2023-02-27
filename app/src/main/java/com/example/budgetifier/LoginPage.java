package com.example.budgetifier;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginPage extends AppCompatActivity implements View.OnClickListener{
    public static String PREFS_NAME="MyPrefsFile";
    private TextView signupText,forgotText;
    private TextView login;
    private EditText username,password;
    private Button loginButton;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        signupText=(TextView) findViewById(R.id.signupText);
        signupText.setOnClickListener(this);

        forgotText=(TextView) findViewById(R.id.forgotText);
        forgotText.setOnClickListener(this);

        login=(TextView) findViewById(R.id.login);
        username=(EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);

        loginButton=(Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(this);
        mAuth=FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.signupText:
                startActivity(new Intent(this,signUp.class));
                break;
            case R.id.loginButton:
                loginUser();
                break;
            case R.id.forgotText:
                startActivity(new Intent(this,ForgotPassword.class));
        }

    }

    private void loginUser() {
        String email=username.getText().toString();
        String pswd=password.getText().toString().trim();

        if(email.isEmpty()){
            username.setError("Email is Required");
            username.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            username.setError("provide a valid email!");
            username.requestFocus();
            return;
        }
        if(pswd.isEmpty()){
            password.setError("password is required");
            password.requestFocus();
            return;
        }
        if(pswd.length()<6){
            password.setError("Minimum password length should be 6 characters");
            password.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,pswd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                    if (user.isEmailVerified()) {
                        SharedPreferences sharedPreferences=getSharedPreferences(LoginPage.PREFS_NAME,0);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putBoolean("Has logged in",true);
                        editor.commit();
                        startActivity(new Intent(LoginPage.this, MainActivity.class));
                        finish();
                    }else {
                        user.sendEmailVerification();
                        Toast.makeText(LoginPage.this, "Check your rmail to verify", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(LoginPage.this, "Failed to login! please check your password!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}