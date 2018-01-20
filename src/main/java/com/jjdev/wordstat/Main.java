package com.jjdev.wordstat;

import com.jjdev.facades.WordsFrequencyFacade;
import com.jjdev.model.ParamsParseStatus;
import com.jjdev.model.WordstatParams;
import com.jjdev.network.FileDownloaderImpl;
import com.jjdev.parsers.FileParserImpl;
import com.jjdev.parsers.WordsCounterImpl;
import com.jjdev.parsers.WordstatParamsParserImpl;

import java.io.IOException;

/**
 * @author Jakub Jankowski
 */
public class Main {
    public static void main(String args[]){

        WordstatParamsParserImpl paramsParser = new WordstatParamsParserImpl();

        WordstatParams params = paramsParser.parseParams(args);
        ParamsParseStatus status = params.getStatus();

        if (status != ParamsParseStatus.Success) {
            System.out.println("Two arguments are needed: [N] (number) - indicates how many top words to display; [path] (string) - target file path.");
            System.out.println("The order of arguments is irrelevant");
            return;
        }

        WordsFrequencyFacade facade = new WordsFrequencyFacade(new FileDownloaderImpl(),
                new FileParserImpl(), new WordsCounterImpl());

        try {
            facade.getWordsFrequencies(params);
        } catch (IOException e) {
            System.out.println("There was an error while opening file: " + params.getPath());
            System.out.println("Please make sure that specified file exists.");
            e.printStackTrace();
        }
    }
}
