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
import com.example.hat.interfaces.FragmentSettingsListener;
import com.example.hat.viewModels.SettingsModel;


public class FragmentSettings extends Fragment {

    SeekBar seekBarGroupsCount, seekBarWordsCount, seekBarTime;
    TextView txtGroupsCount, txtWordsCount, txtTime;
    ToggleButton btnRepeat;
    Button btnPlay;

    private static FragmentSettingsListener mListener;

    // метод-сеттер для привязки колбэка к получателю событий
    public void setOnClickAdapterListener(FragmentSettingsListener listener) {
        mListener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_settings, container, false);

        seekBarGroupsCount = v.findViewById(R.id.seekBarGroupsCount);
        seekBarWordsCount = v.findViewById(R.id.seekBarWordsCount);
        seekBarTime = v.findViewById(R.id.seekBarTime);
        txtGroupsCount = v.findViewById(R.id.txtGroupsCount);
        txtWordsCount = v.findViewById(R.id.txtWordsCount);
        txtTime = v.findViewById(R.id.txtTime);
        btnRepeat = v.findViewById(R.id.btnRepeat);
        btnPlay = v.findViewById(R.id.btnPlay);




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


        seekBarGroupsCount.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtGroupsCount.setText("Количетво команд: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        seekBarWordsCount.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtWordsCount.setText("Количество слов: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        btnPlay.setOnClickListener(view -> {
            if(mListener != null){
                SettingsModel model = new SettingsModel();
                model.setCountGroups(seekBarGroupsCount.getProgress());
                model.setCountWords(seekBarWordsCount.getProgress());
                model.setTime(seekBarTime.getProgress());
                model.setRepeat(btnRepeat.isChecked());
                mListener.start(model);
            }
        });
        return v;
    }
}