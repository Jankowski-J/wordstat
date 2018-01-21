package com.jjdev.network;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDownloaderImplTests {
    private FileDownloaderImpl sut;

    @Before
    public void setup() {
        sut = new FileDownloaderImpl();
    }

    @Test
    public void sampleFileDownloadTest() throws IOException {
        String fileName = sut.downloadFile("http://www.sample-videos.com/doc/Sample-doc-file-100kb.doc");
        Path path = Paths.get(fileName);
        boolean doesFileExist = Files.exists(path);
        Assert.assertTrue(doesFileExist);

        Files.delete(path);
    }
}
