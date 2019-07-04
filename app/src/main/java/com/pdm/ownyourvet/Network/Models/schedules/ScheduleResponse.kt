package com.pdm.ownyourvet.Network.Models.schedules

import com.squareup.moshi.Json

class ScheduleResponse (
        @field:Json(name="data")
        val data:List<Schedule>
)