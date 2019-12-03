package com.onxmaps.playground.calculator.util

/**
 * Singleton Calculator object. Normally I would wrap business
 * functionality into service interfaces with serviceImpl's, however
 * this case has no complexity nor dependencies and is easy to test
 * as-is. This is just a utility class -- keep it simple!
 */
object Calculator {
    fun add(firstNum: Float = 0.0f, secondNum:Float = 0.0f): Float {
        return firstNum.plus(secondNum)
    }

    fun subtract(firstNum: Float = 0.0f, secondNum:Float = 0.0f): Float {
        return firstNum.minus(secondNum)
    }

    fun multiply(firstNum: Float = 1.0f, secondNum:Float = 1.0f): Float {
        return firstNum.times(secondNum)
    }

    fun divide(firstNum: Float = 0.0f, secondNum:Float = 1.0f): Float {
        return firstNum.div(secondNum)
    }

    fun mod(firstNum: Float = 0.0f, secondNum:Float = 1.0f): Float {
        return firstNum.rem(secondNum)
    }
}