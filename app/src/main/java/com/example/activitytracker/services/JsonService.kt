package com.example.activitytracker.services

import com.example.activitytracker.models.ActivityResponse
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class JsonService {
    val gsonClient: Gson = GsonBuilder().create()

//     fun convertToJson(jsonString: String): ActivityResponse {
//        return gsonClient.fromJson(jsonString, ActivityResponse::class.java)
//    }

    //https://stackoverflow.com/questions/45949584/how-does-the-reified-keyword-in-kotlin-work
    inline fun <reified T> typeToken(): Type = object : TypeToken<T>() {}.type

    inline fun <reified T> convertToJson(jsonString: String): T {
        val type = typeToken<T>()
        return gsonClient.fromJson(jsonString, type)
    }





}