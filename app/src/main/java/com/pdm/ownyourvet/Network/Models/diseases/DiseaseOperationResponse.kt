package com.pdm.ownyourvet.Network.Models.diseases

import com.pdm.ownyourvet.Room.Entities.Diseases
import com.squareup.moshi.Json

class DiseaseOperationResponse (
        @field:Json(name = "data")
        val data:Diseases
)