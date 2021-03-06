package fr.neamar.teamsoloquiz.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import fr.neamar.teamsoloquiz.R;

class AnswerHolder extends RecyclerView.ViewHolder {
    private final TextView answerButton;
    private int position;

    public AnswerHolder(View v, final AnswerAdapter adapter) {
        super(v);

        answerButton = v.findViewById(R.id.answer);

        answerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.setSelectedItem(position);
            }
        });
    }

    public void bindSimpleAnswer(String answer, int position, int selectedPosition) {
        answerButton.setText(answer);

        if(position == selectedPosition) {
            answerButton.setBackgroundColor(Color.parseColor("#D1363A"));
            answerButton.setTextColor(Color.parseColor("#FFFFFF"));
        }
        else {
            answerButton.setBackgroundColor(Color.parseColor("#EFF2F6"));
            answerButton.setTextColor(Color.parseColor("#1F2021"));
        }

        this.position = position;
    }

}
