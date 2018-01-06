package com.jjdev.wordstat;

import java.util.List;
import java.util.Map;

public interface WordsCounter {
    Map<String, Integer> calculateWordsFrequency(List<String> words);
}
