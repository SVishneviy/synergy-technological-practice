package org.example;

public class GreaterThanCalculator {

    public Result calculate(int[] array, int b) {
        int product = 1;
        int count = 0;

        for (int num : array) {
            if (num > b) {
                product *= num;
                count++;
            }
        }
        if (count == 0) {
            product = 0;
        }
        return new Result(product, count, b);
    }
}