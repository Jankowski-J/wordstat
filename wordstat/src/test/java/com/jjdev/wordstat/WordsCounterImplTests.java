package com.jjdev.wordstat;

import com.jjdev.wordstat.WordsCounterImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
}
