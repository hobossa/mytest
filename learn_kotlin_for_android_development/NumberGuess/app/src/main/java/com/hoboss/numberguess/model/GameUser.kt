package com.hoboss.numberguess.model

data class GameUser(
    val firstName: String,
    val lastName: String,
    val userName: String,
    val registrationNumber: Int,
    val birthday: String = "",
    val userRank: Double = 0.0
) {
    enum class Gender { F, M, X }

    val fullName: String
        get() = "$firstName $lastName"

    val initials: String
        get() = "${firstName.toUpperCase()} ${lastName.toUpperCase()}"

}