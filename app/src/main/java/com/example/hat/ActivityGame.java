package com.example.hat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.TextView;

import com.example.hat.fragments.FragmentDictionary;
import com.example.hat.fragments.FragmentGame;
import com.example.hat.fragments.FragmentGroupsSettings;
import com.example.hat.fragments.FragmentResult;
import com.example.hat.fragments.FragmentTimeSettings;
import com.example.hat.fragments.FragmentWordsSettings;
import com.example.hat.interfaces.FragmentDictionaryListener;
import com.example.hat.interfaces.FragmentGameListener;
import com.example.hat.interfaces.FragmentSettingsListener;
import com.example.hat.models.Group;

public class ActivityGame extends AppCompatActivity implements FragmentSettingsListener, FragmentGameListener, FragmentDictionaryListener {

    private FragmentGame fragmentGame;
    private FragmentGroupsSettings fragmentGroupsSettings;
    private FragmentWordsSettings fragmentWordsSettings;
    private FragmentTimeSettings fragmentTimeSettings;
    private FragmentDictionary fragmentDictionary;
    private FragmentResult fragmentResult;

    private final String SIMPLE_FRAGMENT_GAME_TAG = "SIMPLE_FRAGMENT_GAME_TAG";
    private final String SIMPLE_FRAGMENT_GROUP_SETTINGS_TAG = "SIMPLE_FRAGMENT_GROUP_SETTINGS_TAG";
    private final String SIMPLE_FRAGMENT_WORDS_SETTINGS_TAG = "SIMPLE_FRAGMENT_WORDS_SETTINGS_TAG";
    private final String SIMPLE_FRAGMENT_TIME_SETTINGS_TAG = "SIMPLE_FRAGMENT_TIME_SETTINGS_TAG";
    private final String SIMPLE_FRAGMENT_RESULT_TAG = "SIMPLE_FRAGMENT_RESULT_TAG";
    private final String SIMPLE_FRAGMENT_DICTIONARY_TAG = "SIMPLE_FRAGMENT_DICTIONARY_TAG";
    private String current_fragment = SIMPLE_FRAGMENT_GROUP_SETTINGS_TAG;

    private int currentSettingStep = 0;


    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("current_fragment", current_fragment);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        if (savedInstanceState != null) { // saved instance state, fragment may exist
            // look up the instance that already exists by tag
            fragmentGame = (FragmentGame)
                    getSupportFragmentManager().findFragmentByTag(SIMPLE_FRAGMENT_GAME_TAG);
            fragmentGroupsSettings = (FragmentGroupsSettings)
                    getSupportFragmentManager().findFragmentByTag(SIMPLE_FRAGMENT_GROUP_SETTINGS_TAG);
            fragmentWordsSettings = (FragmentWordsSettings)
                    getSupportFragmentManager().findFragmentByTag(SIMPLE_FRAGMENT_WORDS_SETTINGS_TAG);
            fragmentTimeSettings = (FragmentTimeSettings)
                    getSupportFragmentManager().findFragmentByTag(SIMPLE_FRAGMENT_TIME_SETTINGS_TAG);
            fragmentResult = (FragmentResult)
                    getSupportFragmentManager().findFragmentByTag(SIMPLE_FRAGMENT_RESULT_TAG);
            fragmentDictionary = (FragmentDictionary)
                    getSupportFragmentManager().findFragmentByTag(SIMPLE_FRAGMENT_DICTIONARY_TAG);

            current_fragment = savedInstanceState.getString("current_fragment");

        }


        if (fragmentGame == null)
            fragmentGame = new FragmentGame();
        if(fragmentGroupsSettings == null)
            fragmentGroupsSettings = new FragmentGroupsSettings();
        if(fragmentWordsSettings == null)
            fragmentWordsSettings = new FragmentWordsSettings();
        if(fragmentTimeSettings == null)
            fragmentTimeSettings = new FragmentTimeSettings();
        if(fragmentDictionary == null)
            fragmentDictionary = new FragmentDictionary();


        fragmentGame.setFragmentGameListener(this);
        fragmentGroupsSettings.setOnClickAdapterListener(this);
        fragmentWordsSettings.setOnClickAdapterListener(this);
        fragmentTimeSettings.setOnClickAdapterListener(this);
        fragmentDictionary.setFragmentDictionaryListener(this);

        transaction();
    }

    void transaction(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(current_fragment.equals(SIMPLE_FRAGMENT_GAME_TAG)){
            fragmentTransaction.replace(R.id.fragment, fragmentGame, SIMPLE_FRAGMENT_GAME_TAG);
        } else if(current_fragment.equals(SIMPLE_FRAGMENT_GROUP_SETTINGS_TAG)){
            fragmentTransaction.replace(R.id.fragment, fragmentGroupsSettings, SIMPLE_FRAGMENT_GROUP_SETTINGS_TAG);
        } else if(current_fragment.equals(SIMPLE_FRAGMENT_WORDS_SETTINGS_TAG)){
            fragmentTransaction.replace(R.id.fragment, fragmentWordsSettings, SIMPLE_FRAGMENT_WORDS_SETTINGS_TAG);
        } else if(current_fragment.equals(SIMPLE_FRAGMENT_TIME_SETTINGS_TAG)){
            fragmentTransaction.replace(R.id.fragment, fragmentTimeSettings, SIMPLE_FRAGMENT_TIME_SETTINGS_TAG);
        } else if(current_fragment.equals(SIMPLE_FRAGMENT_RESULT_TAG)){
            fragmentTransaction.replace(R.id.fragment, fragmentResult, SIMPLE_FRAGMENT_RESULT_TAG);
        }
        fragmentTransaction.commit();
    }

    @Override
    public void start() {
        current_fragment = SIMPLE_FRAGMENT_GAME_TAG;
        transaction();
    }


    @Override
    public void next() {
        switch (currentSettingStep){
            case 0:{
                current_fragment = SIMPLE_FRAGMENT_WORDS_SETTINGS_TAG;
                break;
            }
            case 1:{
                current_fragment = SIMPLE_FRAGMENT_TIME_SETTINGS_TAG;
                break;
            }
            case 2:{
                current_fragment = SIMPLE_FRAGMENT_GAME_TAG;
                break;
            }
        }
        currentSettingStep++;
        transaction();
    }

    @Override
    public void openDictionaryFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, fragmentDictionary, SIMPLE_FRAGMENT_DICTIONARY_TAG);
        fragmentTransaction.commit();
    }

    @Override
    public void result() {
        fragmentResult = new FragmentResult();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, fragmentResult, SIMPLE_FRAGMENT_RESULT_TAG);
        fragmentTransaction.commit();

        currentSettingStep = 0;
        SingleClass.settingsModel.setBuffer_word(null);
        SingleClass.settingsModel.setTime(0);
        SingleClass.settingsModel.setCountWords(0);
        SingleClass.settingsModel.setCountGroups(0);
    }


    @Override
    public void onBack() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, fragmentWordsSettings, SIMPLE_FRAGMENT_WORDS_SETTINGS_TAG);
        fragmentTransaction.commit();
    }
}