package com.example.hat.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.hat.R;
import com.example.hat.SingleClass;
import com.example.hat.adapters.GroupAdapter;
import com.example.hat.interfaces.AdapterGroupListener;
import com.example.hat.interfaces.FragmentSettingsListener;
import com.example.hat.models.Group;

import java.util.ArrayList;


public class FragmentGroupsSettings extends Fragment implements AdapterGroupListener {

    RecyclerView list_groups;
    Button btnNext;
    SeekBar seekBarGroupsCount;
    TextView txtGroupsCount;

    int OldCountGroups = 2;

    ArrayList<Group> groups = new ArrayList<>();


    private static FragmentSettingsListener mListener;

    public void setOnClickAdapterListener(FragmentSettingsListener listener) {
        mListener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_groups_settings, container, false);

        list_groups = v.findViewById(R.id.list_groups);
        btnNext = v.findViewById(R.id.btnNext);
        seekBarGroupsCount = v.findViewById(R.id.seekBarGroupsCount);
        txtGroupsCount = v.findViewById(R.id.txtGroupsCount);

        groups.add(new Group("Комканда 1", 0));
        groups.add(new Group("Комканда 2", 0));

        GroupAdapter adapter = new GroupAdapter(getContext(), groups);
        adapter.setAdapterGroupListener(this);
        list_groups.setLayoutManager(new LinearLayoutManager(getContext(),
                RecyclerView.VERTICAL, false));
        list_groups.setAdapter(adapter);

        seekBarGroupsCount.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtGroupsCount.setText("Количетво команд: " + progress);

                if(progress > OldCountGroups){
                    groups.add(new Group("Комканда " + progress, 0));
                }else{
                    try{
                        groups.remove(progress);
                    }catch (IndexOutOfBoundsException e){

                    }
                }

                OldCountGroups = progress;
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnNext.setOnClickListener(view -> {
            SingleClass.settingsModel.setGroups(groups);
            mListener.next();
        });


        return v;
    }

    @Override
    public void textChange(int index, String change) {
        groups.remove(index);
        groups.add(index, new Group(change, 0));
    }
}