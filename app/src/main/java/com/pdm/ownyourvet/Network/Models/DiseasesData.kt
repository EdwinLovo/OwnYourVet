package com.pdm.ownyourvet.Network.Models

import com.squareup.moshi.Json

data class DiseasesData(
    @field:Json(name = "info")
    val info:DiseaseInfo
)