package com.reham11203.data.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val API_KEY = "8843e314239042f99415f4420a35eebe"
        val newRequestBuilder = chain.request().newBuilder()
        newRequestBuilder.addHeader("X-Api-Key", API_KEY)
        return chain.proceed(newRequestBuilder.build())
    }
}