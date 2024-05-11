package com.nguyenphamnhatanh.fbauthapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nguyenphamnhatanh.fbauthapplication.Domain.User;
import com.nguyenphamnhatanh.fbauthapplication.databinding.ActivitySignupBinding;

public class SignupActivity extends BaseActivity {
    private static final String TAG = "SignupActivity";
    private FirebaseAuth mAuth;
    private ActivitySignupBinding binding;
    FirebaseDatabase db;
    DatabaseReference ref;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();


        setVariable();

    }


    private void setVariable() {

            binding.signupBtn.setOnClickListener(v -> {
            String email = binding.userEdt.getText().toString().trim();
            String password = binding.passEdt.getText().toString().trim();
            String name = binding.nameEdt.getText().toString().trim();

            if (password.length() < 6) {
                Toast.makeText(SignupActivity.this,
                        "Your password must be at least 6 characters", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!name.isEmpty() && !email.isEmpty()) {
                User newUser = new User(name, email);
                db = FirebaseDatabase.getInstance();
                ref = db.getReference("User");
                ref.child(name).setValue(newUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(SignupActivity.this, ProfileActivity.class));

                            Toast.makeText(SignupActivity.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
                        } else {
                            // Handle failure
                            Toast.makeText(SignupActivity.this, "Failed to update data: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {

                                FirebaseUser user = mAuth.getCurrentUser();
                                DatabaseReference usersRef = ref.child("users");
                                String userId = user.getUid();
                                usersRef.child(userId).child("name").setValue(name);
                                usersRef.child(userId).child("email").setValue(email);

                                // Chuyển sang ProfileActivity và chuyển dữ liệu
                                Intent intent = new Intent(SignupActivity.this, ProfileActivity.class);
                                intent.putExtra("name", name);
                                intent.putExtra("email", email);
                                startActivity(intent);
                                finish();

                                Toast.makeText(SignupActivity.this, "Authentication success.", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignupActivity.this, MainActivity.class));
                            } else {
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(SignupActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        binding.textView5.setOnClickListener(v -> {
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }
}
