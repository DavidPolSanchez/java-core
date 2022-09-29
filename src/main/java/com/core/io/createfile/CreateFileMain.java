package com.core.io.createfile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateFileMain {

    public static void main(String[] args) throws IOException {

        String fileName = "file1.txt";
        File file1 = new File(fileName);
        boolean created = file1.createNewFile();
        System.out.println(created);


        String fileName2 = "file2.txt";
        Path file2Path = Paths.get(fileName2);
        Files.createFile(file2Path);
    }
}
