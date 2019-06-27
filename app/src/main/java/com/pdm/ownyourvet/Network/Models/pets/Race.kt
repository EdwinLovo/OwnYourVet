package com.pdm.ownyourvet.Network.Models.pets

import com.pdm.ownyourvet.Room.Entities.Species
import com.squareup.moshi.Json

class Race(
        @field:Json(name = "name")
        val name:String,
        @field:Json(name = "id")
        val id:Long,
        @field:Json(name = "specie")
        val specie:Species
)