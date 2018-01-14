package com.jjdev.wordstat;

import com.jjdev.model.WordEntry;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class WordsCounterImplTests {

    private WordsCounterImpl sut;

    public WordsCounterImplTests() {
        sut = new WordsCounterImpl();
    }

    @Test
    public void calculateWordsFrequency_forOneWord_shouldReturnThatWord() {
        List<String> words = Arrays.asList("derp");

        Map<String, Integer> map = sut.calculateWordsFrequency(words);

        Assert.assertTrue(map.containsKey("derp"));
    }

    @Test
    public void calculateWordsFrequency_forOneWord_shouldReturnCorrectCount() {
        List<String> words = Arrays.asList("derp");

        Map<String, Integer> map = sut.calculateWordsFrequency(words);

        Integer actual = map.get("derp");
        Assert.assertEquals((Integer)1, actual);
    }

    @Test
    public void calculateWordsFrequency_forThreeDifferentWords_shouldReturnTheseWords() {
        List<String> words = Arrays.asList("derp", "aa", "bb");

        Map<String, Integer> map = sut.calculateWordsFrequency(words);

        Assert.assertTrue(map.containsKey("derp"));
        Assert.assertTrue(map.containsKey("aa"));
        Assert.assertTrue(map.containsKey("bb"));
    }

    @Test
    public void calculateWordsFrequency_forThreeDifferentWords_shouldReturnCorrectCounts() {
        List<String> words = Arrays.asList("derp", "aa", "bb");

        Map<String, Integer> map = sut.calculateWordsFrequency(words);

        Integer first = map.get("derp");
        Integer second = map.get("aa");
        Integer third = map.get("bb");

        Assert.assertEquals((Integer)1, first);
        Assert.assertEquals((Integer)1, second);
        Assert.assertEquals((Integer)1, third);
    }

    @Test
    public void calculateWordsFrequency_forThreeTimesTheSameWord_shouldReturnOneKey() {
        List<String> words = Arrays.asList("uga", "uga", "uga");

        Map<String, Integer> map = sut.calculateWordsFrequency(words);

        Assert.assertEquals(1, map.keySet().size());
    }

    @Test
    public void calculateWordsFrequency_forThreeTimesTheSameWord_shouldReturnThatWord() {
        List<String> words = Arrays.asList("uga", "uga", "uga");

        Map<String, Integer> map = sut.calculateWordsFrequency(words);

        Assert.assertTrue(map.containsKey("uga"));
    }

    @Test
    public void calculateWordsFrequency_forThreeTimesTheSameWord_shouldReturnCorrectCount() {
        List<String> words = Arrays.asList("uga", "uga", "uga");

        Map<String, Integer> map = sut.calculateWordsFrequency(words);

        Integer actual = map.get("uga");
        Assert.assertEquals((Integer)3, actual);
    }

    @Test
    public void getMostCommonWords_forThreeWordsAndTopOne_shouldReturnTheMostFrequent() {
        Map<String, Integer> words = new HashMap<>();
        words.put("slowo", 5);
        words.put("andrzej", 7);
        words.put("ups", 1);

        List<WordEntry> mostCommonWords = sut.getMostCommonWords(words, 1);
        WordEntry entry = mostCommonWords.get(0);

        Assert.assertEquals(7, (int)entry.getCount());
    }

    @Test
    public void getMostCommonWords_forThreeWordsAndTopOne_shouldReturnOnlyOneKey() {
        Map<String, Integer> words = new HashMap<>();
        words.put("slowo", 5);
        words.put("andrzej", 7);
        words.put("ups", 1);

        List<WordEntry> mostCommonWords = sut.getMostCommonWords(words, 1);

        Assert.assertEquals(1, mostCommonWords.size());
    }

    @Test
    public void getMostCommonWords_forThreeWordsAndTopFive_shouldReturnAllThreeWords() {
        Map<String, Integer> words = new HashMap<>();
        words.put("slowo", 5);
        words.put("andrzej", 7);
        words.put("ups", 1);

        List<WordEntry> mostCommonWords = sut.getMostCommonWords(words, 5);

        Assert.assertEquals(3, mostCommonWords.size());
    }

    @Test
    public void getMostCommonWords_forThreeWordsAndTopFive_shouldReturnCorrectKeys() {
        Map<String, Integer> words = new HashMap<>();
        words.put("slowo", 5);
        words.put("andrzej", 7);
        words.put("ups", 1);

        List<WordEntry> mostCommonWords = sut.getMostCommonWords(words, 5);
        Set<String> keys = mostCommonWords.stream()
                .map(x -> x.getWord())
                .collect(Collectors.toSet());

        Assert.assertTrue(keys.contains("slowo"));
        Assert.assertTrue(keys.contains("andrzej"));
        Assert.assertTrue(keys.contains("ups"));
    }
}
