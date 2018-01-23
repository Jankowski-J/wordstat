package com.jjdev.wordstat;

import com.jjdev.wordstat.network.FileDownloader;

public class FileDownloaderMock  implements FileDownloader {

    @Override
    public String downloadFile(String webPath) {
        return webPath;
    }
}
