package com.example.hat.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.hat.R;
import com.example.hat.SingleClass;
import com.example.hat.adapters.ResultAdapter;
import com.example.hat.models.Group;


public class FragmentResult extends Fragment {

    TextView txtWinGroup;
    RecyclerView list_result;
    Button btnBackMenu;

    private Group winGroup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.fragment_result, container, false);

        txtWinGroup = v.findViewById(R.id.txtWinGroup);


        list_result = v.findViewById(R.id.list_result);
        btnBackMenu = v.findViewById(R.id.btnBackMenu);

        ResultAdapter adapter = new ResultAdapter(getContext(), SingleClass.settingsModel.getGroups());
        list_result.setLayoutManager(new LinearLayoutManager(getContext(),
                RecyclerView.VERTICAL, false));
        list_result.setAdapter(adapter);

        winGroup = SingleClass.settingsModel.getGroups().get(0);

        for (Group group : SingleClass.settingsModel.getGroups()) {
            if(winGroup.getScore() < group.getScore()){
                winGroup = group;
            }
        }
        txtWinGroup.setText("Победила группа: " + winGroup.getName());


        btnBackMenu.setOnClickListener(view -> {
            getActivity().onBackPressed();
        });

       return v;
    }
}