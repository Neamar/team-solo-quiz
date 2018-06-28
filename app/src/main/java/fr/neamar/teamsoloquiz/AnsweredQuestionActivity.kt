package fr.neamar.teamsoloquiz

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_answered_question.*

class AnsweredQuestionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answered_question)
        setSupportActionBar(toolbar)

    }

}
