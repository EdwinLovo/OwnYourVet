package com.pdm.ownyourvet.Room.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "vaccine")
data class Vaccine(

    @PrimaryKey
    @field:Json(name = "id")
    val id:Long,

    @field:Json(name = "name")
    val name:String,

    @field:Json(name = "information")
    val information:String,

    @field:Json(name = "specie_id")
    val specie_id:Long,

    @field:Json(name = "weeks")
    val weeks:Long,

    @field:Json(name = "estimated_date")
    val estimated_date:String,

    @field:Json(name = "image")
    val image:String
)