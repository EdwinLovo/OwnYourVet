package com.pdm.ownyourvet.ViewModels

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.pdm.ownyourvet.Network.DiseasesService
import com.pdm.ownyourvet.Repositories.DiseasesRepo
import com.pdm.ownyourvet.Repositories.SpeciesRepo
import com.pdm.ownyourvet.Room.Entities.Diseases
import com.pdm.ownyourvet.Room.RoomDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DiseasesViewModel(private val app: Application) : AndroidViewModel(app) {

    private val diseasesRepository: DiseasesRepo
    private val speciesRepository: SpeciesRepo
    val allDiseases: LiveData<List<Diseases>>
    var diseasesBySpecie: LiveData<List<Diseases>>? = null

    init {
        val diseasesDao = RoomDB.getInstance(app).diseasesDao()
        val speciesDao = RoomDB.getInstance(app).speciesDao()
        diseasesRepository = DiseasesRepo(diseasesDao)
        speciesRepository = SpeciesRepo(speciesDao)
        allDiseases = diseasesRepository.allDiseases

    }
    /*
    * DISEASES
    * */
    fun retreiveDiseases() = viewModelScope.launch(Dispatchers.IO) {
        //this@DiseasesViewModel.deleteDiseases()
        val response = diseasesRepository.retrieveDiseasesAsync().await()

        if (response.isSuccessful) with(response.body()!!.data) {
            this?.forEach {
                this@DiseasesViewModel.insertDisease(it)
//                Log.d("CODIGO", it.name + " ingresada correctamente")
            }
        } else with(response) {
            Log.e("CODIGO", "Error: $response")
            when (this.code()) {
                404 -> {
                    Toast.makeText(app, "Disease not found", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    fun insertDisease(diseases: Diseases) = viewModelScope.launch(Dispatchers.IO) { diseasesRepository.insertDisease(diseases) }

    fun updateDiseasesBySpecie(specie: Long)  { diseasesBySpecie = diseasesRepository.getDiseasesBySpecieId(specie) }

    fun deleteDiseases() = viewModelScope.launch(Dispatchers.IO) { diseasesRepository.deleteDiseases() }

    /*
    * SPECIES
    * */
    fun retrieveSpecies() = viewModelScope.launch(Dispatchers.IO) {
        val resp = DiseasesService.getDiseasesService().getSpecies().await()
        if (resp.isSuccessful) with(resp) {

            resp.body()!!.data.forEach {
                speciesRepository.insertSpecie(it)
            }

        } else with(resp) {
            Log.d("CODIGO", "Error: $resp")
            when (this.code()) {
                404 -> {
                    android.widget.Toast.makeText(app, "Disease not found", Toast.LENGTH_LONG).show()
                }
            }

        }
    }
    fun getAllSpecies() = speciesRepository.getAllSpecies()
    fun getSpecieByRelation(id:Long) = speciesRepository.findSpecieByRelation(id)
    fun deleteSpecies() = viewModelScope.launch(Dispatchers.IO){ speciesRepository.deleteAllSpecies() }
}