package com.onxmaps.playground.calculator

import com.onxmaps.playground.calculator.util.Calculator
import org.junit.Assert
import org.junit.Test

/**
 * Low-level unit tests.
 */
class CalculatorTest {

    @Test
    fun testAdd() {
        Assert.assertEquals(4.0f, Calculator.add(2.0f, 2.0f))
    }

    @Test
    fun testAddFalseCase() {
        Assert.assertFalse(Calculator.add(2.0f, 2.0f) == 1.0f)
    }

    @Test
    fun testSubtract() {
        Assert.assertEquals(1.0f, Calculator.subtract(3.0f, 2.0f))
        Assert.assertEquals(-1.0f, Calculator.subtract(2.0f, 3.0f))
    }

    @Test
    fun testSubtractFalseCase() {
        Assert.assertFalse(Calculator.subtract(5.0f, 1.0f) == 0.0f)
    }

    @Test
    fun testMultiply() {
        Assert.assertEquals(16.0f, Calculator.multiply(4.0f, 4.0f))
    }

    @Test
    fun testMultiplyFalseCase() {
        Assert.assertFalse(Calculator.multiply(4.0f, 4.0f) == 1.0f)
    }

    @Test
    fun testDivide() {
        Assert.assertEquals(4.0f, Calculator.divide(8.0f, 2.0f))
    }

    @Test
    fun testDivideFalseCase() {
        Assert.assertFalse(Calculator.divide(2.0f, 2.0f) == 0.0f)
    }

    @Test
    fun testMod() {
        Assert.assertEquals(1.0f, Calculator.mod(10.0f, 3.0f))
    }

    @Test
    fun testModFalseCase() {
        Assert.assertFalse(Calculator.mod(15.0f, 2.0f) == 0.0f)
    }

}