package com.kotasprojects.android.spacexlaunch.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

enum class SpaceXApiFilter(val value: String) { SHOW_UPCOMING("upcoming"), SHOW_PAST("past"), SHOW_ALL("") }

private const val BASE_URL = "https://api.spacexdata.com/v3/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface SpaceXApiService {
    @GET("launches/{filter}")
    fun getProperties(@Path("filter") type: String):
            Deferred<List<SpaceXLunchProperty>>
}

object SpaceXApi {
    val retrofitService: SpaceXApiService by lazy {
        retrofit.create(SpaceXApiService::class.java)
    }
}