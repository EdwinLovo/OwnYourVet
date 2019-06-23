package com.pdm.ownyourvet.Room.Entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "species")
data class Species(
        @PrimaryKey
        @ColumnInfo(name = "id")
        val id: Long,
        @ColumnInfo(name = "name")
        val name: String
)