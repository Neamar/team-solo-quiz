package fr.neamar.teamsoloquiz

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView
import fr.neamar.teamsoloquiz.adapter.Question
import fr.neamar.teamsoloquiz.adapter.SimpleAnswerAdapter
import kotlinx.android.synthetic.main.activity_question.*
import java.util.*

class QuestionActivity : AppCompatActivity() {
    val TAG = "QuestionActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (intent == null || !intent.hasExtra("question")) {
            Log.e(TAG, "No question provided");
        }

        val question: Question = intent.getSerializableExtra("question") as Question;

        Log.i(TAG, "Displaying question " + question.question)

        findViewById<TextView>(R.id.question).text = question.question;

        findViewById<RecyclerView>(R.id.answers).adapter = SimpleAnswerAdapter(question.answers, this, question.id)

        val progressBar = findViewById<ProgressBar>(R.id.progressBar);


        var remainingTime = question.timeout * 1000;
        progressBar.max = remainingTime;


        //Declare the timer
        val t = Timer()
        //Set the schedule function and rate
        t.scheduleAtFixedRate(object : TimerTask() {

            override fun run() {
                progressBar.progress = remainingTime;
                remainingTime -= 100;
            }

        },
                //Set how long before to start calling the TimerTask (in milliseconds)
                0,
                //Set the amount of time between each execution (in milliseconds)
                100)

    }
}
