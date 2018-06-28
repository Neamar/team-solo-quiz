package fr.neamar.teamsoloquiz.adapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

class AnswerStats implements Serializable {
    public String answer;
    public int count;

    AnswerStats(JSONObject json) throws JSONException {
        answer = json.getString("answer");
        count = json.getInt("count");
    }
}
