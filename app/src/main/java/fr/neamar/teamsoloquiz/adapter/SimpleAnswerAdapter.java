package fr.neamar.teamsoloquiz.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import fr.neamar.teamsoloquiz.R;

public class SimpleAnswerAdapter extends RecyclerView.Adapter<SimpleAnswerHolder> {
    private static final String TAG = "SimpleAnswerAdapter";
    private ArrayList<String> answers;
    private int selectedItem = -1;
    private int questionId;

    private Context context;

    public SimpleAnswerAdapter(ArrayList<String> answers, Context context, int questionId) throws JSONException {
        this.context = context;
        this.questionId = questionId;
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
        if(selectedItem == -1) {
            selectedItem = position;
            sendAnswerToServer();
            notifyDataSetChanged();
        }
    }

    public int getSelectedItem() {
        return selectedItem;
    }

    private void sendAnswerToServer() {
        // Instantiate the RequestQueue.
        final RequestQueue queue = Volley.newRequestQueue(context);

        try {
            JSONObject payload = new JSONObject();
            payload.put("answer", selectedItem);
            payload.put("id", FirebaseInstanceId.getInstance().getToken());
            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, context.getString(R.string.API_URL) + "/questions/" + questionId,
                    payload,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.i(TAG, "Answer sent to server for question " + questionId);
                            queue.stop();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, error.toString());

                    try {
                        String responseBody = new String(error.networkResponse.data, "utf-8");
                        Log.i(TAG, responseBody);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (NullPointerException e) {
                    }
                }
            });

            queue.add(jsonRequest);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
