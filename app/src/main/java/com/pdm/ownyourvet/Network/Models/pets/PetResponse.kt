package com.pdm.ownyourvet.Network.Models.pets

import com.squareup.moshi.Json

class PetResponse (
        @field:Json(name ="data")
        val data:List<Patient>

)