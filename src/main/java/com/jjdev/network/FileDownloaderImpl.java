package com.jjdev.network;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileDownloaderImpl implements FileDownloader {
    public String downloadFile(String webPath) throws IOException {

        String timeStamp = getTimeStamp();
        String outputPath = GetDestinationPath(webPath, timeStamp);
        URL website = new URL(webPath);

        try (ReadableByteChannel rbc = Channels.newChannel(website.openStream())){

            FileOutputStream fos = new FileOutputStream(outputPath);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
            return outputPath;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private String GetDestinationPath(String webPath, String timeStamp) {
        String tempDir = System.getProperty("java.io.tmpdir");
        String[] split = webPath.split("/");
        String fileName = split[split.length - 1];

        return tempDir + "/" + fileName + "_" + timeStamp;
    }

    private String getTimeStamp() {
        return new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    }
}
