package com.jjdev.facades;

import com.jjdev.model.FileWordsFrequency;
import com.jjdev.model.WordEntry;
import com.jjdev.model.WordstatParams;
import com.jjdev.network.FileDownloader;
import com.jjdev.parsers.FileParser;
import com.jjdev.wordstat.WordsCounter;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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

            if(!doesFileExist) {
                throw new IOException();
            }
            fileNames.add(rawPath);
        }

        List<List<WordEntry>> collect;
        try {
            collect = fileNames.stream()
                    .map(x -> {
                        try {
                            return fileParser.getLinesFromLocalFile(x);
                        } catch (IOException e) {
                            throw new UncheckedIOException(e);
                        }
                    })
                    .map(x -> fileParser.sanitizeAndGetWords(x))
                    .map(x -> wordsCounter.calculateWordsFrequency(x))
                    .map(x -> wordsCounter.getMostCommonWords(x, params.getTopWordsCount()))
                    .collect(Collectors.toList());

            for(List<WordEntry> entries : collect) {
                for(WordEntry entry: entries) {
                    System.out.println(entry.getWord() + ": " + entry.getCount());
                }
                System.out.println();
            }
        }
        catch(Exception e) {

        }

        return new ArrayList<>();
    }
}
