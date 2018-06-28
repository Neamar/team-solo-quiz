package fr.neamar.teamsoloquiz.adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class QuestionStats implements Serializable {
    public String question;
    public ArrayList<AnswerStats> answerStats;

    public QuestionStats(JSONObject json) throws JSONException {
        this.question = json.getString("question");
        this.answerStats = new ArrayList<>();

        JSONArray jsonAnswers = json.getJSONArray("answers");
        for(int i = 0; i < jsonAnswers.length(); i++) {
            answerStats.add(new AnswerStats(jsonAnswers.getJSONObject(i)));
        }
    }
}
