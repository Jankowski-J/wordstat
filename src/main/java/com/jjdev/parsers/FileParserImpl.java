package com.jjdev.parsers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author Jakub Jankowski
 */
public class FileParserImpl implements FileParser {

    @Override
    public List<String> getLinesFromLocalFile(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        List<String> lines = Files.readAllLines(path);

        return lines;
    }

    @Override
    public List<String> sanitizeAndGetWords(List<String> lines) {
        List<String> sanitized = lines.stream()
                .map(l -> l.replaceAll("[,:;.?!-]", ""))
                .map(l -> l.split(" "))
                .flatMap(l -> Arrays.stream(l))
                .collect(Collectors.toList());

        return sanitized;
    }
}