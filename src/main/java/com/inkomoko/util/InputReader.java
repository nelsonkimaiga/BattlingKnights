package com.inkomoko.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputReader {
    public static List<String> readInputFile(String filePath) {
        List<String> movements = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                movements.add(line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
        }
        return movements;
    }
}