package com.example.activitytracker.services.network

import com.example.activitytracker.services.results.NetworkResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.*
import java.io.IOException
import java.lang.Exception

@ExperimentalCoroutinesApi
class NetworkService : INetworkService {

    private fun getNetworkResponse(url: String, callback: Callback): Call {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()
        val call = client.newCall(request)
        call.enqueue(callback)
        return call
    }


    override suspend fun makeNetworkRequest(url: String): NetworkResult<String> {
        return suspendCancellableCoroutine { continuation ->
            getNetworkResponse(url, object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    val outcome = NetworkResult.Error(e)
                    continuation.resume(outcome, null)
                }

                override fun onResponse(call: Call, response: Response) {
                    response.body?.string()?.also {
                        lateinit var outcome: NetworkResult<String>
                        if(it.contains("error")){
                            outcome   = NetworkResult.Error(Exception(it))
                        }else{
                            outcome   = NetworkResult.Success(it)
                        }
                        continuation.resume(outcome, null)
                    }
                }
            })
        }


    }


}