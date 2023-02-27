package com.example.budgetifier;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.budgetifier.databinding.ActivityAddIncome2Binding;

public class addIncome extends AppCompatActivity {
    ActivityAddIncome2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAddIncome2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());





    }
}