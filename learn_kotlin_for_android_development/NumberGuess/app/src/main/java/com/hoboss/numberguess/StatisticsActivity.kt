package com.hoboss.numberguess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.ViewGroup
import android.widget.TextView
import com.hoboss.numberguess.statistics.Statistics

class StatisticsActivity : AppCompatActivity() {

    companion object {
        const val serializerKey = "statistics.data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)

        restoreData(savedInstanceState)
        showData(Statistics.getStatistics())
    }


    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState?.putSerializable(serializerKey, Statistics.data)
    }

    ///////////////////////////////////////////////////
    ///////////////////////////////////////////////////

    private fun showData(s: List<String>) {
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

    private fun restoreData(savedInstanceState: Bundle?) {
        savedInstanceState?.run {
            getSerializable(serializerKey)?.run {
                Statistics.data.clear()
                Statistics.data.addAll(this as ArrayList<Statistics.GameSessionRecord>)
            }
        }
    }
}