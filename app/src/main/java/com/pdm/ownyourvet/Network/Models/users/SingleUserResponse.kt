package com.pdm.ownyourvet.Network.Models.users

import com.pdm.ownyourvet.Room.Entities.User
import com.squareup.moshi.Json

class SingleUserResponse (
        @field:Json(name = "data")
        val data:User
)