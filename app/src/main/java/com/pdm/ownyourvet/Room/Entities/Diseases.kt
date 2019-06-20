package com.pdm.ownyourvet.Room.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "diseases")
data class Diseases(

    @PrimaryKey
    @field:Json(name = "id")
    val id:Long = 0,

    @field:Json(name = "name")
    val name:String,

    @field:Json(name = "animal")
    val animal:String,

    @field:Json(name = "description")
    val description:String

)