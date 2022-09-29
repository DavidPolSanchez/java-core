package com.core.io.readfile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ReadFileMain {

    public static void main(String[] args) throws IOException {

        String fileName = "file1.txt";

        // comprobar si existe el archivo
        Path filePath = Paths.get(fileName);
        boolean exists = Files.exists(filePath);
        System.out.println(fileName + " existe: " + exists);

        // comprobar si se puede leer
        boolean isReadable = Files.isReadable(filePath);
        System.out.println(fileName + " se puede leer: " + isReadable);

        // leer
        List<String> lines = Files.readAllLines(filePath);
        for (String line : lines) {
            System.out.println(line);
        }

        // lectura tradicional, try catch normal
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = bufferedReader.readLine()) != null)
                System.out.println(line);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null)
                bufferedReader.close(); // cerramos el bufferedReader manualmente
        }

        // try catch with resources
        try (
                FileReader fileReader = new FileReader(fileName);
                BufferedReader bufferedReader2 = new BufferedReader(fileReader)
        ) {
            String line;
            while ((line = bufferedReader2.readLine()) != null)
                System.out.println(line);

        } catch (IOException e) {
            e.printStackTrace();
        } // bufferedReader se cierra automáticamente gracias al try-with-resources


    }
}
