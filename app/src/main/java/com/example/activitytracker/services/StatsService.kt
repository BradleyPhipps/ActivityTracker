package com.example.activitytracker.services

import okhttp3.*
import java.io.IOException

class StatsService {
    /*
    Uses okhttp to sendsendStats("https://bbc.github.io/sport-app-dev-tech-challenge/stats?event=display&data=${System.current a request to the stats endpoint with parameters and data attached to url param
    */
    fun sendStats(url: String) {
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()

        //make new http request on background thread to get json data
        client.newCall(request).enqueue(object : Callback {

            override fun onResponse(call: Call, response: Response) {
                println("stats: $url")
            }

            override fun onFailure(call: Call, e: IOException) {
                println("failed to execute request $e")
            }
        })

    }
}