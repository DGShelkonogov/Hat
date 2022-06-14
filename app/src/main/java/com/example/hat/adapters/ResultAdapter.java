package com.example.hat.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hat.R;
import com.example.hat.interfaces.AdapterGroupListener;
import com.example.hat.models.Group;

import java.util.ArrayList;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final ArrayList<Group> groups;

    public ResultAdapter(Context context, ArrayList<Group> groups) {
        this.inflater = LayoutInflater.from(context);
        this.groups = groups;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_group_result, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Group group = this.groups.get(position);
        holder.txtGroupName.setText("Группа: " + group.getName());
        holder.txtGroupScore.setText("Счёт: " + group.getScore());

    }

    @Override
    public int getItemCount() {
        return groups.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtGroupName, txtGroupScore;

        ViewHolder(View view){
            super(view);
            txtGroupName = itemView.findViewById(R.id.txtGroupName);
            txtGroupScore = itemView.findViewById(R.id.txtGroupScore);
        }
    }
}
