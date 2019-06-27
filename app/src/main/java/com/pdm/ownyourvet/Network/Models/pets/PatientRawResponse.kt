package com.pdm.ownyourvet.Network.Models.pets

import com.squareup.moshi.Json

class PatientRawResponse (
        @field:Json(name ="data")
        val data:PatientRaw
)