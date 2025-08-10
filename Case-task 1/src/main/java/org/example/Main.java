package org.example;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        FileDataReader reader = new FileDataReader();
        PositiveNumbersCalculator calculator = new PositiveNumbersCalculator();

        String filePath = "Case-task 1/src/main/resources/test_data.txt";
        List<int[]> arrays = reader.readArraysFromFile(filePath);

        for (int[] arr : arrays) {
            Result result = calculator.calculate(arr);
            System.out.println(result);
        }
    }
}