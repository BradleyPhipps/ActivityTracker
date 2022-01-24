package com.example.activitytracker.services

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.*
import java.io.IOException

class NetworkService {

    private fun getNetworkResponse(url: String, callback: Callback): Call {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()
        val call = client.newCall(request)
        call.enqueue(callback)
        return call
    }

    @ExperimentalCoroutinesApi
    suspend fun getNetworkResponse(url: String): String {
        return suspendCancellableCoroutine { continuation ->
            getNetworkResponse(url, object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    val outcome = Result.Error(e)
                    continuation.resume(outcome.toString(), null)
                }

                override fun onResponse(call: Call, response: Response) {
                    response.body?.string()?.also {
                        val outcome = it
                        continuation.resume(outcome, null)
                    }
                }
            })
        }


    }

    sealed class Result<out R> {
        data class Success<out T>(val data: T) : Result<T>()
        data class Error(val exception: Exception) : Result<Nothing>()
    }
}