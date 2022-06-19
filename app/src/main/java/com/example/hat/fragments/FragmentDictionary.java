package com.example.hat.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.hat.R;
import com.example.hat.SingleClass;
import com.example.hat.adapters.DictionaryAdapter;
import com.example.hat.adapters.ResultAdapter;
import com.example.hat.database.DBHelper;
import com.example.hat.interfaces.DictionaryAdapterListener;
import com.example.hat.interfaces.FragmentDictionaryListener;
import com.example.hat.interfaces.FragmentGameListener;
import com.example.hat.models.HatDictionary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FragmentDictionary extends Fragment implements DictionaryAdapterListener {

    RecyclerView list_dictionary;
    DictionaryAdapter adapter;
    DBHelper helper;

    Button btnBack;
    boolean[] checkBoxes;

    ArrayList<HatDictionary> dictionaries;

    private static FragmentDictionaryListener mListener;

    public void setFragmentDictionaryListener(FragmentDictionaryListener listener) {
        mListener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dictionary, container, false);

        list_dictionary = v.findViewById(R.id.list_dictionary);
        btnBack = v.findViewById(R.id.btnBack);

        helper = new DBHelper(getContext());

       /* List<String> dic_1 = Arrays.asList("сабака", "кошка", "бурундук", "слово", "сабака", "кошка", "бурундук", "слово", "сабака", "кошка", "бурундук", "слово", "сабака", "кошка", "бурундук", "слово");
        List<String> dic_2 = Arrays.asList("сабака", "кошка", "бурундук", "слово", "сабака", "кошка", "бурундук", "слово", "сабака", "кошка", "бурундук", "слово", "сабака", "кошка", "бурундук", "слово");
        List<String> dic_3 = Arrays.asList("сабака", "кошка", "бурундук", "слово", "сабака", "кошка", "бурундук", "слово", "сабака", "кошка", "бурундук", "слово", "сабака", "кошка", "бурундук", "слово");


        helper.insertDictionary(new HatDictionary("Животные", dic_1));
        helper.insertDictionary(new HatDictionary("Еда", dic_2));
        helper.insertDictionary(new HatDictionary("Машины", dic_3));*/

        dictionaries = helper.getDictionary();

        checkBoxes = new boolean[dictionaries.size()];

        for(int i = 0; i < dictionaries.size(); i++){
            checkBoxes[i] = true;
        }

        adapter = new DictionaryAdapter(getContext(), dictionaries);
        adapter.setDictionaryAdapterListener(this);

        list_dictionary.setLayoutManager(new LinearLayoutManager(getContext(),
                RecyclerView.VERTICAL, false));
        list_dictionary.setAdapter(adapter);


        btnBack.setOnClickListener(view -> {

            ArrayList<String> words = new ArrayList<>();
            for(int i = 0; i < dictionaries.size(); i++){
                if(checkBoxes[i]){
                    for(String word : dictionaries.get(i).getWords()){
                        words.add(word);
                    }
                }
            }

            SingleClass.settingsModel.setBuffer_word(words);
            mListener.onBack();
        });


        return v;
    }

    @Override
    public void changeChecked(int position, boolean isChecked) {
        checkBoxes[position] = isChecked;
    }
}