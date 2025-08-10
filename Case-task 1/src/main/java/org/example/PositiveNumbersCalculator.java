package org.example;

public class PositiveNumbersCalculator {

    public Result calculate(int[] array) {
        int sum = 0;
        int count = 0;

        for (int num : array) {
            if (num > 0) {
                sum += num;
                count++;
            }
        }

        return new Result(sum, count);
    }
}