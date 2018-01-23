package com.jjdev.wordstat.parsers;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class FileParserImplTests {

    private FileParserImpl sut;
    private ClassLoader loader;

    @Before
    public void setup() {
        sut = new FileParserImpl();
        loader = getClass().getClassLoader();
    }

    @Test
    public void getLinesFromLocalFile_forFileWithOneLine_shouldReturnThatLine() {
        URL resource = loader.getResource("1.txt");
        String path = resource.getPath().substring(1);

        try {
            List<String> linesFromFile = sut.getLinesFromLocalFile(path);

            assertEquals(1, linesFromFile.size());
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void getLinesFromLocalFile_forFileWithSevenLines_shouldReturnTheseLines() {
        URL resource = loader.getResource("2.txt");
        String path = resource.getPath().substring(1);

        try {
            List<String> linesFromFile = sut.getLinesFromLocalFile(path);

            assertEquals(7, linesFromFile.size());
        } catch (IOException e) {
            e.printStackTrace();
            fail();
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

        assertFalse(anyCommas);
        assertFalse(anySemicolons);
    }

    @Test
    public void sanitizeAndGetWords_forLinesWithSomeNonAlphabeticSymbols_shouldReturnCorrectWordsCount() {
        String line = "Patrzę w niebo, gwiazd szukam, przewodniczek łodzi;";
        List<String> lines = new ArrayList<>();
        lines.add(line);

        List<String> result = sut.sanitizeAndGetWords(lines);

        assertEquals(7, result.size());
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
            assertTrue(result.contains(word));
        }
    }

    @Test
    public void sanitizeAndGetWords_forSingleLineWithManyIgnoredSymbols_shouldTrimAllOfThem() {
        String line = "c,h-r:u;p.k?i!";
        List<String> words = Arrays.asList(line);

        List<String> result = sut.sanitizeAndGetWords(words);
        String parsedWord = result.get(0);

        assertEquals("chrupki", parsedWord);
    }
    
    @Test
    public void getLinesFromLocalFileAndSanitizeFromALongerFile_shouldReturnOnlyWords() {
        URL resource = loader.getResource("wiersz.txt");
        String path = resource.getPath().substring(1);

        try {
            List<String> linesFromFile = sut.getLinesFromLocalFile(path);
            List<String> wordPairs = sut.sanitizeAndGetWords(linesFromFile);

            boolean allWordsValid = wordPairs.stream()
                    .allMatch(x -> x.length() > 0);
            assertTrue(allWordsValid);
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }
}
