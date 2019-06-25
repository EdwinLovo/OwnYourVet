package com.pdm.ownyourvet.Network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.pdm.ownyourvet.BASE_URL
import com.pdm.ownyourvet.Network.Models.DiseaseInfo
import com.pdm.ownyourvet.Network.Models.DiseasesData
import com.pdm.ownyourvet.Network.Models.SpeciesData
import com.pdm.ownyourvet.Room.Entities.Diseases
import com.pdm.ownyourvet.Room.Entities.Species
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface DiseasesService {

    @GET("diseases")
    fun getDiseases(
        @Query("page")
        page:String? = "1"
    ):Deferred<Response<DiseasesData>>

    @GET("species")
    fun getSpecies():Deferred<Response<SpeciesData>>

    companion object {
        fun getDiseasesService():DiseasesService= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build().create(DiseasesService::class.java)
    }

}