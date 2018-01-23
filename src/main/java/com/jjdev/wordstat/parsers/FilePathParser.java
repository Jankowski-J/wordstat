package com.jjdev.wordstat.parsers;

import java.io.IOException;
import java.util.List;

public interface FilePathParser {
    List<String> getFileNamesToProcess(String rawPath) throws IOException;
}
