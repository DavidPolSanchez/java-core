package com.core.io.downloadfile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;

public class DownloadFileMain {

    public static void main(String[] args) throws IOException {

        String url = "https://raw.githubusercontent.com/mwaskom/seaborn-data/master/tips.csv";
        String[] urlParts = url.split("/");
        String fileName = urlParts[urlParts.length - 1];

        InputStream input = new URL(url).openStream();

        String timedFileName = getTimedFileName(fileName);
        Path filePath = Paths.get(timedFileName);

        Files.copy(input, filePath);
        // Files.copy(input, filePath, StandardCopyOption.REPLACE_EXISTING); // reemplazar el existente

    }

    private static String getTimedFileName(String fileName){
        LocalDateTime now = LocalDateTime.now();
        String[] fileParts = fileName.split("\\.");

        String currentTime = String.format("%d-%d-%d-%d-%d-%d",
                now.getYear(), now.getMonthValue(), now.getDayOfMonth(), // date
                now.getHour(), now.getMinute(), now.getSecond()); // time

        return fileParts[0] + "-" + currentTime + "." + fileParts[1];
    }
}
