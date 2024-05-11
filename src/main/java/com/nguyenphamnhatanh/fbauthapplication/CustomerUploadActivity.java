package com.nguyenphamnhatanh.fbauthapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nguyenphamnhatanh.fbauthapplication.Domain.User;
import com.nguyenphamnhatanh.fbauthapplication.databinding.ActivityCustomerUploadBinding;

public class CustomerUploadActivity extends BaseActivity {

    ActivityCustomerUploadBinding binding;
    DatabaseReference cusRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCustomerUploadBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        cusRef = database.getReference().child("users");

        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserData();
            }
        });
    }

    private void saveUserData() {
        String name = binding.uploadName.getText().toString().trim();
        String email = binding.uploadEmail.getText().toString().trim();
        String address = binding.uploadAddress.getText().toString().trim();
        String phone = binding.uploadPhone.getText().toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(address) || TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }
        // Tạo một User object với các thông tin được nhập
        User user = new User(name, email, address, phone);

        cusRef.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Email đã tồn tại, cập nhật thông tin của người dùng đầu tiên có cùng email
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String userId = snapshot.getKey();
                        cusRef.child(userId).setValue(user);
                        Toast.makeText(CustomerUploadActivity.this, "User data updated successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(CustomerUploadActivity.this, CRUDCustomerManagementActivity.class));
                        return;
                    }
                } else {
                    // Email chưa tồn tại, thêm người dùng mới vào Firebase
                    String userId = cusRef.push().getKey();
                    cusRef.child(userId).setValue(user);
                    Toast.makeText(CustomerUploadActivity.this, "User data saved successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(CustomerUploadActivity.this, CRUDCustomerManagementActivity.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(CustomerUploadActivity.this, "Failed to save user data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}