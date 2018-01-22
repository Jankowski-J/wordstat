package com.jjdev.wordstat.parsers;

import com.jjdev.wordstat.model.ParamsParseStatus;
import com.jjdev.wordstat.model.WordstatParams;

public class WordstatParamsParserImpl implements WordstatParamsParser {

    @Override
    public WordstatParams parseParams(String[] args) {
        if (args.length < 2) {
            return WordstatParams.invalid(ParamsParseStatus.NotEnoughArgsProvided);
        }

        String firstArg = args[0];
        String secondArg = args[1];
        int topWords;
        String path;

        if (isNumeric(firstArg)) {
            topWords = Integer.parseInt(firstArg);
            path = secondArg;
        } else if (isNumeric(secondArg)) {
            topWords = Integer.parseInt(secondArg);
            path = firstArg;
        } else {
            return WordstatParams.invalid(ParamsParseStatus.InvalidArgsProvided);
        }

        if (isNumeric(path)) {
            return WordstatParams.invalid(ParamsParseStatus.InvalidArgsProvided);
        }

        return WordstatParams.valid(topWords, path);
    }

    private boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }
}
