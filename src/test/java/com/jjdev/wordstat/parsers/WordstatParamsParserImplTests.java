package com.jjdev.wordstat.parsers;

import com.jjdev.wordstat.model.ParamsParseStatus;
import com.jjdev.wordstat.model.WordstatParams;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WordstatParamsParserImplTests {
    private WordstatParamsParserImpl sut;

    @Before
    public void setup() {
        sut = new WordstatParamsParserImpl();
    }

    @Test
    public void parseParams_forEmptyArray_returnsCorrectStatus() {
        WordstatParams wordstatParams = sut.parseParams(new String[] {});

        assertEquals(ParamsParseStatus.NotEnoughArgsProvided, wordstatParams.getStatus());
    }

    @Test
    public void parseParams_forOnlyOneElementInArray_returnsInvalidParams() {
        WordstatParams wordstatParams = sut.parseParams(new String[] {"1"});

        assertEquals(ParamsParseStatus.NotEnoughArgsProvided, wordstatParams.getStatus());
    }

    @Test
    public void parseParams_forOnlyNumericElementsInArray_returnsInvalidParams() {
        WordstatParams wordstatParams = sut.parseParams(new String[] {"1", "2"});

        assertEquals(ParamsParseStatus.InvalidArgsProvided, wordstatParams.getStatus());
    }

    @Test
    public void parseParams_forOnlyStringArgsProvided_returnsInvalidParams() {
        WordstatParams wordstatParams = sut.parseParams(new String[] {"aaaa", "bbbbb"});

        assertEquals(ParamsParseStatus.InvalidArgsProvided, wordstatParams.getStatus());
    }

    @Test
    public void parseParams_forValidArguments_returnsValidParams() {
        WordstatParams wordstatParams = sut.parseParams(new String[] {"aaa", "1"});

        assertEquals(ParamsParseStatus.Success, wordstatParams.getStatus());
    }

    @Test
    public void parseParams_forValidArgumentsAndFlippedOrded_returnsValidParams() {
        WordstatParams wordstatParams = sut.parseParams(new String[] {"1", "aaa"});

        assertEquals(ParamsParseStatus.Success, wordstatParams.getStatus());
    }
}
