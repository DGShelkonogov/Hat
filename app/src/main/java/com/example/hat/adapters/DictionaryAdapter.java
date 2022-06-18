package com.example.hat.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hat.R;
import com.example.hat.interfaces.DictionaryAdapterListener;
import com.example.hat.models.HatDictionary;

import java.util.ArrayList;

public class DictionaryAdapter extends RecyclerView.Adapter<DictionaryAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final ArrayList<HatDictionary> dictionaries;

    private static DictionaryAdapterListener mListener;

    public DictionaryAdapter(Context context, ArrayList<HatDictionary> dictionaries) {
        this.inflater = LayoutInflater.from(context);
        this.dictionaries = dictionaries;
    }

    public void setDictionaryAdapterListener(DictionaryAdapterListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public DictionaryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_dictionary, parent, false);
        return new DictionaryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DictionaryAdapter.ViewHolder holder, int position) {
        HatDictionary dictionary = this.dictionaries.get(position);
        holder.checkBox.setText(dictionary.getName());

    }

    @Override
    public int getItemCount() {
        return dictionaries.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        private CheckBox checkBox;
        ViewHolder(View view){
            super(view);
            checkBox = itemView.findViewById(R.id.checkBox);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    mListener.changeChecked(getAdapterPosition(), isChecked);
                }
            });
        }
    }
}
