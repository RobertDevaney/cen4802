package com.cen4802;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FibonacciAppTest {

    @Test
    void testFibonacciBaseCases() {
        assertEquals(0, FibonacciApp.fibonacci(0));
        assertEquals(1, FibonacciApp.fibonacci(1));
    }

    @Test
    void testFibonacciTenthTerm() {
        assertEquals(55, FibonacciApp.fibonacci(10));
    }

    @Test
    void testFibonacciAnotherValue() {
        assertEquals(21, FibonacciApp.fibonacci(8));
    }

    @Test
    void testFibonacciTwelfthTerm() {
        assertEquals(144, FibonacciApp.fibonacci(12));
    }

    @Test
    void testFibonacciNegativeNumber() {
        assertThrows(IllegalArgumentException.class, () -> FibonacciApp.fibonacci(-1));
    }
}