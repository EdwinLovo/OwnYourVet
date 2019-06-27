package com.pdm.ownyourvet.Network.Models.users

import com.squareup.moshi.Json

class ClientPatientRaw (
        @field:Json(name = "id")
        val id:Long,
        @field:Json(name = "client_id")
        val client_id:Long,
        @field:Json(name = "patient_id")
        val patient_id:Long
)