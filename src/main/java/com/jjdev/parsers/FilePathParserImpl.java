package com.jjdev.parsers;

import com.jjdev.network.FileDownloader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FilePathParserImpl implements FilePathParser {

    private FileDownloader fileDownloader;

    public FilePathParserImpl(FileDownloader downloader) {
        fileDownloader = downloader;
    }

    @Override
    public List<String> getFileNamesToProcess(String rawPath) throws IOException {
        boolean isNetworkResource = rawPath.startsWith("http");

        List<String> fileNames = new ArrayList<>();

        if (isNetworkResource) {
            tryGetRemoteFile(rawPath, fileNames);
        } else {
            Path path = Paths.get(rawPath);
            boolean doesFileExist = Files.exists(path);

            if (!doesFileExist) {
                return getMultipleLocalFileNames(rawPath, fileNames);
            }
            fileNames.add(rawPath);
        }
        return fileNames;
    }

    private List<String> getMultipleLocalFileNames(String rawPath, List<String> fileNames) {
        int dotIndex = rawPath.lastIndexOf(".");
        String extension = "";
        if (dotIndex > 0) {
            extension = rawPath.substring(dotIndex + 1);
        }

        String baseFileName = rawPath.replace("." + extension, "");
        int currentFileIndex = 1;
        String filePath = baseFileName + currentFileIndex + "." + extension;
        while (Files.exists(Paths.get(filePath))) {
            fileNames.add(filePath);
            currentFileIndex++;
            filePath = baseFileName + currentFileIndex + "." + extension;
        }
        return fileNames;
    }

    private void tryGetRemoteFile(String rawPath, List<String> fileNames) {
        try {
            String fileName = fileDownloader.downloadFile(rawPath);
            fileNames.add(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
