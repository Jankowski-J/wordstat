package com.jjdev.wordstat;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WordsCounterImpl implements WordsCounter {

    @Override
    public Map<String, Integer> calculateWordsFrequency(List<String> words) {

        Map<String, Integer> benito = words.stream()
                .collect(Collectors.groupingBy(x -> x))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().size()));

        return benito;
    }
}
