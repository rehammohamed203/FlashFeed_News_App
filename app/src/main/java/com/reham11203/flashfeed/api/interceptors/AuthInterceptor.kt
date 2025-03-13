package com.reham11203.flashfeed.api.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val API_KEY = "8843e314239042f99415f4420a35eebe"
        val newRequestBuilder = chain.request().newBuilder()
        newRequestBuilder.addHeader("X-Api-Key", API_KEY)
        return chain.proceed(newRequestBuilder.build())
    }
}