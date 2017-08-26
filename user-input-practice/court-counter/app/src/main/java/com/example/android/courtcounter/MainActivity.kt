package com.example.android.courtcounter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    val POINTS_FOR_FREE_THROW: Int = 1
    var scoreTeamA: Int = 0
    var scoreTeamB: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        displayForTeamA(scoreTeamA)
        displayForTeamB(scoreTeamB)
    }

    // Team A
    fun displayForTeamA(score: Int) {
        val scoreView = findViewById<TextView>(R.id.team_a_score) as TextView
        scoreView.text = score.toString()
    }

    fun addOnePointTeamA(view: View) {
        displayForTeamA(++scoreTeamA)
    }

    fun addThreePointsTeamA(view: View) {
        scoreTeamA += 3
        displayForTeamA(scoreTeamA)
    }

    fun addFreeThrowTeamA(view: View) {
        scoreTeamA += POINTS_FOR_FREE_THROW
        displayForTeamA(scoreTeamA)
    }

    // Team B
    fun displayForTeamB(score: Int) {
        val scoreView = findViewById<TextView>(R.id.team_b_score) as TextView
        scoreView.text = score.toString()
    }

    fun addOnePointTeamB(view: View) {
        displayForTeamB(++scoreTeamB)
    }

    fun addThreePointsTeamB(view: View) {
        scoreTeamB += 3
        displayForTeamB(scoreTeamB)
    }

    fun addFreeThrowTeamB(view: View) {
        scoreTeamB += POINTS_FOR_FREE_THROW
        displayForTeamB(scoreTeamB)
    }

    // Reset
    fun reset(view: View) {
        scoreTeamA = 0
        scoreTeamB = 0
        displayForTeamA(scoreTeamA)
        displayForTeamB(scoreTeamB)
    }
}
