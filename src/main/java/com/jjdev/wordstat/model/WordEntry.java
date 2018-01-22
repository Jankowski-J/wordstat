package com.jjdev.wordstat.model;

public class WordEntry {
    private String word;
    private Integer count;

    public WordEntry(String word, Integer count) {
        this.word = word;
        this.count = count;
    }

    public String getWord() {
        return word;
    }

    public Integer getCount() {
        return count;
    }
}
