package com.pdm.ownyourvet.Room.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pdm.ownyourvet.Room.Entities.Species

@Dao
interface SpecieDao {

    @Query("select * from species")
    fun getAllSpecies():LiveData<List<Species>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpecie(specie:Species):Long

    @Query("delete  from species")
    suspend fun deleteAll()

    @Query("select * from species where id = :id")
    fun findSpecieById(id:Long):LiveData<Species>
}