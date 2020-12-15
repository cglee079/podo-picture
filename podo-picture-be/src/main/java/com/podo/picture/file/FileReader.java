package com.podo.picture.file;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FileReader {

    public List<File> readFilesInFolder(final String path){
        File folder = new File(path);

        if(folder.isDirectory()){
            return Arrays.stream(folder.listFiles())
                    .collect(Collectors.toList());
        }

        return Collections.emptyList();
    }
}
