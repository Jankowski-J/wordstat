package com.jjdev.wordstat;

import com.jjdev.model.WordEntry;
import com.jjdev.parsers.FileParser;
import com.jjdev.parsers.FileParserImpl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Jakub Jankowski
 */
public class Main {
    public static void main(String args[]) {

        if(args.length < 2) {
            System.out.println("Two arguments are needed: [N] (number) - indicates how many top words to display; [path] (string) - target file path");
        }

        String topArg = args[0];
        int n = 0;
        try {
            n = Integer.parseInt(topArg);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number specified: " + topArg);
        }

        String fileName = args[1];

        FileParser fileParser = new FileParserImpl();
        List<String> lines = null;
        try {
            lines = fileParser.getLinesFromLocalFile(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> sanitized = fileParser.sanitizeAndGetWords(lines);

        WordsCounterImpl wordsCounter = new WordsCounterImpl();

        Map<String, Integer> wordsMap = wordsCounter.calculateWordsFrequency(sanitized);

        List<WordEntry> mostCommonWords = wordsCounter.getMostCommonWords(wordsMap, n);

        for(WordEntry entry: mostCommonWords) {
            System.out.println(entry.getWord() + ": " + entry.getCount().toString());
        }
    }
}
