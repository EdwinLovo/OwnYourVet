package com.pdm.ownyourvet.Adapters.diseases

import android.util.Log
import androidx.paging.PagedList
import com.pdm.ownyourvet.Network.DiseasesService
import com.pdm.ownyourvet.Room.Entities.Diseases
import com.pdm.ownyourvet.Room.RoomDB
import com.pdm.ownyourvet.Utils.PagingRequestHelper
import kotlinx.coroutines.*
import java.util.concurrent.Executors

class DiseasesBoundaryCallback(private val db: RoomDB) :
    PagedList.BoundaryCallback<Diseases>() {
    var currentPage:Int = 0
    private val api = DiseasesService.getDiseasesService()
    private val executor = Executors.newSingleThreadExecutor()
    private val helper = PagingRequestHelper(executor)

    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        helper.runIfNotRunning(PagingRequestHelper.RequestType.INITIAL) {
            GlobalScope.launch(Dispatchers.IO) {
                val newList = ArrayList<Diseases>()
                val resp = api.getDiseases().await()
                if (resp.isSuccessful) {
                    val body = resp.body()!!
                    currentPage = body.info.currentPage.toInt()
                    body.info.data.forEach {
                        it.nextReference = body.info.nextPage
                        newList.add(it)
                    }
                    db.diseasesDao().insertDiseases(newList)
                    it.recordSuccess()
                }
            }
        }
    }

    override fun onItemAtEndLoaded(itemAtEnd: Diseases) {
        super.onItemAtEndLoaded(itemAtEnd)
        helper.runIfNotRunning(PagingRequestHelper.RequestType.AFTER) {
            GlobalScope.launch(Dispatchers.IO) {
                currentPage++
                val resp = api.getDiseases(currentPage.toString()).await()
                if (resp.isSuccessful) {
                    db.diseasesDao().insertDiseases(resp.body()!!.info.data)
                    it.recordSuccess()
                }
            }
        }
    }

}