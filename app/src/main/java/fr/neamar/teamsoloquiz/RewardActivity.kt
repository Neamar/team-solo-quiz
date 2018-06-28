package fr.neamar.teamsoloquiz

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class RewardActivity : AppCompatActivity() {
    var score : String = "0"
    var rank : String = "1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reward)

        if(intent != null) {
            score = intent.getStringExtra("score")
            rank = intent.getStringExtra("rank");
        }

        findViewById<TextView>(R.id.rank).text = "You placed #" + rank + " in this challenge and earned 1350 Riot Points.";

        findViewById<Button>(R.id.leaderboard).setOnClickListener { _ ->
            val i = Intent(this, LeaderboardActivity::class.java);
            startActivity(i);
        }

    }

}
