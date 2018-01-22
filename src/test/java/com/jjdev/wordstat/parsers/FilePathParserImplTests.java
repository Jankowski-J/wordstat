package com.jjdev.wordstat.parsers;

import com.jjdev.wordstat.FileDownloaderMock;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FilePathParserImplTests {

    private FilePathParserImpl sut;
    private ClassLoader loader;

    @Before
    public void setup() {
        sut = new FilePathParserImpl(new FileDownloaderMock());
        loader = getClass().getClassLoader();
    }

    @Test
    public void getFileNamesToProcess_forSingleLocalFile_returnsSinglePath() {
        URL resource = loader.getResource("1.txt");
        try {
            String path = resource.getPath().substring(1);
            List<String> fileNamesToProcess = sut.getFileNamesToProcess(path);

            assertEquals(1, fileNamesToProcess.size());
            String filePath = fileNamesToProcess.get(0);
            assertTrue(filePath.endsWith("1.txt"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getFileNamesToProcess_forRemoteLocalFile_returnsSinglePath() {
        try {
            List<String> fileNamesToProcess = sut.getFileNamesToProcess("http://www.sample-videos.com/doc/Sample-doc-file-100kb.doc");

            assertEquals(1, fileNamesToProcess.size());
            String filePath = fileNamesToProcess.get(0);
            assertTrue(filePath.endsWith("Sample-doc-file-100kb.doc"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getFileNamesToProcess_forMultipleLocalFiles_returnsAllOfThem() {
        URL resource = loader.getResource("sharingName/file1.txt");
        try {
            String path = resource.getPath().substring(1).replace("file1", "file");
            List<String> fileNamesToProcess = sut.getFileNamesToProcess(path);

            assertEquals(5, fileNamesToProcess.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getFileNamesToProcess_forMultipleLocalFiles_returnsCorrectFileNames() {
        URL resource = loader.getResource("sharingName/file1.txt");
        try {
            String path = resource.getPath().substring(1).replace("file1", "file");
            List<String> fileNamesToProcess = sut.getFileNamesToProcess(path);

            String first = fileNamesToProcess.get(0);
            String second = fileNamesToProcess.get(1);
            String third = fileNamesToProcess.get(2);
            String fourth = fileNamesToProcess.get(3);
            String fifth = fileNamesToProcess.get(4);

            assertTrue(first.endsWith("file1.txt"));
            assertTrue(second.endsWith("file2.txt"));
            assertTrue(third.endsWith("file3.txt"));
            assertTrue(fourth.endsWith("file4.txt"));
            assertTrue(fifth.endsWith("file5.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
