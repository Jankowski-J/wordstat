package com.jjdev.parsers;

import com.jjdev.model.WordstatParams;

public interface WordstatParamsParser {
    WordstatParams parseParams(String[] args);
}
