package com.example.myfavmemes

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest


import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val shareBtn : Button = findViewById<Button>(R.id.shareBtn)
        val nextBtn : Button = findViewById<Button>(R.id.nextBtn)
        loadmeme()
        nextBtn.setOnClickListener {
            loadmeme()
        }

    }

    private fun loadmeme(){
        val subredditTxt : TextView = findViewById(R.id.subredditTxt)
        val progressBar : ProgressBar = findViewById(R.id.progressBar)
        progressBar.visibility= View.VISIBLE
        // Instantiate the RequestQueue.

        val queue = Volley.newRequestQueue(this)
        val url = "https://meme-api.herokuapp.com/gimme"

        // Request a jsonObject response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            { response ->
                val url = response.getString("url")
                val subreddit =response.getString("subreddit")
                Glide.with(this).load(url).listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.visibility = View.GONE
                        return false
                    }
                }).into(findViewById(R.id.memeImg))
                subredditTxt.text = subreddit
            },
            {

            }
        )

        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)
    }
}