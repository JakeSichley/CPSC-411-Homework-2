package com.example.cpsc411homework2


import android.util.Log
import com.loopj.android.http.AsyncHttpResponseHandler
import com.google.gson.Gson
import cz.msebera.android.httpclient.Header
import cz.msebera.android.httpclient.entity.StringEntity
import com.loopj.android.http.AsyncHttpClient


class ClaimService (val context: MainActivity)
{
    companion object
    {
        private var claimService : ClaimService? = null
        fun getInstance(activity: MainActivity) : ClaimService
        {
            if (claimService == null)
            {
                claimService = ClaimService(activity)
            }

            return claimService!!
        }
    }

    inner class addServiceRespHandler : AsyncHttpResponseHandler()
    {
        override fun onSuccess(
            statusCode: Int,
            headers: Array<out Header>?,
            responseBody: ByteArray?
        )
        {
            if (responseBody != null)
            {
                Log.d("Claim Service", "Add Claim Response: ${String(responseBody)}")
            }
        }

        override fun onFailure(
            statusCode: Int,
            headers: Array<out Header>?,
            responseBody: ByteArray?,
            error: Throwable?
        )
        {
            Log.d("Claim Service", "Failed to Add Claim.")
        }

    }

    fun addClaim(claimObject : Claim)
    {
        val client = AsyncHttpClient()
        val requestURL = "http://192.168.0.50:8080/ClaimService/add"
        val claimJsonString = Gson().toJson(claimObject)
        val entity = StringEntity(claimJsonString)

        client.post(context, requestURL, entity, "application/json",
            addServiceRespHandler())
    }
}