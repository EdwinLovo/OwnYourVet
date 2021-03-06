package com.pdm.ownyourvet.Network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.pdm.ownyourvet.BASE_URL
import com.pdm.ownyourvet.Network.Models.vaccine.VaccineData
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface VaccineService {

    @GET("vaccines")
    fun getVaccinations():Deferred<Response<VaccineData>>

    companion object {
        fun getVaccinationsService():VaccineService= Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build().create(VaccineService::class.java)
    }
}