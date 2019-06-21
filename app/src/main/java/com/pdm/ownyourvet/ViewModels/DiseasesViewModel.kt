package com.pdm.ownyourvet.ViewModels

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.pdm.ownyourvet.Repositories.DiseasesRepo
import com.pdm.ownyourvet.Room.Entities.Diseases
import com.pdm.ownyourvet.Room.RoomDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DiseasesViewModel(private val app:Application):AndroidViewModel(app) {

    private val diseasesRepository:DiseasesRepo
    val allDiseases:LiveData<List<Diseases>>

    init {
        val diseasesDao = RoomDB.getInstance(app).diseasesDao()
        diseasesRepository = DiseasesRepo(diseasesDao)
        allDiseases = diseasesRepository.allDiseases
    }

    fun insertDisease(diseases: Diseases) = viewModelScope.launch(Dispatchers.IO){
        diseasesRepository.insertDisease(diseases)
    }

    fun deleteDiseases() = viewModelScope.launch(Dispatchers.IO){
        diseasesRepository.deleteDiseases()
    }

    fun retrieveMovies() = viewModelScope.launch(Dispatchers.IO){
        this@DiseasesViewModel.deleteDiseases()
        val response=diseasesRepository.retrieveDiseasesAsync().await()

        if (response.isSuccessful) with(response.body()!!.data){
            this?.forEach {
                this@DiseasesViewModel.insertDisease(it)
                Log.d("CODIGO", it.name+" ingresada correctamente")
            }
        } else with(response){
            Log.d("CODIGO", "Error: $response")
            when(this.code()){
                404->{
                    Toast.makeText(app, "Disease not found", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}