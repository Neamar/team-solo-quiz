package fr.neamar.teamsoloquiz

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Button
import android.widget.TextView
import fr.neamar.teamsoloquiz.adapter.AnswerStatsAdapter
import fr.neamar.teamsoloquiz.adapter.QuestionStats

class QuestionStatsActivity : AppCompatActivity() {
    val TAG = "QuestionStatsActivity"

    lateinit var question: QuestionStats;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_stats)

        if (intent == null || !intent.hasExtra("question")) {
            Log.e(TAG, "No question provided");
        }
        question = intent.getSerializableExtra("question") as QuestionStats;

        Log.i(TAG, "Displaying question stats for " + question.question)

        findViewById<TextView>(R.id.question).text = question.question;

        findViewById<RecyclerView>(R.id.answers).adapter = AnswerStatsAdapter(question.answerStats)


        findViewById<Button>(R.id.leaderboard).setOnClickListener { _ ->
            val i = Intent(this, LeaderboardActivity::class.java);
            startActivity(i);
            finish();
        }
    }

}
