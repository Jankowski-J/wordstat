package com.jjdev.parsers;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileParserImplTests {

    private FileParserImpl sut;
    private ClassLoader loader;

    public FileParserImplTests() {
        sut = new FileParserImpl();
        loader = getClass().getClassLoader();
    }

    @Test
    public void getLinesFromLocalFile_forFileWithOneLine_shouldReturnThatLine() {
        URL resource = loader.getResource("1.txt");
        String path = resource.getPath().substring(1);

        try {
            List<String> linesFromFile = sut.getLinesFromLocalFile(path);

            Assert.assertEquals(1, linesFromFile.size());
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void getLinesFromLocalFile_forFileWithSevenLines_shouldReturnTheseLines() {
        URL resource = loader.getResource("2.txt");
        String path = resource.getPath().substring(1);

        try {
            List<String> linesFromFile = sut.getLinesFromLocalFile(path);

            Assert.assertEquals(7, linesFromFile.size());
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void sanitizeAndGetWords_forLinesWithSomeNonAlphabeticSymbols_shouldStripThem() {
        String line = "Patrzę w niebo, gwiazd szukam, przewodniczek łodzi;";
        List<String> lines = new ArrayList<>();
        lines.add(line);

        List<String> result = sut.sanitizeAndGetWords(lines);
        boolean anyCommas = result.stream().anyMatch(x -> x.contains(","));
        boolean anySemicolons = result.stream().anyMatch(x -> x.contains(";"));

        Assert.assertFalse(anyCommas);
        Assert.assertFalse(anySemicolons);
    }

    @Test
    public void sanitizeAndGetWords_forLinesWithSomeNonAlphabeticSymbols_shouldReturnCorrectWordsCount() {
        String line = "Patrzę w niebo, gwiazd szukam, przewodniczek łodzi;";
        List<String> lines = new ArrayList<>();
        lines.add(line);

        List<String> result = sut.sanitizeAndGetWords(lines);

        Assert.assertEquals(7, result.size());
    }

    @Test
    public void sanitizeAndGetWords_forLinesWithSomeNonAlphabeticSymbols_shouldReturnCorrectWords() {
        String line = "Patrzę w niebo, gwiazd szukam, przewodniczek łodzi;";
        List<String> lines = new ArrayList<>();
        lines.add(line);

        List<String> expected = Arrays.asList("Patrzę", "w", "niebo", "gwiazd", "szukam",
                "przewodniczek", "łodzi");

        List<String> result = sut.sanitizeAndGetWords(lines);

        for (String word : expected) {
            Assert.assertTrue(result.contains(word));
        }
    }

    @Test
    public void sanitizeAndGetWords_forSingleLineWithManyIgnoredSymbols_shouldTrimAllOfThem() {
        String line = "c,h-r:u;p.k?i!";
        List<String> words = Arrays.asList(line);

        List<String> result = sut.sanitizeAndGetWords(words);
        String parsedWord = result.get(0);

        Assert.assertEquals("chrupki", parsedWord);
    }
}
