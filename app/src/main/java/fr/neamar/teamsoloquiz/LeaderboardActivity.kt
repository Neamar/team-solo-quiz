package fr.neamar.teamsoloquiz

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import fr.neamar.teamsoloquiz.adapter.ScoreAdapter
import kotlinx.android.synthetic.main.content_leaderboard.*
import org.json.JSONArray


class LeaderboardActivity : AppCompatActivity() {
    val TAG = "LeaderboardActivity";

    private inner class DataUpdateReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action == NotificationService.REFRESH_LEADERBOARD) {
                Log.i(TAG, "Refreshed leaderboard");
                loadLeaderboard();
            }
        }
    }

    private var dataUpdateReceiver: DataUpdateReceiver? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)

        loadLeaderboard();
    }

    override fun onResume() {
        if (dataUpdateReceiver == null) dataUpdateReceiver = DataUpdateReceiver()
        val intentFilter = IntentFilter(NotificationService.REFRESH_LEADERBOARD)
        registerReceiver(dataUpdateReceiver, intentFilter)
        super.onResume()
    }

    override fun onPause() {
        if (dataUpdateReceiver != null) unregisterReceiver(dataUpdateReceiver);
        super.onPause()
    }

    fun loadLeaderboard() {
        val url = getString(R.string.API_URL) + "/leaderboard"
        val queue = Volley.newRequestQueue(this)

// Request a string response from the provided URL.
        val stringRequest = JsonArrayRequest(Request.Method.GET, url, null,
                Response.Listener<JSONArray> { response ->
                    leaderboard.adapter = ScoreAdapter(response)
                },
                Response.ErrorListener { Toast.makeText(this, "Unable to load leaderboard :(", Toast.LENGTH_LONG).show() })

// Add the request to the RequestQueue.
        queue.add(stringRequest)
    }

}
