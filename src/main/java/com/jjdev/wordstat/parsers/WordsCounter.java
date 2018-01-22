package com.jjdev.wordstat.parsers;

import com.jjdev.wordstat.model.WordEntry;

import java.util.List;
import java.util.Map;

public interface WordsCounter {
    Map<String, Integer> calculateWordsFrequency(List<String> words);

    List<WordEntry> getMostCommonWords(Map<String, Integer> baseMap, int n);
}
