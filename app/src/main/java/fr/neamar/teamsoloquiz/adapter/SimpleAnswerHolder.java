package fr.neamar.teamsoloquiz.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import fr.neamar.teamsoloquiz.R;

class SimpleAnswerHolder extends RecyclerView.ViewHolder {
    private final TextView answerButton;

    public SimpleAnswerHolder(View v) {
        super(v);

        answerButton = v.findViewById(R.id.answer);
    }

    public void bindSimpleAnswer(String answer) {
        answerButton.setText(answer);
    }

}
