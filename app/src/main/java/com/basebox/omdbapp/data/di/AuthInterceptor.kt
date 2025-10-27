//package com.basebox.omdbapp.data.di
//
//import okhttp3.Interceptor
//import okhttp3.Response
//
//class AuthInterceptor(private val apiKey: String) : Interceptor {
//    override fun intercept(chain: Interceptor.Chain): Response {
//        // Get the original request
//        val originalRequest = chain.request()
//
//        // Get the original URL
//        val originalHttpUrl = originalRequest.url
//
//        // Add the 'apikey' query parameter to the URL
//        val urlWithApiKey = originalHttpUrl.newBuilder()
//            .addQueryParameter("apikey", apiKey)
//            .build()
//
//        // Build the new request with the updated URL
//        val requestBuilder = originalRequest.newBuilder().url(urlWithApiKey)
//        val newRequest = requestBuilder.build()
//
//        // Proceed with the new request
//        return chain.proceed(newRequest)
//    }
//}
