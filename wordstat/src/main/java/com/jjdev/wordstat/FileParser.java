package com.jjdev.wordstat;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Jakub Jankowski
 */
public class FileParser {
    public Map<String, Integer> getWordCounts(String fileName) {
        
        try {
            Path path = Paths.get(fileName);
            List<String> lines = Files.readAllLines(path);
        } catch (Exception e) {
            
        }
        
        return new HashMap<String, Integer>();
    }
}
