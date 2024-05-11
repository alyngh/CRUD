package com.nguyenphamnhatanh.fbauthapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyenphamnhatanh.fbauthapplication.Domain.User;
import com.nguyenphamnhatanh.fbauthapplication.databinding.RecyclerItemBinding;

import java.util.ArrayList;
import java.util.List;
public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.MyViewHolder> {

    Context context;
    private List<User> dataList;
    private OnItemClickListener listener; // Moved here

    public CustomerAdapter(Context context, List<User> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerItemBinding binding = RecyclerItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(dataList.get(position));

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void searchDataList(ArrayList<User> searchList) {
        dataList.clear(); // Xóa tất cả các phần tử trong danh sách hiện tại
        dataList.addAll(searchList); // Thêm tất cả các phần tử từ danh sách tìm kiếm vào danh sách hiện tại
        notifyDataSetChanged(); // Thông báo cho RecyclerView biết rằng dữ liệu đã thay đổi
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private RecyclerItemBinding binding;

        public MyViewHolder(@NonNull RecyclerItemBinding itemBinding) {
            super(itemBinding.getRoot());
            binding = itemBinding;

            binding.buttonView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        User user = dataList.get(position);
                        listener.onItemClick(user); // Call the listener
                    }
                }
            });



        }

        public void bind(User user) {
            binding.recName.setText(user.getName());
            binding.recEmail.setText(user.getEmail());
            binding.recAddress.setText(user.getAddress());
            binding.recPhone.setText(user.getPhoneNumber());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(User user);
    }
}