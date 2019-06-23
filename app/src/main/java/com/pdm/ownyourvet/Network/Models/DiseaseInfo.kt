package com.pdm.ownyourvet.Network.Models

import com.pdm.ownyourvet.Room.Entities.Diseases
import com.squareup.moshi.Json

class DiseaseInfo(
    @field:Json(name = "data")
    val data:List<Diseases>,
    @field:Json(name = "next_page_url")
    val nextPage:String,
    @field:Json(name = "prev_page_url")
    val prevPage:String
)