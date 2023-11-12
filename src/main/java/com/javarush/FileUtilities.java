package com.javarush;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileUtilities {
    /*
    Method gets data from the file at the specified path
    Params: filePath - path to the data file
    Returns: data from file
     */
    public List<String> getDataFromFile(String filePath) {
        Path path = Path.of(filePath);
        List<String> fileData;
        try {
            fileData = Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return fileData;
    }

    /*
    Method puts data to the file at the specified path
    Params: filePath - path to the data file
            fileData - data to be written to the file
    Creates a data file
     */
    public void writeDataToFile(String filePath, List<String> fileData) {
        Path path = Path.of(filePath);
        try {
            Files.write(path, fileData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
