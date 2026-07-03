package com.cen4802;

public class FibonacciApp {

    public static void main(String[] args) {
        int term = 10;
        int result = fibonacci(term);

        System.out.println("Dry run workflow demo, the 10th term of the Fibonacci sequence is " + result + ".");
    }

    /**
     * Calculates the nth term in the Fibonacci sequence using recursion.
     *
     * @param n the position in the Fibonacci sequence
     * @return the Fibonacci value at the specified position
     */

    public static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }

        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}