package fr.neamar.teamsoloquiz.adapter;

import org.json.JSONException;
import org.json.JSONObject;

class Score {
    public Score(JSONObject json) throws JSONException {
        this.rank = json.getInt("rank");
        this.score = json.getInt("points");
        this.name = json.getString("name");
    }

    public int rank;
    public int score;
    public String name;

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
