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

        if(args.length < 2) {
            System.out.println("Two arguments are needed: [N] (number) - indicates how many top words to display; [path] (string) - target file path");
        }

        String topArg = args[0];
        int n;
        try {
            n = Integer.parseInt(topArg);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number specified: " + topArg);
        }
    }
}
