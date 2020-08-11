package com.hoboss.numberguess.random.impl

import com.hoboss.numberguess.random.RandomNumberGenerator
import kotlin.math.floor

class StdRandom : RandomNumberGenerator {
    override fun rnd(minInt: Int, maxInt: Int): Int {
        val span = maxInt - minInt + 1
        return minInt + floor(Math.random() * span).toInt()
    }
}