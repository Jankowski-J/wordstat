package com.jjdev.parsers;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class FileParserImplTests {

    private FileParserImpl sut;
    private ClassLoader loader;

    public FileParserImplTests() {
        sut = new FileParserImpl();
        loader = getClass().getClassLoader();
    }

    @Test
    public void getLinesFromLocalFile_forFileWithOneLine_shouldReturnThatLine(){
        URL resource = loader.getResource("1.txt");
        String path = resource.getPath().substring(1);

        try {
            List<String> linesFromFile = sut.getLinesFromFile(path);

            Assert.assertEquals(1, linesFromFile.size());
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void getLinesFromLocalFile_forFileWithSevenLines_shouldReturnTheseLines(){
        URL resource = loader.getResource("2.txt");
        String path = resource.getPath().substring(1);

        try {
            List<String> linesFromFile = sut.getLinesFromFile(path);

            Assert.assertEquals(7, linesFromFile.size());
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
}
