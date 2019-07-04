package com.pdm.ownyourvet.ViewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pdm.ownyourvet.Network.Models.schedules.Schedule
import com.pdm.ownyourvet.Network.SchedulesService
import com.pdm.ownyourvet.Utils.FragmentHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SchedulesViewModel(private val app: Application) : AndroidViewModel(app) {
    val schedulesLiveData:MutableLiveData<List<Schedule>> = MutableLiveData()
    fun retrieveAllSchedules(id:String) = viewModelScope.launch(Dispatchers.IO){
        val resp =SchedulesService.getSchedulesService().getSchedules(id).await()
        if(resp.isSuccessful){
            Log.d("REQUESTS","schedules retrieved!!")
            schedulesLiveData.postValue(resp.body()!!.data)
        }
        else Log.e("REQUESTS",resp.message())
    }
    fun saveSchedule(id:String,day:String,fragmentHelper: FragmentHelper) = viewModelScope.launch(Dispatchers.IO){
        val resp = SchedulesService.getSchedulesService().saveSchedule(day,id).await()
        if(resp.isSuccessful){
            withContext(Dispatchers.Main){
                fragmentHelper.executeAfter()
            }
        }
        else {
            when(resp.code()){
                400-> withContext(Dispatchers.Main){
                    fragmentHelper.executeAfter("Un veterinario ya estara de turno ese dia, seleccionar otro")
                }
                else-> Log.e("REQUESTS",resp.message())
            }
        }
    }
}