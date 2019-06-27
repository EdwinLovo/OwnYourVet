package com.pdm.ownyourvet.Network.Models.users

import com.squareup.moshi.Json

class ClientRawResponse(
        @field:Json(name = "data")
        val data:ClientPatientRaw
)