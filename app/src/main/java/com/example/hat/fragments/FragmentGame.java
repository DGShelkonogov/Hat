package com.example.hat.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.hat.ActivityGame;
import com.example.hat.R;
import com.example.hat.interfaces.FragmentGameListener;
import com.example.hat.interfaces.FragmentSettingsListener;


public class FragmentGame extends Fragment {

    TextView txtMain, txtGroup, txtTime;
    Button btnGuess, btnPass;

    private static FragmentGameListener mListener;

    // метод-сеттер для привязки колбэка к получателю событий
    public void setOnClickAdapterListener(FragmentGameListener listener) {
        mListener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_game, container, false);

        txtMain = v.findViewById(R.id.txtMain);
        txtGroup = v.findViewById(R.id.txtGroup);
        txtTime = v.findViewById(R.id.txtTime);
        btnGuess = v.findViewById(R.id.btnGuess);
        btnPass = v.findViewById(R.id.btnPass);

        btnGuess.setOnClickListener(view -> {
            if(mListener != null)
                mListener.guess();
        });

        btnPass.setOnClickListener(view -> {
            if(mListener != null)
                mListener.pass();
        });

        ActivityGame activity = (ActivityGame) getActivity();
        if(activity.settingsModel != null)
            txtTime.setText(String.valueOf(activity.settingsModel.getTime()));
        return v;
    }
}