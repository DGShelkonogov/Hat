package com.example.hat.viewModels;

public class SettingsModel {
    private int countGroups;
    private int countWords;
    private int time;
    private boolean repeat;

    public SettingsModel() {
    }

    public SettingsModel(int countGroups, int countWords, int time, boolean repeat) {
        this.countGroups = countGroups;
        this.countWords = countWords;
        this.time = time;
        this.repeat = repeat;
    }

    public int getCountGroups() {
        return countGroups;
    }

    public void setCountGroups(int countGroups) {
        this.countGroups = countGroups;
    }

    public int getCountWords() {
        return countWords;
    }

    public void setCountWords(int countWords) {
        this.countWords = countWords;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public boolean isRepeat() {
        return repeat;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }
}
