package com.jjdev.wordstat.facades;

import com.jjdev.wordstat.model.FileNameWithData;
import com.jjdev.wordstat.model.FileWordsFrequency;
import com.jjdev.wordstat.model.WordEntry;
import com.jjdev.wordstat.model.WordstatParams;
import com.jjdev.wordstat.parsers.FileParser;
import com.jjdev.wordstat.parsers.FilePathParser;
import com.jjdev.wordstat.parsers.WordsCounter;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WordsFrequencyFacade {

    private FileParser fileParser;
    private WordsCounter wordsCounter;
    private FilePathParser filePathParser;

    public WordsFrequencyFacade(FileParser parser,
                                WordsCounter counter, FilePathParser pathParser) {
        fileParser = parser;
        wordsCounter = counter;
        filePathParser = pathParser;
    }

    public List<FileWordsFrequency> getWordsFrequencies(WordstatParams params) throws IOException {
        String rawPath = params.getPath();

        List<String> fileNames = filePathParser.getFileNamesToProcess(rawPath);

        List<FileWordsFrequency> result;
        result = fileNames.parallelStream()
                .map(x -> getLinesFromFile(x))
                .map(x -> parseLinesFromFile(x))
                .map(x -> calculateWordsFrequencies(x))
                .map(x -> getMostCommonWords(params, x))
                .collect(Collectors.toList());

        printFrequencies(result, params.getTopWordsCount());

        return result;
    }

    private void printFrequencies(List<FileWordsFrequency> frequencies, int topWords) {

        for (FileWordsFrequency frequency : frequencies) {
            System.out.println("Top " + topWords + " in file: " + frequency.getFileName());
            for (WordEntry entry : frequency.getWordEntries()) {
                System.out.println(entry.getWord() + ": " + entry.getCount());
            }
            System.out.println();
        }
    }

    private FileWordsFrequency getMostCommonWords(WordstatParams params, FileNameWithData<Map<String, Integer>> fileWordMaps) {
        List<WordEntry> mostCommonWords = wordsCounter.getMostCommonWords(fileWordMaps.getData(), params.getTopWordsCount());

        return new FileWordsFrequency(fileWordMaps.getFileName(), mostCommonWords);
    }

    private FileNameWithData<Map<String, Integer>> calculateWordsFrequencies(FileNameWithData<List<String>> fileWords) {
        Map<String, Integer> wordMap = wordsCounter.calculateWordsFrequency(fileWords.getData());
        return new FileNameWithData<>(fileWords.getFileName(), wordMap);
    }

    private FileNameWithData<List<String>> parseLinesFromFile(FileNameWithData<List<String>> fileLines) {
        List<String> sanitizationResult = fileParser.sanitizeAndGetWords(fileLines.getData());
        return new FileNameWithData<>(fileLines.getFileName(), sanitizationResult);
    }

    private FileNameWithData<List<String>> getLinesFromFile(String fileName) {
        try {
            List<String> parseResult = fileParser.getLinesFromLocalFile(fileName);
            return new FileNameWithData<>(fileName, parseResult);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}

