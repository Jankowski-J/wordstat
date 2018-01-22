package com.jjdev.wordstat.parsers;

import com.jjdev.wordstat.model.ParamsParseStatus;
import com.jjdev.wordstat.model.WordstatParams;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WordstatParamsParserImplTests {
    private WordstatParamsParserImpl sut;

    @Before
    public void setup() {
        sut = new WordstatParamsParserImpl();
    }

    @Test
    public void parseParams_forEmptyArray_returnsCorrectStatus() {
        WordstatParams wordstatParams = sut.parseParams(new String[] {});

        Assert.assertEquals(ParamsParseStatus.NotEnoughArgsProvided, wordstatParams.getStatus());
    }

    @Test
    public void parseParams_forOnlyOneElementInArray_returnsInvalidParams() {
        WordstatParams wordstatParams = sut.parseParams(new String[] {"1"});

        Assert.assertEquals(ParamsParseStatus.NotEnoughArgsProvided, wordstatParams.getStatus());
    }

    @Test
    public void parseParams_forOnlyNumericElementsInArray_returnsInvalidParams() {
        WordstatParams wordstatParams = sut.parseParams(new String[] {"1", "2"});

        Assert.assertEquals(ParamsParseStatus.InvalidArgsProvided, wordstatParams.getStatus());
    }

    @Test
    public void parseParams_forOnlyStringArgsProvided_returnsInvalidParams() {
        WordstatParams wordstatParams = sut.parseParams(new String[] {"aaaa", "bbbbb"});

        Assert.assertEquals(ParamsParseStatus.InvalidArgsProvided, wordstatParams.getStatus());
    }

    @Test
    public void parseParams_forValidArguments_returnsValidParams() {
        WordstatParams wordstatParams = sut.parseParams(new String[] {"aaa", "1"});

        Assert.assertEquals(ParamsParseStatus.Success, wordstatParams.getStatus());
    }

    @Test
    public void parseParams_forValidArgumentsAndFlippedOrded_returnsValidParams() {
        WordstatParams wordstatParams = sut.parseParams(new String[] {"1", "aaa"});

        Assert.assertEquals(ParamsParseStatus.Success, wordstatParams.getStatus());
    }
}
