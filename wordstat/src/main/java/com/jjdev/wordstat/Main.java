package com.jjdev.wordstat;

import java.util.List;

/**
 *
 * @author Jakub Jankowski
 */
public class Main {
    public static void main(String args[]) {
        FileParser parser = new FileParser();
        List<String> wordsFromFile = parser.getWordsFromFile("D:\\Dev\\words-counter\\wordstat\\test-data\\1.txt");

        for(String word : wordsFromFile) {
            System.out.println(word);
        }
    }
}
