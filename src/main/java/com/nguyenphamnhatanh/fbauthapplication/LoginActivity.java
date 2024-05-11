package com.nguyenphamnhatanh.fbauthapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nguyenphamnhatanh.fbauthapplication.databinding.ActivityLoginBinding;



public class LoginActivity extends BaseActivity {
    private static final String TAG = "LoginActivity";
    private FirebaseDatabase db;
    private FirebaseAuth mAuth;
    private DatabaseReference ref;

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        db = FirebaseDatabase.getInstance();
        ref = db.getReference();

        mAuth = FirebaseAuth.getInstance();


        setVariables();
    }

    private void setVariables() {
        binding.loginBtn.setOnClickListener(v -> {
            String email = binding.userEdt.getText().toString().trim();
            String password = binding.passEdt.getText().toString().trim();
            String name = binding.nameEdt.getText().toString().trim(); // Lấy tên người dùng từ EditText

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(name)) { // Kiểm tra xem các trường có trống không
                Toast.makeText(LoginActivity.this, "Please enter both email, password and display name", Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();

                                // Lưu tên người dùng và email vào Firebase Realtime Database
                                DatabaseReference usersRef = ref.child("users");
                                String userId = user.getUid();
                                usersRef.child(userId).child("name").setValue(name);
                                usersRef.child(userId).child("email").setValue(email);


                                DatabaseReference adminRef = ref.child("admins").child(userId);
                                adminRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.exists()) {
                                            // Nếu người dùng là admin, chuyển hướng đến AdminSiteActivity
                                            Intent intent = new Intent(LoginActivity.this, AdminProfileActivity.class);
                                            startActivity(intent);
                                            finish();
                                        } else {
                                            // Chuyển sang ProfileActivity và chuyển dữ liệu
                                            Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                                            intent.putExtra("name", name);
                                            intent.putExtra("email", email);
                                            startActivity(intent);
                                            finish();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {
                                        // Xử lý lỗi khi kiểm tra quyền admin
                                        Log.e(TAG, "Error checking admin privilege", databaseError.toException());
                                        Toast.makeText(LoginActivity.this, "Error checking admin privilege", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {
                                // Đăng nhập thất bại, hiển thị thông báo lỗi
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            }
                        }

                    });


            // Di chuyển việc chuyển qua SignUpActivity ra khỏi sự kiện click của loginBtn
            binding.textViewSignup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                    startActivity(intent);
                }
            });
        });
    }
}