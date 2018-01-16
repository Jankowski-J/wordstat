package com.jjdev.wordstat;

import com.jjdev.model.ParamsParseStatus;
import com.jjdev.model.WordEntry;
import com.jjdev.model.WordstatParams;
import com.jjdev.parsers.FileParser;
import com.jjdev.parsers.FileParserImpl;
import com.jjdev.parsers.WordstatParamsParserImpl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Jakub Jankowski
 */
public class Main {
    public static void main(String args[]) {

        WordstatParamsParserImpl paramsParser = new WordstatParamsParserImpl();

        WordstatParams params = paramsParser.parseParams(args);
        ParamsParseStatus status = params.getStatus();

        if (status != ParamsParseStatus.Success) {
            System.out.println("Two arguments are needed: [N] (number) - indicates how many top words to display; [path] (string) - target file path.");
            System.out.println("The order of arguments is irrelevant");
            return;
        }

        List<String> sanitized = getWordsFromFile(params);

        List<WordEntry> mostCommonWords = getMostCommonWords(params, sanitized);

        for (WordEntry entry : mostCommonWords) {
            System.out.println(entry.getWord() + ": " + entry.getCount().toString());
        }
    }

    private static List<WordEntry> getMostCommonWords(WordstatParams params, List<String> sanitized) {
        WordsCounterImpl wordsCounter = new WordsCounterImpl();

        Map<String, Integer> wordsMap = wordsCounter.calculateWordsFrequency(sanitized);

        return wordsCounter.getMostCommonWords(wordsMap, params.getTopWordsCount());
    }

    private static List<String> getWordsFromFile(WordstatParams params) {
        FileParser fileParser = new FileParserImpl();
        List<String> lines = null;
        try {
            lines = fileParser.getLinesFromLocalFile(params.getPath());
        } catch (IOException e) {
            System.out.println("Specified file does not exist");
        }
        return fileParser.sanitizeAndGetWords(lines);
    }
}
