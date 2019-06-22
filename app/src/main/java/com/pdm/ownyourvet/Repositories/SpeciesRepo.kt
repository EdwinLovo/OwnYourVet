package com.pdm.ownyourvet.Repositories

import androidx.annotation.WorkerThread
import com.pdm.ownyourvet.Room.Dao.SpecieDao
import com.pdm.ownyourvet.Room.Entities.Species

class SpeciesRepo (private val specieDao: SpecieDao){

    @WorkerThread
    suspend fun insertSpecie(specie:Species) = specieDao.insertSpecie(specie)


    @WorkerThread
    suspend fun deleteAllSpecies() = specieDao.deleteAll()


    fun getAllSpecies() = specieDao.getAllSpecies()



}