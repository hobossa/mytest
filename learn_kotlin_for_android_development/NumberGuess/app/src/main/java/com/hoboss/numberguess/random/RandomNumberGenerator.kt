package com.hoboss.numberguess.random

interface RandomNumberGenerator {
    fun rnd(minInt: Int, maxInt: Int): Int
}