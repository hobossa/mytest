package com.hoboss.numberguess.gui

import android.content.Context
import android.util.AttributeSet
import android.widget.ScrollView
import android.widget.TextView


class Console(ctx: Context, aset: AttributeSet? = null)
    : ScrollView(ctx, aset) {
    companion object {
        const val BACKGROUND_COLOR = 0x40FFFF00
        const val MAX_LINES = 100
    }
    private val tv = TextView(ctx)
    var text:String
        get() = tv.text.toString()
        set(value) { tv.setText(value) }
    init {
        setBackgroundColor(BACKGROUND_COLOR)
        addView(tv)
    }

    fun log(msg:String) {
        val l = tv.text.let {
            if(it == "") listOf() else it.split("\n")
        }.takeLast(MAX_LINES) + msg
        tv.text = l.joinToString("\n")
        post { fullScroll(ScrollView.FOCUS_DOWN) }
    }
}