package com.pdm.ownyourvet.Network.Models.pets

import com.squareup.moshi.Json

class PatientRaw (
        @field:Json(name ="id")
        val id:Long,
        @field:Json(name ="name")
        val name:String,
        @field:Json(name ="race_id")
        val race_id:String
)