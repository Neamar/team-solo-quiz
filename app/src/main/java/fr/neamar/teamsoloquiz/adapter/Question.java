package fr.neamar.teamsoloquiz.adapter;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class Question implements Serializable {
    public String question;
    public int id;
    public int timeout;
    public ArrayList<String> answers = new ArrayList<>();


    public Question(Map<String, String> data) throws JSONException {
        question = data.get("question");
        timeout = Integer.parseInt(data.get("timeout"));
        id = Integer.parseInt(data.get("question_id"));

        JSONArray jsonAnswers = new JSONArray(data.get("answers"));
        for(int i = 0; i < jsonAnswers.length(); i++) {
            answers.add(jsonAnswers.getString(i));
        }
    }
}
