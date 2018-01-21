package com.jjdev.parsers;

import java.io.IOException;
import java.util.List;

public interface FileParser {
    List<String> getLinesFromLocalFile(String fileName) throws IOException;

    List<String> sanitizeAndGetWords(List<String> lines);
}
