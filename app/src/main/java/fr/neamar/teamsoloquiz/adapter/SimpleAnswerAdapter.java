package fr.neamar.teamsoloquiz.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;

import java.util.ArrayList;

import fr.neamar.teamsoloquiz.R;

public class SimpleAnswerAdapter extends RecyclerView.Adapter<SimpleAnswerHolder> {
    private ArrayList<String> answers;
    private int selectedItem = -1;

    public SimpleAnswerAdapter(ArrayList<String> answers) throws JSONException {
        this.answers = answers;
        updateAnswers(answers);
    }

    @NonNull
    @Override
    public SimpleAnswerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.item_answer_simple, parent, false);

        return new SimpleAnswerHolder(view, this);
    }

    @Override
    public void onBindViewHolder(SimpleAnswerHolder holder, int position) {
        holder.bindSimpleAnswer(answers.get(position), position, selectedItem);
    }

    @Override
    public long getItemId(int position) {
        return answers.get(position).hashCode();
    }

    @Override
    public int getItemCount() {
        return answers.size();
    }

    private void updateAnswers(ArrayList<String> answers) {
        this.answers = answers;
        notifyDataSetChanged();
    }

    public void setSelectedItem(int position) {
        selectedItem = position;
        notifyDataSetChanged();
    }

    public int getSelectedItem() {
        return selectedItem;
    }
}
