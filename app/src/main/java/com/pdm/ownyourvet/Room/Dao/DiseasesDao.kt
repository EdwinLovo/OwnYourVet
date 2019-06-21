package com.pdm.ownyourvet.Room.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pdm.ownyourvet.Room.Entities.Diseases

@Dao
interface DiseasesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDiseases(diseases: Diseases)

    @Query("select * from diseases")
    fun getAllDiseases():LiveData<List<Diseases>>

    @Query("select * from diseases where specie_id=:specie")
    fun getDiseasesBySpecieId(specie:Long):LiveData<List<Diseases>>

    @Query("delete from diseases")
    suspend fun deleteAllDiseases()

}