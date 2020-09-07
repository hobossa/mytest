package com.hoboss.multithreadedpi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.Callable
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    var points = 0L
    var insideCircle = 0L
    var totalIters = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState?.run {
            points = getLong("points")
            insideCircle = getLong("insideCircle")
            totalIters = getLong("totalIter")
        }

        val cores = Runtime.getRuntime().availableProcessors()
        procs.text = cores.toString()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState?.run {
            putLong("points", points)
            putLong("insideCircle", insideCircle)
            putLong("totalIter", totalIters)
            report()
        }
    }

    fun calc(view: View) {
        val t1 = System.currentTimeMillis()
        val nThreads = threads.text.toString().takeIf { it != "" }?.toInt()?:1
        val itersNum = iters.text.toString().takeIf { it != "" }?.toInt()?:10000
        val itersPerThread = itersNum / nThreads
        val srvc = Executors.newFixedThreadPool(nThreads)
        val callables = (1..nThreads).map {
            Callable<Pair<Int, Int>> {
                var i = 0
                var p = 0
                (1..itersPerThread).forEach { _ ->
                    val x = Math.random()
                    val y = Math.random()
                    val r = x * x + y * y
                    i++
                    if (r < 1.0) {
                        p++
                    }
                }
                Pair(i, p)
            }
        }
        val futures = srvc.invokeAll(callables)
        futures.forEach { f ->
            val p = f.get()
            points += p.first
            insideCircle += p.second
        }
        val t2 = System.currentTimeMillis()
        calcTime.text = (t2-t1).toString()
        report()
    }

    fun reset(view: View) {
        points = 0
        insideCircle = 0
        report()
    }

    private fun report(){
        cumulIters.text = points.toString()
        if(points > 0) {
            val pipi = 1.0 * insideCircle / points * 4
            pi.text = pipi.toString()
        } else {
            pi.text = ""
        }
    }
}