package com.pdm.ownyourvet.Room.Entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "species")
data class Species(
        @PrimaryKey
        @ColumnInfo(name = "id")
        @field:Json(name = "id")
        val id: Long,

        @field:Json(name = "name")
        @ColumnInfo(name = "name")
        val name: String
)