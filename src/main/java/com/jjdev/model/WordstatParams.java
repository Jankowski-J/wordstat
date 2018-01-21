package com.jjdev.model;

public class WordstatParams {


    private int topWordsCount;
    private String path;
    private ParamsParseStatus status;

    private WordstatParams(int topWords, String path, ParamsParseStatus status) {
        this.topWordsCount = topWords;
        this.path = path;
        this.status = status;
    }

    public static WordstatParams valid(int topWords, String path) {
        return new WordstatParams(topWords, path, ParamsParseStatus.Success);
    }

    public static WordstatParams invalid(ParamsParseStatus status) {
        return new WordstatParams(0, "", status);
    }

    public int getTopWordsCount() {
        return topWordsCount;
    }

    public String getPath() {
        return path;
    }

    public ParamsParseStatus getStatus() {
        return status;
    }
}
