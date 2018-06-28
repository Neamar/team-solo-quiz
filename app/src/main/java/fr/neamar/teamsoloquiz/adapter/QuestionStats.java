package fr.neamar.teamsoloquiz.adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class QuestionStats implements Serializable {
    public String question;
    public int numberOfResults = 0;
    public ArrayList<AnswerStats> answers = new ArrayList<>();


    public QuestionStats(JSONObject json) throws JSONException {
        question = json.getString("question");

        JSONArray jsonAnswers = json.getJSONArray("answers");
        for (int i = 0; i < jsonAnswers.length(); i++) {
            AnswerStats answerStats = new AnswerStats(jsonAnswers.getJSONObject(i));
            answers.add(answerStats);
            numberOfResults += answerStats.count;
        }
    }
}
