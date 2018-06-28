package fr.neamar.teamsoloquiz.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import fr.neamar.teamsoloquiz.R;

public class AnswerStatsHolder extends RecyclerView.ViewHolder {
    private final TextView answerButton;
    private final ProgressBar percentageProgress;

    public AnswerStatsHolder(View v, int count) {
        super(v);

        answerButton = v.findViewById(R.id.answer);
        percentageProgress = v.findViewById(R.id.percentage);
        percentageProgress.setMax(count);
    }

    public void bindAnswerStats(AnswerStats answerStats) {
        answerButton.setText(answerStats.answer);
        percentageProgress.setProgress(answerStats.count);
    }

}
