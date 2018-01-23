package com.jjdev.wordstat.parsers;

import com.jjdev.wordstat.model.WordEntry;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WordsCounterImplTests {

    private WordsCounterImpl sut;

    @Before
    public void setup() {
        sut = new WordsCounterImpl();
    }

    @Test
    public void calculateWordsFrequency_forOneWord_shouldReturnThatWord() {
        List<String> words = Arrays.asList("derp");

        Map<String, Integer> map = sut.calculateWordsFrequency(words);

        assertTrue(map.containsKey("derp"));
    }

    @Test
    public void calculateWordsFrequency_forOneWord_shouldReturnCorrectCount() {
        List<String> words = Arrays.asList("derp");

        Map<String, Integer> map = sut.calculateWordsFrequency(words);

        Integer actual = map.get("derp");
        assertEquals((Integer) 1, actual);
    }

    @Test
    public void calculateWordsFrequency_forThreeDifferentWords_shouldReturnTheseWords() {
        List<String> words = Arrays.asList("derp", "aa", "bb");

        Map<String, Integer> map = sut.calculateWordsFrequency(words);

        assertTrue(map.containsKey("derp"));
        assertTrue(map.containsKey("aa"));
        assertTrue(map.containsKey("bb"));
    }

    @Test
    public void calculateWordsFrequency_forThreeDifferentWords_shouldReturnCorrectCounts() {
        List<String> words = Arrays.asList("derp", "aa", "bb");

        Map<String, Integer> map = sut.calculateWordsFrequency(words);

        Integer first = map.get("derp");
        Integer second = map.get("aa");
        Integer third = map.get("bb");

        assertEquals((Integer) 1, first);
        assertEquals((Integer) 1, second);
        assertEquals((Integer) 1, third);
    }

    @Test
    public void calculateWordsFrequency_forThreeTimesTheSameWord_shouldReturnOneKey() {
        List<String> words = Arrays.asList("uga", "uga", "uga");

        Map<String, Integer> map = sut.calculateWordsFrequency(words);

        assertEquals(1, map.keySet().size());
    }

    @Test
    public void calculateWordsFrequency_forThreeTimesTheSameWord_shouldReturnThatWord() {
        List<String> words = Arrays.asList("uga", "uga", "uga");

        Map<String, Integer> map = sut.calculateWordsFrequency(words);

        assertTrue(map.containsKey("uga"));
    }

    @Test
    public void calculateWordsFrequency_forThreeTimesTheSameWord_shouldReturnCorrectCount() {
        List<String> words = Arrays.asList("uga", "uga", "uga");

        Map<String, Integer> map = sut.calculateWordsFrequency(words);

        Integer actual = map.get("uga");
        assertEquals((Integer) 3, actual);
    }

    @Test
    public void getMostCommonWords_forThreeWordsAndTopOne_shouldReturnTheMostFrequent() {
        Map<String, Integer> words = new HashMap<>();
        words.put("slowo", 5);
        words.put("andrzej", 7);
        words.put("ups", 1);

        List<WordEntry> mostCommonWords = sut.getMostCommonWords(words, 1);
        WordEntry entry = mostCommonWords.get(0);

        assertEquals(7, (int) entry.getCount());
    }

    @Test
    public void getMostCommonWords_forThreeWordsAndTopOne_shouldReturnOnlyOneKey() {
        Map<String, Integer> words = new HashMap<>();
        words.put("slowo", 5);
        words.put("andrzej", 7);
        words.put("ups", 1);

        List<WordEntry> mostCommonWords = sut.getMostCommonWords(words, 1);

        assertEquals(1, mostCommonWords.size());
    }

    @Test
    public void getMostCommonWords_forThreeWordsAndTopFive_shouldReturnAllThreeWords() {
        Map<String, Integer> words = new HashMap<>();
        words.put("slowo", 5);
        words.put("andrzej", 7);
        words.put("ups", 1);

        List<WordEntry> mostCommonWords = sut.getMostCommonWords(words, 5);

        assertEquals(3, mostCommonWords.size());
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

        assertTrue(keys.contains("slowo"));
        assertTrue(keys.contains("andrzej"));
        assertTrue(keys.contains("ups"));
    }

    @Test
    public void getMostCommonWords_forThreeWordsAndTopFive_shouldSortAlphabeticallySameCounts() {
        Map<String, Integer> words = new HashMap<>();
        words.put("się", 3);
        words.put("nie", 2);
        words.put("na", 2);
        words.put("błyszczy", 2);
        words.put("w", 2);

        List<WordEntry> mostCommonWords = sut.getMostCommonWords(words, 5);

        WordEntry first = mostCommonWords.get(0);
        WordEntry second = mostCommonWords.get(1);
        WordEntry third = mostCommonWords.get(2);
        WordEntry fourth = mostCommonWords.get(3);
        WordEntry fifth = mostCommonWords.get(4);

        assertEquals("się", first.getWord());
        assertEquals("błyszczy", second.getWord());
        assertEquals("na", third.getWord());
        assertEquals("nie", fourth.getWord());
        assertEquals("w", fifth.getWord());
    }
}
