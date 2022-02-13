package com.example.activitytracker.services.json

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class JsonService {
    val gsonClient: Gson = GsonBuilder().create()

    //https://stackoverflow.com/questions/45949584/how-does-the-reified-keyword-in-kotlin-work
    inline fun <reified T> typeToken(): Type = object : TypeToken<T>() {}.type

    inline fun <reified T> convertToObject(jsonString: String): T {
        val type = typeToken<T>()
        return gsonClient.fromJson(jsonString, type)
    }

    inline fun <reified T> convertToString(objectToConvert: T): String {
        return gsonClient.toJson(objectToConvert)
    }
}