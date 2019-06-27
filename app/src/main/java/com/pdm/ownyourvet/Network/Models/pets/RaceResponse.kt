package com.pdm.ownyourvet.Network.Models.pets

import com.pdm.ownyourvet.Room.Entities.Species
import com.squareup.moshi.Json

class RaceResponse (
        @field:Json(name = "data")
        val data:List<Race>
)