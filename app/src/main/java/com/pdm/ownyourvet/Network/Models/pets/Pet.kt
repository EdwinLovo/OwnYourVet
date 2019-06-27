package com.pdm.ownyourvet.Network.Models.pets

import com.squareup.moshi.Json

class Pet (
        @field:Json(name ="id")
        val id:Long,
        @field:Json(name ="name")
        val name:String,
        @field:Json(name ="race")
        val race:Race

)