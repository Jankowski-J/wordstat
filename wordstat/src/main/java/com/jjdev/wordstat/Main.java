package com.jjdev.wordstat;

import com.jjdev.parsers.FileParser;
import com.jjdev.parsers.FileParserImpl;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author Jakub Jankowski
 */
public class Main {
    public static void main(String args[]) {
        FileParser parser = new FileParserImpl();
        List<String> wordsFromFile = null;
        try {
            wordsFromFile = parser.getLinesFromLocalFile("D:\\Dev\\words-counter\\wordstat\\test-data\\1.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(String word : wordsFromFile) {
            System.out.println(word);
        }
    }
}
