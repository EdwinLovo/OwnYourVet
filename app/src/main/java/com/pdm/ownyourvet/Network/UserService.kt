package com.pdm.ownyourvet.Network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.pdm.ownyourvet.BASE_URL
import com.pdm.ownyourvet.Network.Models.diseases.DiseaseOperationResponse
import com.pdm.ownyourvet.Network.Models.pets.Pet
import com.pdm.ownyourvet.Network.Models.pets.PetResponse
import com.pdm.ownyourvet.Network.Models.users.SingleUserResponse
import com.pdm.ownyourvet.Network.Models.users.UserResponse
import com.pdm.ownyourvet.Room.Entities.User
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

interface UserService {

    @GET("usersadmins")
    fun getAdmins(): Deferred<Response<UserResponse>>

    @GET("usersnormal")
    fun getClients(): Deferred<Response<UserResponse>>

    @GET("users")
    fun getUsers(): Deferred<Response<UserResponse>>

    @GET("patientsof/{id}")
    fun getPetsOf(
            @Path("id") id: String
    ): Deferred<Response<PetResponse>>

    @GET("users/{id}")
    fun getUserById(
            @Path("id") id: String
    ): Deferred<Response<SingleUserResponse>>
    @POST("users")
    @FormUrlEncoded
    fun saveUser(
            @Field("id") id: String,
            @Field("email") email: String,
            @Field("user_type") user_type: String,
            @Field("names") names: String? = "none",
            @Field("direction") direction: String? = "none"
    ): Deferred<Response<SingleUserResponse>>

    @PUT("users/{id}")
    @FormUrlEncoded
    fun updateUser(
            @Path("id") id: String,
            @Field("email") email: String,
            @Field("user_type") user_type: String,
            @Field("names") names: String? = "none",
            @Field("direction") direction: String? = "none"
    ):Deferred<Response<SingleUserResponse>>

    companion object {
        fun getUserService(): UserService = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build().create(UserService::class.java)
    }
}