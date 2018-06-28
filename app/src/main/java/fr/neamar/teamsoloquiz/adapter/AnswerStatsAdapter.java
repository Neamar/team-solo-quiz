package fr.neamar.teamsoloquiz.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;

import java.util.ArrayList;

import fr.neamar.teamsoloquiz.R;

public class AnswerStatsAdapter extends RecyclerView.Adapter<AnswerStatsHolder> {
    private static final String TAG = "SimpleAnswerAdapter";
    private ArrayList<AnswerStats> answers;


    public AnswerStatsAdapter(ArrayList<AnswerStats> answers) throws JSONException {
        this.answers = answers;
        updateAnswers(answers);
    }

    @NonNull
    @Override
    public AnswerStatsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.item_answer_stats, parent, false);

        return new AnswerStatsHolder(view);
    }

    @Override
    public void onBindViewHolder(AnswerStatsHolder holder, int position) {
        holder.bindAnswerStats(answers.get(position));
    }

    @Override
    public long getItemId(int position) {
        return answers.get(position).hashCode();
    }

    @Override
    public int getItemCount() {
        return answers.size();
    }

    private void updateAnswers(ArrayList<AnswerStats> answers) {
        this.answers = answers;
        notifyDataSetChanged();
    }
}
