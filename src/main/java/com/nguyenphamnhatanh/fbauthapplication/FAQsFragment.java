package com.nguyenphamnhatanh.fbauthapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nguyenphamnhatanh.fbauthapplication.databinding.FragmentFAQsBinding;
import com.nguyenphamnhatanh.fbauthapplication.databinding.FragmentPolicyBinding;


public class FAQsFragment extends Fragment {


    FragmentFAQsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFAQsBinding.inflate(inflater, container, false); // Inflate binding
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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