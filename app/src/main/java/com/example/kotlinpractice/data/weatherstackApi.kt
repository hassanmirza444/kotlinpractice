package com.example.kotlinpractice.data

import com.example.kotlinpractice.data.network.response.CurrentWeatherResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


const val API_KEY = "15254e45ed17aa357102145dbf1571ac"

///http://api.weatherstack.com/current?access_key=15254e45ed17aa357102145dbf1571ac&query=NewYork

interface weatherstackApi {


    @GET("current")
   fun getCurrentWeather(@Query("query")location:String /*, @Query("language")lang:String ="en"*/)
            : Deferred<CurrentWeatherResponse>


    companion object{

        operator fun invoke ():weatherstackApi {

            val  requestInterceptor = Interceptor{ chain ->

                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("access_key",API_KEY)
                    .build()

                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://api.weatherstack.com/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(weatherstackApi::class.java)
        }
    }
}