package com.example.hat.viewModels;

import com.example.hat.models.Group;

import java.util.ArrayList;
import java.util.Stack;

public class SettingsModel {
    private int countGroups;
    private int countWords;
    private int time;
    private boolean pass;
    private boolean rowing;
    private ArrayList<Group> groups;
    private ArrayList<String> buffer_word;
    private Stack<String> words;


    public SettingsModel() {
    }


    public ArrayList<String> getBuffer_word() {
        return buffer_word;
    }

    public void setBuffer_word(ArrayList<String> buffer_word) {
        this.buffer_word = buffer_word;
    }

    public Stack<String> getWords() {
        return words;
    }

    public void setWords(Stack<String> words) {
        this.words = words;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
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

    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }

    public boolean isRowing() {
        return rowing;
    }

    public void setRowing(boolean rowing) {
        this.rowing = rowing;
    }
}
