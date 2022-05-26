package com.example.hat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.hat.fragments.FragmentGame;
import com.example.hat.fragments.FragmentSettings;
import com.example.hat.interfaces.FragmentGameListener;
import com.example.hat.interfaces.FragmentSettingsListener;
import com.example.hat.viewModels.SettingsModel;

public class ActivityGame extends AppCompatActivity implements FragmentSettingsListener, FragmentGameListener {

    private FragmentGame fragmentGame;
    private FragmentSettings fragmentSettings;

    private final String SIMPLE_FRAGMENT_GAME_TAG = "SIMPLE_FRAGMENT_GAME_TAG";
    private final String SIMPLE_FRAGMENT_SETTINGS_TAG = "SIMPLE_FRAGMENT_SETTINGS_TAG";
    private String current_fragment = SIMPLE_FRAGMENT_SETTINGS_TAG;
    public SettingsModel settingsModel;

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
            fragmentSettings = (FragmentSettings)
                    getSupportFragmentManager().findFragmentByTag(SIMPLE_FRAGMENT_SETTINGS_TAG);

            current_fragment = savedInstanceState.getString("current_fragment");

        }
        // only create fragment if they haven't been instantiated already
        if (fragmentGame == null)
            fragmentGame = new FragmentGame();
        if(fragmentSettings == null)
            fragmentSettings = new FragmentSettings();

        fragmentSettings.setOnClickAdapterListener(this);

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(current_fragment.equals(SIMPLE_FRAGMENT_GAME_TAG)){
            fragmentTransaction.replace(R.id.fragment, fragmentGame, SIMPLE_FRAGMENT_GAME_TAG);
        }else if(current_fragment.equals(SIMPLE_FRAGMENT_SETTINGS_TAG)){
            fragmentTransaction.replace(R.id.fragment, fragmentSettings, SIMPLE_FRAGMENT_SETTINGS_TAG);
        }
        fragmentTransaction.commit();

    }

    @Override
    public void start(SettingsModel model) {
        settingsModel = model;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, fragmentGame, SIMPLE_FRAGMENT_GAME_TAG);
        fragmentTransaction.commit();
        current_fragment = SIMPLE_FRAGMENT_GAME_TAG;
    }

    @Override
    public void pass() {

    }

    @Override
    public void guess() {

    }
}