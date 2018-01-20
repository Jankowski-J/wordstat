package com.jjdev.parsers;

import com.jjdev.comparators.WordEntryComparator;
import com.jjdev.model.WordEntry;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WordsCounterImpl implements WordsCounter {

    @Override
    public Map<String, Integer> calculateWordsFrequency(List<String> words) {

        Map<String, Integer> wordsMap = words.stream()
                .collect(Collectors.groupingBy(x -> x))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().size()));

        return wordsMap;
    }

    @Override
    public List<WordEntry> getMostCommonWords(Map<String, Integer> baseMap, int n) {

        List<WordEntry> collected = baseMap.entrySet().stream()
                .map(x -> new WordEntry(x.getKey(), x.getValue()))
                .sorted(new WordEntryComparator())
                .limit(n)
                .collect(Collectors.toList());

        return collected;
    }
}

