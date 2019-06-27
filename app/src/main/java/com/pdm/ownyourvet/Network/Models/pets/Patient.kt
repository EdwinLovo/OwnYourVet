package com.pdm.ownyourvet.Network.Models.pets

import com.squareup.moshi.Json

class Patient (
        @field:Json(name ="patient")
        val patient:Pet
)