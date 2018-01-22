package com.jjdev.wordstat;

import com.jjdev.wordstat.network.FileDownloader;

import java.io.IOException;

public class FileDownloaderMock  implements FileDownloader {

    @Override
    public String downloadFile(String webPath) throws IOException {
        return webPath;
    }
}
