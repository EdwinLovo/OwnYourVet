package com.pdm.ownyourvet.Network.Models

import com.pdm.ownyourvet.Room.Entities.Species
import com.squareup.moshi.Json

class SpeciesData (
    @field:Json(name = "data")
    val data:List<Species>
)