package fr.neamar.teamsoloquiz

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val usernameText = findViewById<EditText>(R.id.username);
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        findViewById<Button>(R.id.logIn).setOnClickListener { _ ->
            val userName = usernameText.text.toString();
            sharedPreferences.edit().putString("username", userName).apply()

            register(userName);
        }

        if(sharedPreferences.contains("username")) {
           register(sharedPreferences.getString("username", ""));
           finish();
        }
    }

    private fun register(username: String) {
        Log.i(TAG, "Registering as user" + username);
        val intent = Intent(this, RegistrationIntentService::class.java)
        startService(intent)

        val i = Intent(this, LeaderboardActivity::class.java)
        startActivity(i)
    }
}
