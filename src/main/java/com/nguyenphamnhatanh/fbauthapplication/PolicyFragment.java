package com.nguyenphamnhatanh.fbauthapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.nguyenphamnhatanh.fbauthapplication.databinding.FragmentPolicyBinding;

public class PolicyFragment extends Fragment {

    FragmentPolicyBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentPolicyBinding.inflate(inflater, container, false); // Inflate binding
        return binding.getRoot();
    }


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
    }
}