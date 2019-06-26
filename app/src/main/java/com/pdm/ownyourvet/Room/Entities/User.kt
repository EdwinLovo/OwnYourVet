package com.pdm.ownyourvet.Room.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "users")
data class User(
        @PrimaryKey
        @field:Json(name = "id")
        val id:String,
        @field:Json(name = "email")
        val email:String,
        @field:Json(name = "user_type")
        val userType:String
){
    @field:Json(name = "names")
    var names:String = "none"
    @field:Json(name = "direction")
    var direction:String = "none"
}