package com.hoboss.numberguess.random.impl

import com.hoboss.numberguess.random.RandomNumberGenerator
import java.util.*

class RandomRandom : RandomNumberGenerator {
    override fun rnd(minInt: Int, maxInt: Int): Int {
        val rnd: Random = Random()
        return minInt + rnd.nextInt(maxInt - minInt + 1)
    }
}