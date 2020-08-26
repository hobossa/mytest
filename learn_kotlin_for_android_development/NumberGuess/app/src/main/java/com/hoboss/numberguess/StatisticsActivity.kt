package com.hoboss.numberguess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import com.hoboss.numberguess.statistics.Statistics

class StatisticsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)
        showData(Statistics.getStatistics())
    }

    fun showData(s: List<String>) {
        val container = findViewById<ViewGroup>(
            R.id.statisticsContainer
        )
        container.removeAllViews()
        s.forEach { line ->
            container.addView(TextView(this).apply {
                text = line
            })
        }
    }
}