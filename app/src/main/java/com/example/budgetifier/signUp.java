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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class signUp extends AppCompatActivity implements View.OnClickListener{
    private TextView signup,LoginTxt;
    private EditText signupusername,pswd,cnfrmpswd,signupname;
    Button signupButton;

    private FirebaseAuth mAuth;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

       mAuth=FirebaseAuth.getInstance();

       signup=(TextView) findViewById(R.id.signup);

       LoginTxt=(TextView) findViewById(R.id.LoginTxt);
       LoginTxt.setOnClickListener(this);

       signupusername=(EditText) findViewById(R.id.signupusername);
       pswd=(EditText) findViewById(R.id.pswd);
       cnfrmpswd=(EditText) findViewById(R.id.cnfrmpswd);

       signupButton=(Button) findViewById(R.id.signupButton);
       signupButton.setOnClickListener(this);

       signupname=(EditText) findViewById(R.id.signupname);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.LoginTxt:
                startActivity(new Intent(this,LoginPage.class));
                break;
            case R.id.signupButton:
                signupUser();
                break;
        }
    }

    private void signupUser() {
        String email=signupusername.getText().toString();
        String password=pswd.getText().toString().trim();
        String name=signupname.getText().toString().trim();
        String confirmPassword=cnfrmpswd.getText().toString().trim();

        if(name.isEmpty()){
            signupname.setError("Name is Required");
            signupname.requestFocus();
            return;
        }
        if(email.isEmpty()){
            signupusername.setError("Email is Required");
            signupusername.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            signupusername.setError("provide a valid email!");
            signupusername.requestFocus();
            return;
        }
        if(password.isEmpty()){
            pswd.setError("password is required");
            pswd.requestFocus();
            return;
        }
        if(password.length()<6){
            pswd.setError("Minimum password length should be 6 characters");
            pswd.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            User user=new User(name,email);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){
                                                Toast.makeText(signUp.this, "User has been Registered Successfully", Toast.LENGTH_SHORT).show();
                                            }
                                            else{
                                                Toast.makeText(signUp.this, "Failed to Register!TRY AGAIN.", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }else{
                            Toast.makeText(signUp.this, "Failed to Register!TRY AGAIN.", Toast.LENGTH_SHORT).show();

                        }

                    }
                });
    }
}