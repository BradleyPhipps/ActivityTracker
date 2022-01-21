package com.example.activitytracker.services

import com.example.activitytracker.models.ActivityResponse
import com.google.gson.Gson
import com.google.gson.GsonBuilder

class JsonService {
    val gsonClient: Gson = GsonBuilder().create()

     fun convertToJson(jsonString: String): ActivityResponse {
        return gsonClient.fromJson(jsonString, ActivityResponse::class.java)
    }
}