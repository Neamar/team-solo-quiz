package fr.neamar.teamsoloquiz.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import fr.neamar.teamsoloquiz.R;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreHolder> {
    private ArrayList<Score> scores;

    public ScoreAdapter(JSONArray jsonScores) throws JSONException {
        updateScores(jsonScores);
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public ScoreHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.item_score, parent, false);

        return new ScoreHolder(view);
    }

    @Override
    public void onBindViewHolder(ScoreHolder holder, int position) {
        holder.bindScore(scores.get(position));
    }

    @Override
    public long getItemId(int position) {
        return scores.get(position).hashCode();
    }

    @Override
    public int getItemCount() {
        return scores.size();
    }

    public void updateScores(JSONArray jsonScores) {
        scores = new ArrayList<>(jsonScores.length());
        for(int i = 0; i < jsonScores.length(); i++) {
            try {
                Score score = new Score(jsonScores.getJSONObject(i));
                scores.add(score);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        notifyDataSetChanged();
    }
}
