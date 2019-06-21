package com.pdm.ownyourvet.Network.Models

import com.pdm.ownyourvet.Room.Entities.Diseases
import com.squareup.moshi.Json

data class DiseasesData(

    @field:Json(name = "data")
    val data:List<Diseases>
)