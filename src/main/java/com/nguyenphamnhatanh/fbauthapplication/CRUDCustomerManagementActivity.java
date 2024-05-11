package com.nguyenphamnhatanh.fbauthapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.nguyenphamnhatanh.fbauthapplication.Domain.User;
import com.nguyenphamnhatanh.fbauthapplication.databinding.ActivityCrudcustomerManagementBinding;

import java.util.List;
import java.util.ArrayList;

public class CRUDCustomerManagementActivity extends BaseActivity {

    private DatabaseReference cusRef;
    private CustomerAdapter adapter;
    private List<User> userList;
    private ActivityCrudcustomerManagementBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCrudcustomerManagementBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userList = new ArrayList<>();
        adapter = new CustomerAdapter(this, userList);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        cusRef = database.getReference().child("users");


        binding.recyclerView.setAdapter(adapter);



        binding.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processDelete();
            }
        });



        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                processUpload();
            }
        });

        retrieveUserData();

        adapter.setOnItemClickListener(new CustomerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(User user) {
                Intent intent = new Intent(CRUDCustomerManagementActivity.this, CustomerDetailActivity.class);
                intent.putExtra("name", user.getName());
                intent.putExtra("email", user.getEmail());
                intent.putExtra("address", user.getAddress());
                intent.putExtra("phone", user.getPhoneNumber());
                startActivity(intent);
            }
        });


    }

    private void processDelete() {
        startActivity(new Intent(this, CustomerDeleteActivity.class));
    }

    private void processUpload() {
        startActivity(new Intent(this, CustomerUploadActivity.class));
    }


    private void retrieveUserData() {
        Query query = cusRef.orderByKey(); // You can apply any sorting or filtering logic here if needed
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    userList.add(user);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });
    }
}
