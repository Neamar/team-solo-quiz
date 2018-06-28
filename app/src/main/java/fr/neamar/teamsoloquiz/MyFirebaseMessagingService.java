package fr.neamar.teamsoloquiz;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;

import java.util.Map;

import fr.neamar.teamsoloquiz.adapter.UnansweredQuestion;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Map<String, String> data = remoteMessage.getData();

        String type = data.containsKey("type") ? data.get("type") : "unknown";

        try {
            switch (type) {
                case "new_question":
                    onNewQuestion(remoteMessage);
                    return;
                default:
                    Log.e("WTF", "Received unknown message! Type=" + type);
            }
        } catch(JSONException e) {
            e.printStackTrace();
        }
    }

    private void onNewQuestion(RemoteMessage remoteMessage) throws JSONException {
        Log.i(TAG, "Received a new question");
        UnansweredQuestion question = new UnansweredQuestion(remoteMessage.getData());

        Intent questionIntent = new Intent(this, QuestionActivity.class);
        questionIntent.putExtra("question", question);
        startActivity(questionIntent);
    }


}
