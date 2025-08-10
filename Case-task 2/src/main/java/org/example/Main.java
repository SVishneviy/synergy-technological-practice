package org.example;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {
        FileDataReader reader = new FileDataReader();
        GreaterThanCalculator calculator = new GreaterThanCalculator();

        String filePath = "Case-task 2/src/main/resources/test_data.txt";
        List<int[]> arrays = reader.readArraysFromFile(filePath);
        for (int[] arr : arrays) {
            int b = new Random().nextInt(-5, 6);
            Result result = calculator.calculate(arr, b);
            System.out.println(result);
        }
    }
}