package com.jjdev.wordstat;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author Jakub Jankowski
 */
public class FileParser {
    public List<String> getWordsFromFile(String fileName) {
        List<String> words = null;

        try {
            Path path = Paths.get(fileName);
            List<String> lines = Files.readAllLines(path);

            words = lines.stream()
                    .map(l -> l.split(" "))
                    .flatMap(l -> Arrays.stream(l))
                    .collect(Collectors.toList());
        } catch (Exception e) {

        }
        
        return words;
    }
}
