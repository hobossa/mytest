package com.hoboss.numberguess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.hoboss.numberguess.common.Constants
import com.hoboss.numberguess.model.GameUser
import kotlinx.android.synthetic.main.activity_main.*
import com.hoboss.numberguess.random.RandomNumberGenerator
import com.hoboss.numberguess.random.impl.StdRandom

class MainActivity : AppCompatActivity() {
    var started = false
    var number = 0
    var tries = 0
    private val randomNumberGenerator : RandomNumberGenerator =
        StdRandom()
    var gameUser = GameUser(
        firstName = "John",
        lastName = "Doe",
        userName = "jdoe",
        registrationNumber = 0,
        birthday = "1900-01-01",
        userRank = 0.0
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fetchSavedInstanceData(savedInstanceState)
        doGuess.isEnabled = started
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        putInstanceData(outState)
    }

    fun start(v: View) {
        log("Game started")
        num.setText("")
        started = true
        doGuess.isEnabled = true
        status.text = getString(R.string.guess_hint, Constants.LOWER_BOUND, Constants.UPPER_BOUND)
        number = randomNumberGenerator.rnd(Constants.LOWER_BOUND, Constants.UPPER_BOUND)
        tries = 0
    }

    fun guess(v: View) {
        if (num.text.toString() == "") return
        tries++
        log("Guessed ${num.text} (tries:${tries})")
        val g = num.text.toString().toInt()
        if (g < number) {
            status.setText(R.string.status_too_low)
            num.setText("")
        } else if (g > number) {
            status.setText(R.string.status_too_high)
            num.setText("")
        } else {
            status.text = getString(
                R.string.status_hit,
                tries
            )
            started = false
            doGuess.isEnabled = false
        }
    }

    ///////////////////////////////////////////////////
    ///////////////////////////////////////////////////
    private fun putInstanceData(outState: Bundle?) {
        if (outState != null) with(outState) {
            putBoolean("started", started)
            putInt("number", number)
            putInt("tries", tries)
            putString("statusMsg", status.text.toString())
            putStringArrayList(
                "logs",
                ArrayList(console.text.split("\n"))
            )
        }
    }

    private fun fetchSavedInstanceData(
        savedInstanceState: Bundle?
    ) {
        if (savedInstanceState != null)
            with(savedInstanceState) {
                started = getBoolean("started")
                number = getInt("number")
                tries = getInt("tries")
                status.text = getString("statusMsg")
                console.text = getStringArrayList("logs")!!.joinToString("\n")
            }
    }

    private fun log(msg: String) {
        Log.d("LOG", msg)
        console.log(msg)
    }
}
