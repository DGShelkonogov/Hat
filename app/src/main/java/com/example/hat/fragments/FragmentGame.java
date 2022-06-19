package com.example.hat.fragments;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.hat.ActivityGame;
import com.example.hat.R;
import com.example.hat.SingleClass;
import com.example.hat.interfaces.FragmentGameListener;
import com.example.hat.interfaces.FragmentSettingsListener;
import com.example.hat.models.Group;
import com.example.hat.viewModels.SettingsModel;

import java.util.Stack;


public class FragmentGame extends Fragment {

    TextView txtWord, txtGroup, txtTime;
    Button btnGuess, btnPass;
    long startTime = 0;
    int time;
    private boolean startGame = false;
    private String currentWord = "";
    public static int indexCurrentGroup = -1;

    private static FragmentGameListener mListener;

    public void setFragmentGameListener(FragmentGameListener listener) {
        mListener = listener;
    }

    Handler timerHandler = new Handler();

    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            if (startGame) {
                long currentTime = System.currentTimeMillis();

                if(startTime - currentTime < 0){
                    if(SingleClass.settingsModel.getWords().size() > 0){
                        Group currentGroup = SingleClass.settingsModel.getGroups().get(indexCurrentGroup);
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setCancelable(true);
                        builder.setTitle("Итог для группы " + currentGroup.getName());
                        builder.setMessage("Счет: " + currentGroup.getScore());
                        builder.show();

                        txtWord.setText("Начать");
                        startGame = false;
                        btnGuess.setEnabled(false);
                        btnPass.setEnabled(false);
                        nextGroup();
                    }else{
                        mListener.result();
                    }
                }

                long millis = startTime - currentTime;

                int seconds = (int) (millis / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                txtTime.setText(String.format("%d:%02d", minutes, seconds));
                timerHandler.postDelayed(this, 1000);
            }
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_game, container, false);

        txtWord = v.findViewById(R.id.txtWord);
        txtGroup = v.findViewById(R.id.txtGroup);
        txtTime = v.findViewById(R.id.txtTime);
        btnGuess = v.findViewById(R.id.btnGuess);
        btnPass = v.findViewById(R.id.btnPass);

        btnGuess.setEnabled(false);
        btnPass.setEnabled(false);

        btnGuess.setOnClickListener(view -> {
            if(SingleClass.settingsModel.getWords().size() > 0){
                SingleClass.settingsModel.getGroups().get(indexCurrentGroup).increaseScore();
                nextWord();
            }else{
                mListener.result();
                indexCurrentGroup = -1;
                currentWord = "";
                startGame = false;
            }
        });

        btnPass.setOnClickListener(view -> {
            if(SingleClass.settingsModel.isPass()){
                SingleClass.settingsModel.getWords().add(0, currentWord);
                nextWord();
            }
        });

        txtWord.setText("Начать");
        nextGroup();
        txtWord.setOnClickListener(view -> {
            if(!startGame){
                start();
            }
        });


        time = SingleClass.settingsModel.getTime();
        txtTime.setText(String.valueOf(SingleClass.settingsModel.getTime()));

        return v;
    }


    void nextGroup(){
        if(indexCurrentGroup == SingleClass.settingsModel.getGroups().size() - 1){
            indexCurrentGroup = 0;
        }else{
            indexCurrentGroup++;
        }

        Group currentGroup = SingleClass.settingsModel.getGroups().get(indexCurrentGroup);
        txtGroup.setText(currentGroup.getName());
    }

    void start(){
        startTime = System.currentTimeMillis() + time * 1000;
        timerHandler.postDelayed(timerRunnable, 0);
        startGame = true;

        btnGuess.setEnabled(true);
        btnPass.setEnabled(true);


        nextWord();
    }

    void nextWord(){
        currentWord = SingleClass.settingsModel.getWords().pop();
        txtWord.setText(currentWord);
    }

}