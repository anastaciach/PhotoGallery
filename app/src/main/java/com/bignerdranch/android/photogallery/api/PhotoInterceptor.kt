package com.bignerdranch.android.photogallery.api

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

private const val API_KEY = "0b1a22f0e22b77664ea6e1432bfe864f"
class PhotoInterceptor : Interceptor {
    override fun intercept(chain:
                           Interceptor.Chain): Response {
        val originalRequest: Request =
            chain.request()
        val newUrl: HttpUrl =
            originalRequest.url().newBuilder()
                .addQueryParameter("api_key", API_KEY)
                .addQueryParameter("format", "json")
                .addQueryParameter("nojsoncallb ack", "1")
                    .addQueryParameter("extras",
                        "url_s")
                    .addQueryParameter("safesearch"
                        , "1")
                    .build()
                    val newRequest: Request =
        originalRequest.newBuilder()
            .url(newUrl)
            .build()
        return chain.proceed(newRequest)
    }
}