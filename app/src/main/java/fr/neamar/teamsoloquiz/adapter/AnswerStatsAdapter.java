package fr.neamar.teamsoloquiz.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;

import fr.neamar.teamsoloquiz.R;

public class AnswerStatsAdapter extends RecyclerView.Adapter<AnswerStatsHolder> {
    private static final String TAG = "SimpleAnswerAdapter";
    private QuestionStats questionStats;

    public AnswerStatsAdapter(QuestionStats questionStats) throws JSONException {
        this.questionStats = questionStats;
        updateAnswers(questionStats);
    }

    @NonNull
    @Override
    public AnswerStatsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.item_answer_stats, parent, false);

        return new AnswerStatsHolder(view, questionStats.numberOfResults);
    }

    @Override
    public void onBindViewHolder(AnswerStatsHolder holder, int position) {
        holder.bindAnswerStats(questionStats.answers.get(position));
    }

    @Override
    public long getItemId(int position) {
        return questionStats.answers.get(position).hashCode();
    }

    @Override
    public int getItemCount() {
        return questionStats.answers.size();
    }

    private void updateAnswers(QuestionStats questionStats) {
        this.questionStats = questionStats;
        notifyDataSetChanged();
    }
}
