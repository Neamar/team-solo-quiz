package fr.neamar.teamsoloquiz;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;

import java.util.Map;

import fr.neamar.teamsoloquiz.adapter.UnansweredQuestion;

public class NotificationService extends FirebaseMessagingService {
    public static final String REFRESH_LEADERBOARD = "refreshLeaderboard";

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
                case "leaderboard_update":
                    onLeaderboardUpdated(remoteMessage);
                    return;
                case "game_ended":
                    onGameEnded(remoteMessage);
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

    private void onLeaderboardUpdated(RemoteMessage remoteMessage) throws JSONException {
        Log.i(TAG, "Leaderboard was updated, triggering refresh");
        sendBroadcast(new Intent(NotificationService.REFRESH_LEADERBOARD));
    }

    private void onGameEnded(RemoteMessage remoteMessage) throws JSONException {
        Log.i(TAG, "Game was ended");
        Map<String, String> d = remoteMessage.getData();
        Intent gameEnded = new Intent(this, RewardActivity.class);
        gameEnded.putExtra("score", d.get("score"));
        gameEnded.putExtra("rank", d.get("rank"));

        startActivity(gameEnded);
    }


}
