package fr.neamar.teamsoloquiz

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import fr.neamar.teamsoloquiz.adapter.AnswerAdapter
import fr.neamar.teamsoloquiz.adapter.QuestionStats
import fr.neamar.teamsoloquiz.adapter.UnansweredQuestion
import org.json.JSONException
import java.io.UnsupportedEncodingException
import java.util.*

class QuestionActivity : AppCompatActivity() {
    val TAG = "QuestionActivity"
    lateinit var question: UnansweredQuestion;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_unanswered_question)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (intent == null || !intent.hasExtra("question")) {
            Log.e(TAG, "No question provided");
        }

        question = intent.getSerializableExtra("question") as UnansweredQuestion;

        Log.i(TAG, "Displaying question " + question.question)

        findViewById<TextView>(R.id.question).text = question.question;

        findViewById<RecyclerView>(R.id.answers).adapter = AnswerAdapter(question.answers, this, question.id)

        val progressBar = findViewById<ProgressBar>(R.id.progressBar);


        var remainingTime = question.timeout * 1000;
        progressBar.max = remainingTime;


        //Declare the timer
        val t = Timer()
        //Set the schedule function and rate
        t.scheduleAtFixedRate(object : TimerTask() {

            override fun run() {
                progressBar.progress = remainingTime;
                remainingTime -= 100

                if (remainingTime <= 0) {
                    moveToAnswers();
                    t.cancel();
                }
            }

        },
                //Set how long before to start calling the TimerTask (in milliseconds)
                0,
                //Set the amount of time between each execution (in milliseconds)
                100)

    }

    fun moveToAnswers() {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)

        try {
            Log.e(TAG, "Downloading stats");
            val jsonRequest = JsonObjectRequest(Request.Method.GET, getString(R.string.API_URL) + "/questions/${question.id}/stats",
                    null,
                    Response.Listener {
                        Log.i(TAG, "Retrieved stats for question ${question.id}")

                        val questionStats = QuestionStats(it);

                        val i = Intent(this, QuestionStatsActivity::class.java);
                        i.putExtra("question", questionStats);
                        startActivity(i);

                        queue.stop()

                    }, Response.ErrorListener { error ->
                Log.e(TAG, error.toString())

                try {
                    val responseBody = String(error.networkResponse.data)
                    Log.i(TAG, responseBody)
                } catch (e: UnsupportedEncodingException) {
                    e.printStackTrace()
                } catch (e: NullPointerException) {
                }
            })

            queue.add(jsonRequest)

        } catch (e: JSONException) {
            e.printStackTrace()
        }

    }
}
