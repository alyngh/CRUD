package com.nguyenphamnhatanh.fbauthapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nguyenphamnhatanh.fbauthapplication.Domain.User;
import com.nguyenphamnhatanh.fbauthapplication.databinding.ActivityCustomerDetailBinding;

import java.util.List;

public class CustomerDetailActivity extends BaseActivity {
    ActivityCustomerDetailBinding binding;

    DatabaseReference cusRef;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCustomerDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            cusRef = FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid());

        }

        binding.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomerDetailActivity.this, CRUDCustomerManagementActivity.class));
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String name = extras.getString("name");
            String email = extras.getString("email");
            String address = extras.getString("address");
            String phone = extras.getString("phone");

            // Populate TextViews with retrieved data
            binding.textViewName.setText(name);
            binding.textViewEmail.setText(email);
            binding.editTextAddress.setText(address);
            binding.editTextPhone.setText(phone);
        }
    }
}

