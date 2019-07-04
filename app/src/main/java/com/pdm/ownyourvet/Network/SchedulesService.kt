package com.pdm.ownyourvet.Network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.pdm.ownyourvet.BASE_URL
import com.pdm.ownyourvet.Network.Models.schedules.Schedule
import com.pdm.ownyourvet.Network.Models.schedules.ScheduleResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

interface SchedulesService {
    @GET("schedulesof/{id}")
    fun getSchedules(
            @Path("id")id:String
    ): Deferred<Response<ScheduleResponse>>

    @POST("schedules")
    @FormUrlEncoded
    fun saveSchedule(
            @Field("day") day: String,
            @Field("id_user") id_user: String
    ):Deferred<Response<Void>>

    companion object {
        fun getSchedulesService(): SchedulesService = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build().create(SchedulesService::class.java)
    }
}