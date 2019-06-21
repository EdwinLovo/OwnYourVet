package com.pdm.ownyourvet.Room.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "species")
data class Species(
        @PrimaryKey
        val id: Long,
        val name: String
)