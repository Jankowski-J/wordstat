package com.jjdev.parsers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 *
 * @author Jakub Jankowski
 */
public class FileParserImpl implements FileParser {
    public List<String> getLinesFromFile(String fileName) throws IOException {
        try {
            Path path = Paths.get(fileName);
            List<String> lines = Files.readAllLines(path);

//            words = lines.stream()
//                    .map(l -> l.split(" "))
//                    .flatMap(l -> Arrays.stream(l))
//                    .collect(Collectors.toList());

            return lines;
        } catch (Exception e) {
            throw e;
        }
    }


    @Override
    public List<String> getLinesFromLocalFile(String fileName) throws IOException {
        try {
            Path path = Paths.get(fileName);
            List<String> lines = Files.readAllLines(path);


            return lines;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<String> sanitizeAndGetWords(List<String> lines) {
        return null;
    }
}
