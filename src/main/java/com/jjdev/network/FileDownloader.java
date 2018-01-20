package com.jjdev.network;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.text.SimpleDateFormat;
import java.util.Date;


public class FileDownloader {
    public String downloadFile(String webPath) throws IOException {

        String tempDir = System.getProperty("java.io.tmpdir");
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String[] split = webPath.split("/");
        String fileName = split[split.length - 1];

        String outputPath = tempDir + "/" + fileName + "_" + timeStamp;

        try {

            URL website = new URL(webPath);
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream(outputPath);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
            rbc.close();
            return outputPath;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
