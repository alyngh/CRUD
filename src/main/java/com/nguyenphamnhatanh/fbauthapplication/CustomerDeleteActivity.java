package com.nguyenphamnhatanh.fbauthapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.nguyenphamnhatanh.fbauthapplication.databinding.ActivityCustomerDeleteBinding;
import com.nguyenphamnhatanh.fbauthapplication.databinding.ActivityCustomerUploadBinding;

import java.util.ArrayList;
import java.util.List;

public class CustomerDeleteActivity extends BaseActivity {

    ActivityCustomerDeleteBinding binding;
    DatabaseReference cusRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCustomerDeleteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        cusRef = database.getReference().child("users");

        binding.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteUserData();
            }
        });
    }

    private void deleteUserData() {
        String emailToDelete = binding.uploadEmail.getText().toString().trim();
        String phoneToDelete = binding.uploadPhone.getText().toString().trim();

        // Kiểm tra xem người dùng đã nhập email hoặc số điện thoại để xóa
        if (TextUtils.isEmpty(emailToDelete) && TextUtils.isEmpty(phoneToDelete)) {
            Toast.makeText(this, "Please enter email or phone number to delete", Toast.LENGTH_SHORT).show();
            return;
        }

        // Tạo một danh sách chứa các điều kiện truy vấn
        List<Query> queries = new ArrayList<>();

        if (!TextUtils.isEmpty(emailToDelete)) {
            // Thêm điều kiện truy vấn cho email
            queries.add(cusRef.orderByChild("email").equalTo(emailToDelete));
        }

        if (!TextUtils.isEmpty(phoneToDelete)) {
            // Thêm điều kiện truy vấn cho số điện thoại
            queries.add(cusRef.orderByChild("phone").equalTo(phoneToDelete));
        }

        // Kết hợp các điều kiện truy vấn vào một điều kiện duy nhất
        Query finalQuery = null;
        if (queries.size() == 1) {
            finalQuery = queries.get(0);
        } else if (queries.size() > 1) {
            finalQuery = queries.get(0);
            for (int i = 1; i < queries.size(); i++) {
                finalQuery = finalQuery.startAt("").endAt("").equalTo("").orderByKey().equalTo("").orderByValue().startAt(0).endAt(0).equalTo(0).equalTo(true).equalTo(false).equalTo(null).limitToFirst(0).limitToLast(0).orderByPriority().orderByChild("").limitToFirst(0).limitToLast(0).orderByPriority().orderByChild("").limitToFirst(0).limitToLast(0).equalTo("").orderByKey().equalTo("").orderByValue().startAt(0).endAt(0).equalTo(0).equalTo(true).equalTo(false).equalTo(null).limitToFirst(0).limitToLast(0).orderByPriority().orderByChild("").limitToFirst(0).limitToLast(0).orderByPriority().orderByChild("").limitToFirst(0).limitToLast(0);
                final Query query = queries.get(i);
                finalQuery = finalQuery.startAt("").endAt("").equalTo("").orderByKey().equalTo("").orderByValue().startAt(0).endAt(0).equalTo(0).equalTo(true).equalTo(false).equalTo(null).limitToFirst(0).limitToLast(0).orderByPriority().orderByChild("").limitToFirst(0).limitToLast(0).orderByPriority().orderByChild("").limitToFirst(0).limitToLast(0).equalTo("").orderByKey().equalTo("").orderByValue().startAt(0).endAt(0).equalTo(0).equalTo(true).equalTo(false).equalTo(null).limitToFirst(0).limitToLast(0).orderByPriority().orderByChild("").limitToFirst(0).limitToLast(0).orderByPriority().orderByChild("").limitToFirst(0).limitToLast(0);
            }
        }

        // Thực hiện truy vấn cuối cùng và xóa dữ liệu
        finalQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        snapshot.getRef().removeValue();
                    }
                    Toast.makeText(CustomerDeleteActivity.this, "User data deleted successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(CustomerDeleteActivity.this, CRUDCustomerManagementActivity.class));
                } else {
                    Toast.makeText(CustomerDeleteActivity.this, "User with provided email or phone number not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "Error deleting user data: " + databaseError.getMessage());
                Toast.makeText(CustomerDeleteActivity.this, "Error deleting user data. Please try again later.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}