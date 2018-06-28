package fr.neamar.teamsoloquiz.adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;


public class AnsweredQuestion implements Serializable {
    public String question;
    public ArrayList<AnswerStats> answers = new ArrayList<>();


    public AnsweredQuestion(JSONObject json) throws JSONException {
        question = json.getString("question");

        JSONArray jsonAnswers = json.getJSONArray("answers");
        for (int i = 0; i < jsonAnswers.length(); i++) {
            answers.add(new AnswerStats(jsonAnswers.getJSONObject(i)));
        }
    }
}