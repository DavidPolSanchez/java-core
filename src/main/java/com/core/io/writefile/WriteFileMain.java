package com.core.io.writefile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WriteFileMain {

    public static void main(String[] args) throws IOException {

        String fileName = "write.txt";

        String content = """
                Lorem ipsum dolor sit amet, consectetur adipiscing elit,
                sed do eiusmod tempor incididunt ut labore et dolore magna 
                aliqua. Natoque penatibus et magnis dis parturient montes
                """;

        Path filePath = Paths.get(fileName);

        Files.write(filePath, content.getBytes());

    }
}
