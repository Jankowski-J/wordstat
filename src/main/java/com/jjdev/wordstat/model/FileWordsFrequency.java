package com.jjdev.wordstat.model;

import java.util.List;

public class FileWordsFrequency {
    String fileName;
    List<WordEntry> wordEntries;

    public FileWordsFrequency(String fileName, List<WordEntry> wordEntries) {
        this.fileName = fileName;
        this.wordEntries = wordEntries;
    }

    public String getFileName() {
        return fileName;
    }

    public List<WordEntry> getWordEntries() {
        return wordEntries;
    }
}
