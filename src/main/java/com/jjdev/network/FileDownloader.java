package com.jjdev.network;

import java.io.IOException;


public interface FileDownloader {
    String downloadFile(String webPath) throws IOException;
}
