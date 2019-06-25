package com.pdm.ownyourvet.Network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.pdm.ownyourvet.BASE_URL
import com.pdm.ownyourvet.Network.Models.DiseasesData
import com.pdm.ownyourvet.Network.Models.SpeciesData
import com.pdm.ownyourvet.Network.Models.diseases.DiseaseOperationResponse
import com.pdm.ownyourvet.Network.Models.diseases.DiseaseResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

interface DiseasesService {

    @GET("diseases")
    fun getDiseases(
            @Query("page")
            page: String? = "1"
    ): Deferred<Response<DiseasesData>>
    @GET("diseasesnp")
    fun getDiseasesNoPaging():Deferred<Response<DiseaseResponse>>


    @POST("diseases")
    @FormUrlEncoded
    fun saveDisease(
            @Field("name") name: String,
            @Field("information") info: String,
            @Field("specie_id") specieId: String
    ):Deferred<Response<DiseaseOperationResponse>>

    @PUT("diseases/{id}")
    @FormUrlEncoded
    fun updateDisease(
            @Path("id")id:String,
            @Field("name") name: String,
            @Field("information") info: String,
            @Field("specie_id") specieId: String
    ):Deferred<Response<DiseaseOperationResponse>>

    @GET("species")
    fun getSpecies(): Deferred<Response<SpeciesData>>

    companion object {
        fun getDiseasesService(): DiseasesService = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build().create(DiseasesService::class.java)
    }

}