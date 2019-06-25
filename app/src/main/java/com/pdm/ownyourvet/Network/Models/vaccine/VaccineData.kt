package com.pdm.ownyourvet.Network.Models.vaccine

import com.pdm.ownyourvet.Room.Entities.Vaccine
import com.squareup.moshi.Json

class VaccineData(

        @field:Json(name = "data")
        val data:List<Vaccine>
)