package org.example;

public class Result {
    private final int sum;
    private final int count;

    public Result(int sum, int count) {
        this.sum = sum;
        this.count = count;
    }

    public int getSum() {
        return sum;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "Sum = " + sum + ", Count = " + count;
    }
}
