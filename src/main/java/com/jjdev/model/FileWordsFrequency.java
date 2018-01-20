package com.jjdev.model;

import java.util.List;

public class FileWordsFrequency {
    String fileName;

    public String getFileName() {
        return fileName;
    }

    public List<WordEntry> getWordEntries() {
        return wordEntries;
    }


    List<WordEntry> wordEntries;

    public FileWordsFrequency(String fileName, List<WordEntry> wordEntries) {
        this.fileName = fileName;
        this.wordEntries = wordEntries;
    }
}
