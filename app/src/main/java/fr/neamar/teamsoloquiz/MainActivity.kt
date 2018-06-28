package fr.neamar.teamsoloquiz

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import fr.neamar.teamsoloquiz.R.string.API_URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.logIn).setOnClickListener { v ->
            val queue = Volley.newRequestQueue(this)
            val url = getString(API_URL) + "/username"

// Request a string response from the provided URL.
            val stringRequest = StringRequest(Request.Method.POST, url,
                    Response.Listener<String> { response ->
                        val i =  Intent(this, LeaderboardActivity::class.java)
                        startActivity(i)
                    },
                    Response.ErrorListener { Toast.makeText(this, "Unable to register :(", Toast.LENGTH_LONG).show() })

// Add the request to the RequestQueue.
            queue.add(stringRequest)
        }
    }
}
