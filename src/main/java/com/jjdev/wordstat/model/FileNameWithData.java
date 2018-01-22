package com.jjdev.wordstat.model;

public class FileNameWithData<T> {
    private String fileName;
    private T data;

    public FileNameWithData(String fileName, T data) {
        this.fileName = fileName;
        this.data = data;
    }

    public String getFileName() {
        return fileName;
    }

    public T getData() {
        return data;
    }
}
