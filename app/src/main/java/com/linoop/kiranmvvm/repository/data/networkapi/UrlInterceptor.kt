package com.linoop.kiranmvvm.repository.data.networkapi

import android.util.Base64
import com.linoop.kiranmvvm.repository.data.localdatabse.SharedPrefManager
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response
import java.io.UnsupportedEncodingException

class UrlInterceptor(private val preferences: SharedPrefManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        /*preferences.getBaseUrl()?.let { baseUrl ->
          val newUrl = HttpUrl.parse(
                chain.request().url().toString().replace("https://localhost/", baseUrl)
            ) ?: chain.request().url()
            request.url(newUrl)
        }*/
       // request.header("Authorization", "Bearer ${preferences.getToken()}")
        //request.header("Authorization", getAuthToken())//preferences.getToken())
        return chain.proceed(request.build())
    }

    private fun getAuthToken(): String {
        var data = ByteArray(0)
        try {
            data = ("API_USER_NAME : API_PASSWORD").toByteArray(charset("UTF-8"))
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
        return "Basic " + Base64.encodeToString(data, Base64.NO_WRAP)
    }
}