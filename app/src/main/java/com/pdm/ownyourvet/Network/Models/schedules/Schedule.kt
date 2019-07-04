package com.pdm.ownyourvet.Network.Models.schedules

import com.pdm.ownyourvet.Room.Entities.User
import com.squareup.moshi.Json

class Schedule (
        @field:Json(name = "id")
        val id:Long,
        @field:Json(name = "day")
        val day:String,
        @field:Json(name = "user")
        val user:User
)