package com.example.budgetifier;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.getbase.floatingactionbutton.FloatingActionButton;

public class HomeFragment extends Fragment {
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       View v=inflater.inflate(R.layout.fragment_home,container,false);

        //FloatingActionButton addincome=(FloatingActionButton) v.findViewById(R.id.addincome);
        FloatingActionButton addbtn=(FloatingActionButton) v.findViewById(R.id.addbtn);
        /*addincome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent income;
                income = new Intent(getActivity(),addIncome.class);
                startActivity(income);
            }
        });*/
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent expense=new Intent(getActivity(),addExpense.class);
                startActivity(expense);
            }
        });
        return v;
    }
}