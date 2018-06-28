package fr.neamar.teamsoloquiz.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import fr.neamar.teamsoloquiz.R;

class ScoreHolder extends RecyclerView.ViewHolder {
    private final TextView rankView;
    private final TextView nameView;
    private final TextView scoreView;

    public ScoreHolder(View v) {
        super(v);

        rankView = v.findViewById(R.id.rank);
        nameView = v.findViewById(R.id.name);
        scoreView = v.findViewById(R.id.score);
    }

    public void bindScore(Score score) {
        rankView.setText(Integer.toString(score.rank));
        nameView.setText(score.name);
        scoreView.setText(Integer.toString(score.score));
    }

}
