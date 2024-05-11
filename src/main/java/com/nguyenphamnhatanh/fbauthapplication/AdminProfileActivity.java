package com.nguyenphamnhatanh.fbauthapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nguyenphamnhatanh.fbauthapplication.databinding.ActivityAdminProfileBinding;

public class AdminProfileActivity extends BaseActivity {

    private ActivityAdminProfileBinding binding;
    private DatabaseReference adminRef;
    private FirebaseAuth mAuth;
    private boolean isEditing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            adminRef = FirebaseDatabase.getInstance().getReference().child("admins").child(user.getUid());
            loadAdminInfo();
    }

        binding.buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isEditing = true;
                enableEditMode();
                Toast.makeText(AdminProfileActivity.this, "You can edit your profile now", Toast.LENGTH_SHORT).show();
            }
        });

        binding.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAdminInfo();
            }
        });
}

    private void saveAdminInfo() {
        if (isEditing) {
            String name = binding.textViewName.getText().toString().trim();
            String email = binding.textViewEmail.getText().toString().trim();
            String role = binding.editTextRole.getText().toString().trim();
            String phone = binding.editTextPhone.getText().toString().trim();

            adminRef.child("name").setValue(name);
            adminRef.child("email").setValue(email);
            adminRef.child("role").setValue(role);
            adminRef.child("phone").setValue(phone);

            Toast.makeText(this, "Admin Profile is updated!", Toast.LENGTH_SHORT).show();

            disableEditMode();
            isEditing = false;
        }
    }

    private void disableEditMode() {
        binding.textViewName.setEnabled(false);
        binding.textViewEmail.setEnabled(false);
        binding.editTextRole.setEnabled(false);
        binding.editTextPhone.setEnabled(false);
    }

    private void enableEditMode() {
        binding.textViewName.setEnabled(true);
        binding.textViewEmail.setEnabled(true);
        binding.editTextRole.setEnabled(true);
        binding.editTextPhone.setEnabled(true);
    }

    private void loadAdminInfo() {
        adminRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String name = dataSnapshot.child("name").getValue(String.class);
                    String email = dataSnapshot.child("email").getValue(String.class);
                    String role = dataSnapshot.child("role").getValue(String.class);
                    String phone = dataSnapshot.child("phone").getValue(String.class);

                    // Hiển thị thông tin lên giao diện
                    binding.textViewName.setText(name);
                    binding.textViewEmail.setText(email);
                    binding.editTextRole.setText(role);
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