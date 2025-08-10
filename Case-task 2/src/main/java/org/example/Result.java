package org.example;

public class Result {
    private final int product;
    private final int count;
    private final int b;

    public Result(int product, int count, int b) {
        this.product = product;
        this.count = count;
        this.b = b;
    }

    public int getProduct() {
        return product;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "Product = " + product + ", Count = " + count + ", b = " + b;
    }
}
