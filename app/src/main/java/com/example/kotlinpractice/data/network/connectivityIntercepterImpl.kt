package com.example.kotlinpractice.data.network

import android.content.Context
import android.net.ConnectivityManager
import com.example.kotlinpractice.internal.NetworkException
import okhttp3.Interceptor
import okhttp3.Response

class connectivityIntercepterImpl(context: Context) : connectivityIntercepter {

    private val appContext: Context = context
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isOnlinne())
            throw NetworkException()
        return chain.proceed(chain.request())
    }

    private fun isOnlinne(): Boolean {
        val connectivityManager =
            appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected

    }


}