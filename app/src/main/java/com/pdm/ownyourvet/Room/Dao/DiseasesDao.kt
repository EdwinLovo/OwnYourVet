package com.pdm.ownyourvet.Room.Dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pdm.ownyourvet.Room.Entities.Diseases

@Dao
interface DiseasesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDiseases(diseases: List<Diseases>)

    @Query("select * from diseases order by name asc")
    fun getAllDiseases():DataSource.Factory<Int,Diseases>

    @Query("select * from diseases where specie_id=:specie")
    fun getDiseasesBySpecieId(specie:Long):DataSource.Factory<Int,Diseases>

    @Query("delete from diseases")
    suspend fun deleteAllDiseases()

}