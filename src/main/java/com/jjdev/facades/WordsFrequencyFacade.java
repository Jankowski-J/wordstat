package com.jjdev.facades;

import com.jjdev.model.FileNameWithData;
import com.jjdev.model.FileWordsFrequency;
import com.jjdev.model.WordEntry;
import com.jjdev.model.WordstatParams;
import com.jjdev.network.FileDownloader;
import com.jjdev.parsers.FileParser;
import com.jjdev.parsers.WordsCounter;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WordsFrequencyFacade {

    private FileDownloader fileDownloader;
    private FileParser fileParser;
    private WordsCounter wordsCounter;

    public WordsFrequencyFacade(FileDownloader downloader, FileParser parser,
                                WordsCounter counter) {
        fileDownloader = downloader;
        fileParser = parser;
        wordsCounter = counter;
    }

    public List<FileWordsFrequency> getWordsFrequencies(WordstatParams params) throws IOException {
        String rawPath = params.getPath();

        boolean isNetworkResource = rawPath.startsWith("http");
        List<String> fileNames = getFileNamesToProcess(rawPath, isNetworkResource);

        List<FileWordsFrequency> result;
        result = fileNames.stream()
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

    private List<String> getFileNamesToProcess(String rawPath, boolean isNetworkResource) throws IOException {
        List<String> fileNames = new ArrayList<>();

        if (isNetworkResource) {
            try {
                String fileName = fileDownloader.downloadFile(rawPath);
                fileNames.add(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Path path = Paths.get(rawPath);
            boolean doesFileExist = Files.exists(path);

            if (!doesFileExist) {
                throw new IOException();
            }
            fileNames.add(rawPath);
        }
        return fileNames;
    }

    private FileWordsFrequency getMostCommonWords(WordstatParams params, FileNameWithData<Map<String, Integer>> x) {
        List<WordEntry> mostCommonWords = wordsCounter.getMostCommonWords(x.getData(), params.getTopWordsCount());
        return new FileWordsFrequency(x.getFileName(), mostCommonWords);
    }

    private FileNameWithData<Map<String, Integer>> calculateWordsFrequencies(FileNameWithData<List<String>> x) {
        Map<String, Integer> wordMap = wordsCounter.calculateWordsFrequency(x.getData());
        return new FileNameWithData<>(x.getFileName(), wordMap);
    }

    private FileNameWithData<List<String>> parseLinesFromFile(FileNameWithData<List<String>> x) {
        List<String> sanitizationResult = fileParser.sanitizeAndGetWords(x.getData());
        return new FileNameWithData<>(x.getFileName(), sanitizationResult);
    }

    private FileNameWithData<List<String>> getLinesFromFile(String x) {
        try {
            List<String> parseResult = fileParser.getLinesFromLocalFile(x);
            return new FileNameWithData<>(x, parseResult);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}

