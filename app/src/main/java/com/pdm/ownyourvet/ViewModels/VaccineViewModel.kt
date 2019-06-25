package com.pdm.ownyourvet.ViewModels

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.pdm.ownyourvet.Repositories.VaccineRepo
import com.pdm.ownyourvet.Room.Entities.Vaccine
import com.pdm.ownyourvet.Room.RoomDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VaccineViewModel(private val app:Application):AndroidViewModel(app) {

    private val repo:VaccineRepo
    var allVaccinations:LiveData<List<Vaccine>>? = null
    var all:LiveData<List<Vaccine>>

    init {
        val vaccineDao = RoomDB.getInstance(app).vaccineDao()
        repo = VaccineRepo(vaccineDao)
        all = repo.getAll()
    }

    fun insertVaccine(vaccine: Vaccine) = viewModelScope.launch(Dispatchers.IO){
        repo.insert(vaccine)
    }

    fun deleteVaccinations() = viewModelScope.launch(Dispatchers.IO){
        repo.delete()
    }

    //fun getVaccineBySpecie(id:Long) = repo.getAllVaccinations(id)

    fun updateVaccinations(specie:Long) = viewModelScope.launch(Dispatchers.IO){
        allVaccinations = repo.getAllVaccinations(specie)
    }

    fun retrieveVaccinations() = viewModelScope.launch(Dispatchers.IO){
        this@VaccineViewModel.deleteVaccinations()
        val response=repo.retrieveVaccinationsAsync().await()

        if (response.isSuccessful) with(response.body()?.data){
            this?.forEach {
                this@VaccineViewModel.insertVaccine(it)
                Log.d("CODIGO", it.name+" ingresada correctamente")
            }
        } else with(response){
            Log.d("CODIGO", "Error: $response")
            when(this.code()){
                404->{
                    Toast.makeText(app, "Movie not found", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}