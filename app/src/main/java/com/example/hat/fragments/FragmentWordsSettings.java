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
import java.util.Random;
import java.util.Stack;

public class FragmentWordsSettings extends Fragment {

    SeekBar seekBarWordsCount;
    TextView txtWordsCountSeekBar, txtCountWords;
    Button btnAdd, btnNext, btnDictionary;
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
        btnDictionary = v.findViewById(R.id.btnDictionary);

        btnAdd.setOnClickListener(view -> {
            words.push(edtWord.getText().toString());
            txtCountWords.setText("Количество слов в шляпе: " + (words.size() + seekBarWordsCount.getProgress()));
        });

        btnDictionary.setOnClickListener(view -> {
            mListener.openDictionaryFragment();
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
            fillWords();
            SingleClass.settingsModel.setWords(words);
            mListener.next();
        });

        return v;
    }

    void fillWords(){
        for(int i = 0; i < 3; i++){
            int index = rnd(0,  SingleClass.settingsModel.getBuffer_word().size());
            String word = SingleClass.settingsModel.getBuffer_word().get(index);
            words.push(word);
            SingleClass.settingsModel.getBuffer_word().remove(index);
        }
    }

    public static int rnd(int min, int max)
    {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }
}