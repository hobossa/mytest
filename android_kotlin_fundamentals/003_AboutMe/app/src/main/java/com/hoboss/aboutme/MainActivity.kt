package com.hoboss.aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var editText: EditText
    lateinit var nicknameTextView: TextView
    lateinit var buttonDone: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonDone = findViewById<Button>(R.id.done_button)
        buttonDone.setOnClickListener {
            addNickname(it)
        }

        editText = findViewById(R.id.nickname_edit)
        nicknameTextView = findViewById(R.id.nickname_text)
        nicknameTextView.setOnClickListener { updateNickname(it) }
    }

    private fun addNickname(view: View) {
        nicknameTextView.text = editText.text
        editText.visibility = View.GONE
        buttonDone.visibility = View.GONE
        nicknameTextView.visibility = View.VISIBLE
        // Hide the keyboard.
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun updateNickname(view: View) {
        editText.visibility = View.VISIBLE
        buttonDone.visibility = View.VISIBLE
        nicknameTextView.visibility = View.GONE
        // Set the focus to the edit text.
        editText.requestFocus()
        // Show the keyboard.
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(editText, 0)
    }
}