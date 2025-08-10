package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileDataReader {

    public List<int[]> readArraysFromFile(String filePath) throws IOException {
        List<int[]> arrays = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                int n = Integer.parseInt(line);
                int[] arr = new int[n];

                String[] parts = reader.readLine().trim().split("\\s+");
                for (int i = 0; i < n; i++) {
                    arr[i] = Integer.parseInt(parts[i]);
                }
                arrays.add(arr);

            }
        }
        return arrays;
    }
}
