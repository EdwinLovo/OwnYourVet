package com.pdm.ownyourvet.ViewModels

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.annotation.UiThread
import androidx.lifecycle.*
import androidx.navigation.Navigation
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.room.Room
import com.pdm.ownyourvet.Adapters.diseases.DiseasesBoundaryCallback
import com.pdm.ownyourvet.Network.DiseasesService
import com.pdm.ownyourvet.Network.Models.pets.Race
import com.pdm.ownyourvet.Repositories.DiseasesRepo
import com.pdm.ownyourvet.Repositories.SpeciesRepo
import com.pdm.ownyourvet.Room.Entities.Diseases
import com.pdm.ownyourvet.Room.Entities.Species
import com.pdm.ownyourvet.Room.RoomDB
import com.pdm.ownyourvet.Utils.FragmentHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DiseasesViewModel(private val app: Application) : AndroidViewModel(app) {

    private val diseasesRepository: DiseasesRepo
    private val speciesRepository: SpeciesRepo
//    val allDiseases: LiveData<List<Diseases>>
    val speciesLiveData:MutableLiveData<List<Species>> = MutableLiveData()
    val racesLiveData:MutableLiveData<List<Race>> = MutableLiveData()



    init {
        val diseasesDao = RoomDB.getInstance(app).diseasesDao()
        val speciesDao = RoomDB.getInstance(app).speciesDao()
        diseasesRepository = DiseasesRepo(diseasesDao)
        speciesRepository = SpeciesRepo(speciesDao)
//        allDiseases = diseasesRepository.allDiseases

    }


    /*
    * DISEASES
    * */
    fun retreiveDiseases() = viewModelScope.launch(Dispatchers.IO){
        val resp = DiseasesService.getDiseasesService().getDiseasesNoPaging().await()
        if(resp.isSuccessful) with(resp){
//            diseasesRepository.deleteDiseases()
            val body = resp.body()!!
            diseasesRepository.deleteDiseases()
            diseasesRepository.insertDisease(body.data)
/*            body.data.forEach {
                diseasesRepository.insertDiseaseNoPaging(it)
            }*/
        }
    }

    fun getDiseasesNoPaging() = diseasesRepository.getAllDiseasesNoPaging()

    fun getDiseaseById(id:Long) = diseasesRepository.findDiseaseById(id)
    /*fun retreiveDiseases() = viewModelScope.launch(Dispatchers.IO) {
        //this@DiseasesViewModel.deleteDiseases()
        val response = diseasesRepository.retrieveDiseasesAsync().await()

        if (response.isSuccessful) with(response.body()!!.info.data) {
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
    }*/
//    fun insertDisease(diseases: List<Diseases>) = viewModelScope.launch(Dispatchers.IO) { diseasesRepository.insertDisease(diseases) }

//    fun updateDiseasesBySpecie(specie: Long)  { diseasesBySpecie = diseasesRepository.getDiseasesBySpecieId(specie) }


    fun deleteDiseases() = viewModelScope.launch(Dispatchers.IO) {
        /*Log.d("CUSTOM",id.toString())*/
        diseasesRepository.deleteDiseases()

    }



    /*
    * SPECIES
    * */
    fun retrieveSpecies() = viewModelScope.launch(Dispatchers.IO) {
        val resp = DiseasesService.getDiseasesService().getSpecies().await()
        if (resp.isSuccessful) with(resp) {
            speciesLiveData.postValue(this.body()!!.data)
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
    fun saveDisease(name:String, info:String, specieId:String,fragmentHelper: FragmentHelper) = viewModelScope.launch(Dispatchers.IO){
        val resp = DiseasesService.getDiseasesService().saveDisease(name,info,specieId).await()
        if(resp.isSuccessful){
            val body = resp.body()!!
            diseasesRepository.insertDiseaseNoPaging(body.data)
            withContext(Dispatchers.Main){
                fragmentHelper.executeAfter()

            }

        }else with(resp) {
            Log.d("CODIGO", "Error: $resp")
            when (this.code()) {
                404 -> {
                    android.widget.Toast.makeText(app, "Disease not found", Toast.LENGTH_LONG).show()
                }
            }

        }
    }
    fun updateDisease(diseaseId:String, name:String, info:String, specieId:String,fragmentHelper: FragmentHelper) = viewModelScope.launch(Dispatchers.IO){
        val resp = DiseasesService.getDiseasesService().updateDisease(diseaseId,name,info,specieId).await()
        if(resp.isSuccessful){
            withContext(Dispatchers.Main){
                fragmentHelper.executeAfter()

            }
        }else with(resp) {
            Log.d("CODIGO", "Error: $resp")
            when (this.code()) {
                404 -> {
                    withContext(Dispatchers.Main) {
                        android.widget.Toast.makeText(app, "Disease not found", Toast.LENGTH_LONG).show()

                    }
                }
            }

        }
    }

    fun getAllSpecies() = speciesRepository.getAllSpecies()
    fun getSpecieByRelation(id:Long) = speciesRepository.findSpecieByRelation(id)
    fun deleteSpecies() = viewModelScope.launch(Dispatchers.IO){ speciesRepository.deleteAllSpecies() }
    fun getRacesOfSpecie(id:String) = viewModelScope.launch (Dispatchers.IO){
        val resp = DiseasesService.getDiseasesService().getRacesOfSpecie(id).await()
        if(resp.isSuccessful){
            racesLiveData.postValue(resp.body()!!.data)
        }
        else Log.e("CUSTOM",resp.message())
    }
}