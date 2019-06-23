package com.pdm.ownyourvet.Room.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "diseases")
data class Diseases(

    @PrimaryKey
    val id:Long,

    val name:String,

    val specie_id:Long,

    val information:String

){
    @ColumnInfo(name = "next_ref")
    var nextReference:String? = ""
}