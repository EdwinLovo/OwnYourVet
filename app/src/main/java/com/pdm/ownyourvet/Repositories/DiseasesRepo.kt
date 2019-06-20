package com.pdm.ownyourvet.Repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.pdm.ownyourvet.Network.DiseasesService
import com.pdm.ownyourvet.Room.Dao.DiseasesDao
import com.pdm.ownyourvet.Room.Entities.Diseases
import kotlinx.coroutines.Deferred
import retrofit2.Response

class DiseasesRepo(private val diseasesDao: DiseasesDao) {

    val allDiseases:LiveData<List<Diseases>> = diseasesDao.getAllDiseases()

    @WorkerThread
    suspend fun insertDisease(diseases: Diseases) = diseasesDao.insertDiseas(diseases)

    @WorkerThread
    suspend fun deleteDiseases() = diseasesDao.deleteAllDiseases()

    fun retrieveDiseasesAsync(diseases: Diseases):Deferred<Response<Diseases>> =
            DiseasesService.getDiseasesService().getDiseases()

}