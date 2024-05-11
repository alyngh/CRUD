package com.nguyenphamnhatanh.fbauthapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.nguyenphamnhatanh.fbauthapplication.databinding.ActivityFaqsBinding;
import com.nguyenphamnhatanh.fbauthapplication.databinding.ActivityPolicyBinding;

public class FAQsActivity extends BaseActivity {

    ActivityFaqsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFaqsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        binding.btnPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_policy);
            }
        });


        binding.header1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Nếu nội dung đang hiển thị thì ẩn đi, ngược lại thì hiển thị
                if (binding.info1.getVisibility() == View.VISIBLE) {
                    binding.info1.setVisibility(View.GONE);
                } else {
                    binding.info1.setVisibility(View.VISIBLE);
                }
            }
        });

        // Thiết lập sự kiện click cho header 2
        binding.header2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Nếu nội dung đang hiển thị thì ẩn đi, ngược lại thì hiển thị
                if (binding.info2.getVisibility() == View.VISIBLE) {
                    binding.info2.setVisibility(View.GONE);
                } else {
                    binding.info2.setVisibility(View.VISIBLE);
                }
            }
        });



        binding.header3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Nếu nội dung đang hiển thị thì ẩn đi, ngược lại thì hiển thị
                if (binding.info3.getVisibility() == View.VISIBLE) {
                    binding.info3.setVisibility(View.GONE);
                } else {
                    binding.info3.setVisibility(View.VISIBLE);
                }
            }
        });


        binding.header4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Nếu nội dung đang hiển thị thì ẩn đi, ngược lại thì hiển thị
                if (binding.info4.getVisibility() == View.VISIBLE) {
                    binding.info4.setVisibility(View.GONE);
                } else {
                    binding.info4.setVisibility(View.VISIBLE);
                }
            }
        });

        binding.header5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Nếu nội dung đang hiển thị thì ẩn đi, ngược lại thì hiển thị
                if (binding.info5.getVisibility() == View.VISIBLE) {
                    binding.info5.setVisibility(View.GONE);
                } else {
                    binding.info5.setVisibility(View.VISIBLE);
                }
            }
        });

        // Thiết lập sự kiện click cho header 2
        binding.header6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Nếu nội dung đang hiển thị thì ẩn đi, ngược lại thì hiển thị
                if (binding.info6.getVisibility() == View.VISIBLE) {
                    binding.info6.setVisibility(View.GONE);
                } else {
                    binding.info6.setVisibility(View.VISIBLE);
                }
            }
        });



        binding.header7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Nếu nội dung đang hiển thị thì ẩn đi, ngược lại thì hiển thị
                if (binding.info7.getVisibility() == View.VISIBLE) {
                    binding.info7.setVisibility(View.GONE);
                } else {
                    binding.info7.setVisibility(View.VISIBLE);
                }
            }
        });


        binding.header8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Nếu nội dung đang hiển thị thì ẩn đi, ngược lại thì hiển thị
                if (binding.info8.getVisibility() == View.VISIBLE) {
                    binding.info8.setVisibility(View.GONE);
                } else {
                    binding.info8.setVisibility(View.VISIBLE);
                }
            }
        });

        binding.header9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Nếu nội dung đang hiển thị thì ẩn đi, ngược lại thì hiển thị
                if (binding.info9.getVisibility() == View.VISIBLE) {
                    binding.info9.setVisibility(View.GONE);
                } else {
                    binding.info9.setVisibility(View.VISIBLE);
                }
            }
        });


        binding.header10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Nếu nội dung đang hiển thị thì ẩn đi, ngược lại thì hiển thị
                if (binding.info10.getVisibility() == View.VISIBLE) {
                    binding.info10.setVisibility(View.GONE);
                } else {
                    binding.info10.setVisibility(View.VISIBLE);
                }
            }
        });



    }
}