package fr.neamar.teamsoloquiz

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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

        findViewById<TextView>(R.id.score).text = score;
        findViewById<TextView>(R.id.rank).text = rank;
    }

}
