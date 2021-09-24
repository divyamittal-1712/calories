package com.exam.calorie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.exam.calorie.model.DatabaseManagerModel;
import com.exam.calorie.R;

import java.util.ArrayList;

public class ShowAllUsersAdapter extends RecyclerView.Adapter<ShowAllUsersAdapter.ViewHolder> {
    ArrayList<DatabaseManagerModel> managerModels;
    Context context;

    public ShowAllUsersAdapter(ArrayList<DatabaseManagerModel> managerModels, Context context) {
        this.managerModels = managerModels;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.show_user_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DatabaseManagerModel managerModel = managerModels.get(position);
        holder.name.setText(managerModel.getName());
        holder.email.setText(managerModel.getMail());
        holder.sex.setText(managerModel.getSex());
        holder.age.setText("Age: "+managerModel.getAge());
        holder.height.setText("Height: "+managerModel.getHeight());
        holder.weight.setText("Weight: "+managerModel.getWeight());
    }

    @Override
    public int getItemCount() {
        return managerModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,email,sex,age,height,weight;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
            sex = itemView.findViewById(R.id.sex);
            age = itemView.findViewById(R.id.age);
            height = itemView.findViewById(R.id.height);
            weight = itemView.findViewById(R.id.weight);
        }
    }
}
