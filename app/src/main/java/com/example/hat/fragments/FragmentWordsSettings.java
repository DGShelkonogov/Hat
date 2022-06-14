package com.example.hat.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.hat.R;
import com.example.hat.SingleClass;
import com.example.hat.interfaces.FragmentSettingsListener;

import java.util.ArrayList;
import java.util.Stack;

public class FragmentWordsSettings extends Fragment {

    SeekBar seekBarWordsCount;
    TextView txtWordsCountSeekBar, txtCountWords;
    Button btnAdd, btnNext;
    EditText edtWord;

    Stack<String> words = new Stack<>();

    private static FragmentSettingsListener mListener;

    public void setOnClickAdapterListener(FragmentSettingsListener listener) {
        mListener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_words_settings, container, false);

        seekBarWordsCount = v.findViewById(R.id.seekBarWordsCount);
        txtWordsCountSeekBar = v.findViewById(R.id.txtWordsCountSeekBar);
        txtCountWords = v.findViewById(R.id.txtCountWords);
        btnAdd = v.findViewById(R.id.btnAdd);
        edtWord = v.findViewById(R.id.edtWord);
        btnNext = v.findViewById(R.id.btnNext);

        btnAdd.setOnClickListener(view -> {
            words.push(edtWord.getText().toString());
            txtCountWords.setText("Количество слов в шляпе: " + (words.size() + seekBarWordsCount.getProgress()));
        });

        seekBarWordsCount.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtCountWords.setText("Количество слов в шляпе: " + (words.size() + progress));
                txtWordsCountSeekBar.setText("Количество слов: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnNext.setOnClickListener(view ->{
            SingleClass.settingsModel.setWords(words);
            mListener.next();
        });

        return v;
    }
}