package com.example.hat.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.hat.R;
import com.example.hat.SingleClass;
import com.example.hat.interfaces.FragmentSettingsListener;


public class FragmentTimeSettings extends Fragment {

    SeekBar seekBarTime;
    TextView txtTime;
    ToggleButton btnPass, btnRowing;
    Button btnNext;

    private static FragmentSettingsListener mListener;

    public void setOnClickAdapterListener(FragmentSettingsListener listener) {
        mListener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_time_settings, container, false);

        seekBarTime = v.findViewById(R.id.seekBarTime);
        txtTime = v.findViewById(R.id.txtTime);
        btnPass = v.findViewById(R.id.btnPass);
        btnRowing = v.findViewById(R.id.btnRowing);
        btnNext = v.findViewById(R.id.btnNext);

        seekBarTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtTime.setText("Время на раунд (с): " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnNext.setOnClickListener(view ->
        {
            SingleClass.settingsModel.setPass(btnPass.isChecked());
            SingleClass.settingsModel.setRowing(btnRowing.isChecked());
            SingleClass.settingsModel.setTime(seekBarTime.getProgress());
            mListener.next();
        });

        return v;
    }
}