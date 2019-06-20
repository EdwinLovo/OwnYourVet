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
    suspend fun insertDiseas(diseases: Diseases)

    @Query("select * from diseases")
    fun getAllDiseases():LiveData<List<Diseases>>

    @Query("delete from diseases")
    suspend fun deleteAllDiseases()

}