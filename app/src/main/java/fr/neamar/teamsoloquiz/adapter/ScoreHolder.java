package fr.neamar.teamsoloquiz.adapter;

import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import fr.neamar.teamsoloquiz.R;

class ScoreHolder extends RecyclerView.ViewHolder {
    private final TextView rankView;
    private final TextView nameView;
    private final TextView scoreView;
    private final String myName;

    private final int colorMyself;
    private final int colorOthers;

    public ScoreHolder(View v) {
        super(v);

        rankView = v.findViewById(R.id.rank);
        nameView = v.findViewById(R.id.name);
        scoreView = v.findViewById(R.id.score);

        myName = PreferenceManager.getDefaultSharedPreferences(v.getContext()).getString("username", "");

        colorMyself = nameView.getContext().getResources().getColor(R.color.colorLeaderboardMe);
        colorOthers = nameView.getContext().getResources().getColor(R.color.colorLeaderboardOthers);
    }

    public void bindScore(Score score) {
        rankView.setText(Integer.toString(score.rank));
        nameView.setText(score.name);
        scoreView.setText(Integer.toString(score.score));

        if (myName.equals(score.name)) {
            rankView.setTextColor(colorMyself);
            scoreView.setTextColor(colorMyself);
            nameView.setTextColor(colorMyself);
        } else {
            rankView.setTextColor(colorOthers);
            scoreView.setTextColor(colorOthers);
            nameView.setTextColor(colorOthers);
        }
    }

}
