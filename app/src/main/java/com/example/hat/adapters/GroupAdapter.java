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

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final ArrayList<Group> groups;

    private static AdapterGroupListener mListener;
    public void setAdapterGroupListener(AdapterGroupListener listener) {
        mListener = listener;
    }

    public GroupAdapter(Context context, ArrayList<Group> groups) {
        this.inflater = LayoutInflater.from(context);
        this.groups = groups;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_group_setting, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Group group = this.groups.get(position);
        holder.edtGroupName.setText(group.getName());

    }

    @Override
    public int getItemCount() {
        return groups.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        private EditText edtGroupName;
        ViewHolder(View view){
            super(view);
            edtGroupName = itemView.findViewById(R.id.edtGroupName);

            edtGroupName.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    mListener.textChange(getAdapterPosition(), s.toString());
                }
            });

        }
    }
}
