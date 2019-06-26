package com.pdm.ownyourvet.Network.Models.users

import com.pdm.ownyourvet.Room.Entities.User
import com.pdm.ownyourvet.Room.Entities.Vaccine
import com.squareup.moshi.Json

class UserResponse (
        @field:Json(name = "data")
        val data:List<User>
)