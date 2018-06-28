package fr.neamar.teamsoloquiz

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.TextView
import fr.neamar.teamsoloquiz.adapter.Question
import fr.neamar.teamsoloquiz.adapter.SimpleAnswerAdapter
import kotlinx.android.synthetic.main.activity_question.*

class QuestionActivity : AppCompatActivity() {
    val TAG = "QuestionActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (intent == null ||!intent.hasExtra("question")) {
            Log.e(TAG, "No question provided");
        }

        val question : Question = intent.getSerializableExtra("question") as Question;

        Log.i(TAG, "Displaying question " + question.question)

        findViewById<TextView>(R.id.question).text = question.question;

        findViewById<RecyclerView>(R.id.answers).adapter = SimpleAnswerAdapter(question.answers)
    }
}
