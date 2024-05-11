package com.nguyenphamnhatanh.fbauthapplication;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.nguyenphamnhatanh.fbauthapplication.databinding.ActivityPolicyBinding;

public class PolicyActivity extends AppCompatActivity {


    private ActivityPolicyBinding binding;
    FragmentManager fragmentManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPolicyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        fragmentManager = getSupportFragmentManager();

        PolicyFragment policyFragment = new PolicyFragment();
        fragmentManager.beginTransaction().add(R.id.frContent, policyFragment).commit();

        int defaultButtonColor = getResources().getColor(R.color.light_green);
        int defaultTextColor = getResources().getColor(R.color.moss_green);

        binding.btnPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PolicyFragment policyFragment = new PolicyFragment();
                fragmentManager.beginTransaction().replace(R.id.frContent,policyFragment).commit();

                binding.btnPolicy.setBackgroundColor(getResources().getColor(R.color.moss_green));
                binding.btnPolicy.setTextColor(getResources().getColor(R.color.light_green));

                binding.btnFAQs.setBackgroundColor(defaultButtonColor);
                binding.btnFAQs.setTextColor(defaultTextColor);

                    }
            });


        binding.btnFAQs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FAQsFragment faqsFragment = new FAQsFragment();
                fragmentManager.beginTransaction().replace(R.id.frContent,faqsFragment).commit();

                binding.btnFAQs.setBackgroundColor(getResources().getColor(R.color.moss_green));
                binding.btnFAQs.setTextColor(getResources().getColor(R.color.light_green));

                binding.btnPolicy.setBackgroundColor(defaultButtonColor);
                binding.btnPolicy.setTextColor(defaultTextColor);

                }
        });

    }
}