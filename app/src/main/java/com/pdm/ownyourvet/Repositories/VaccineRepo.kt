package com.pdm.ownyourvet.Repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.pdm.ownyourvet.Network.Models.vaccine.VaccineData
import com.pdm.ownyourvet.Network.VaccineService
import com.pdm.ownyourvet.Room.Dao.VaccineDao
import com.pdm.ownyourvet.Room.Entities.Vaccine
import kotlinx.coroutines.Deferred
import retrofit2.Response

class VaccineRepo(private val vaccineDao: VaccineDao) {

    fun getAll():LiveData<List<Vaccine>> = vaccineDao.getAll()

    fun getVaccinationsBySpecie(specie:Long) = vaccineDao.getVaccinationsBySpecie(specie)

    @WorkerThread
    suspend fun insert(vaccine: Vaccine) = vaccineDao.insertVaccine(vaccine)

    @WorkerThread
    suspend fun delete() = vaccineDao.deleteAll()

    fun retrieveVaccinationsAsync():Deferred<Response<VaccineData>> =
            VaccineService.getVaccinationsService().getVaccinations()
}