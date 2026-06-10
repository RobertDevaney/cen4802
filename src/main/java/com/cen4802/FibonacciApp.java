package com.cen4802;

public class FibonacciApp {

    public static void main(String[] args) {
        int term = 10;
        int result = fibonacci(term);

        System.out.println("The 10th term of the Fibonacci sequence is " + result + ".");
    }

    public static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }

        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}