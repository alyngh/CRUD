package com.nguyenphamnhatanh.fbauthapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nguyenphamnhatanh.fbauthapplication.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {

    private ActivityProfileBinding binding;
    private FirebaseDatabase db;
    private FirebaseAuth mAuth;
    private DatabaseReference ref;
    private boolean isEditing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        db = FirebaseDatabase.getInstance();
        ref = db.getReference();


        // Lấy dữ liệu từ Intent và hiển thị lên giao diện
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            ref = FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid());
            loadUserInfo();
        }
        binding.buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isEditing = true;
                enableEditMode();
                Toast.makeText(ProfileActivity.this, "You can edit your profile now", Toast.LENGTH_SHORT).show();
            }
        });

        binding.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserInfo();
            }
        });

    }

    private void saveUserInfo() {
        if (isEditing) {
            String name = binding.textViewName.getText().toString().trim();
            String email = binding.textViewEmail.getText().toString().trim();
            String address = binding.editTextAddress.getText().toString().trim();
            String phone = binding.editTextPhone.getText().toString().trim();

            ref.child("name").setValue(name);
            ref.child("email").setValue(email);
            ref.child("address").setValue(address);
            ref.child("phone").setValue(phone);

            Toast.makeText(this, "User Profile is updated!", Toast.LENGTH_SHORT).show();

            disableEditMode();
            isEditing = false;
        }
    }

    private void disableEditMode() {
        binding.textViewName.setEnabled(false);
        binding.textViewEmail.setEnabled(false);
        binding.editTextAddress.setEnabled(false);
        binding.editTextPhone.setEnabled(false);
    }

    private void enableEditMode() {
        binding.textViewName.setEnabled(true);
        binding.textViewEmail.setEnabled(true);
        binding.editTextAddress.setEnabled(true);
        binding.editTextPhone.setEnabled(true);
    }

    private void loadUserInfo() {
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String name = dataSnapshot.child("name").getValue(String.class);
                    String email = dataSnapshot.child("email").getValue(String.class);
                    String address = dataSnapshot.child("address").getValue(String.class);
                    String phone = dataSnapshot.child("phone").getValue(String.class);

                    // Hiển thị thông tin lên giao diện
                    binding.textViewName.setText(name);
                    binding.textViewEmail.setText(email);
                    binding.editTextAddress.setText(address);
                    binding.editTextPhone.setText(phone);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Xử lý khi có lỗi xảy ra
            }
        });
    }
}
