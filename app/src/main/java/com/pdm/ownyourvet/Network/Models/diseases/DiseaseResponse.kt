package com.pdm.ownyourvet.Network.Models.diseases

import com.pdm.ownyourvet.Room.Entities.Diseases
import com.squareup.moshi.Json

class DiseaseResponse(
        @field:Json(name = "info")
        val data: List<Diseases>
)