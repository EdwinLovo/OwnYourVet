package com.pdm.ownyourvet.Repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.pdm.ownyourvet.Network.DiseasesService
import com.pdm.ownyourvet.Network.Models.DiseasesData
import com.pdm.ownyourvet.Room.Dao.DiseasesDao
import com.pdm.ownyourvet.Room.Entities.Diseases
import kotlinx.coroutines.Deferred
import retrofit2.Response

class DiseasesRepo(private val diseasesDao: DiseasesDao) {

    val allDiseases:LiveData<List<Diseases>> = diseasesDao.getAllDiseases()

    fun getDiseasesBySpecieId(specie:Long) = diseasesDao.getDiseasesBySpecieId(specie)

    @WorkerThread
    suspend fun insertDisease(diseases: Diseases) = diseasesDao.insertDiseases(diseases)

    @WorkerThread
    suspend fun deleteDiseases() = diseasesDao.deleteAllDiseases()

    fun retrieveDiseasesAsync():Deferred<Response<DiseasesData>> =
            DiseasesService.getDiseasesService().getDiseases()

}