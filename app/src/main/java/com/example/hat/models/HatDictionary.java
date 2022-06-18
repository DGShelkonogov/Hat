package com.example.hat.models;

import java.util.ArrayList;
import java.util.List;

public class HatDictionary {
    public String name;
    public List<String> words;

    public HatDictionary() {
    }

    public HatDictionary(String name, List<String> words) {
        this.name = name;
        this.words = words;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }
}
