package com.hoboss.numberguess.model

class GameUser(
    val firstName: String,
    val lastName: String,
    val userName: String,
    val registrationNumber: Int,
    val birthday: String = "",
    val userRank: Double = 0.0
) {

}