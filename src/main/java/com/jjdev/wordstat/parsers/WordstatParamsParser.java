package com.jjdev.wordstat.parsers;

import com.jjdev.wordstat.model.WordstatParams;

public interface WordstatParamsParser {
    WordstatParams parseParams(String[] args);
}
