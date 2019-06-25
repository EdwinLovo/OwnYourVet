package com.pdm.ownyourvet.Room.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pdm.ownyourvet.Room.Entities.Vaccine

@Dao
interface VaccineDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVaccine(vaccine: Vaccine)

    @Query("select * from vaccine where specie_id=:specie order by weeks asc")
    fun getAllVaccinationsBySpecie(specie:Long):LiveData<List<Vaccine>>

    @Query("select * from vaccine order by weeks asc")
    fun getAll():LiveData<List<Vaccine>>

    @Query("delete from vaccine")
    suspend fun deleteAll()
}