package com.pdm.ownyourvet.Adapters.diseases

import androidx.paging.PageKeyedDataSource
import com.pdm.ownyourvet.Network.DiseasesService
import com.pdm.ownyourvet.Room.Entities.Diseases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DiseaseDataSource:PageKeyedDataSource<String,Diseases>() {
    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, Diseases>) {
        GlobalScope.launch(Dispatchers.IO){
            val resp = DiseasesService.getDiseasesService().getDiseases().await()
            if(resp.isSuccessful) with(resp){
                val body = resp.body()!!
                body.info.data.forEach {
                    it.nextReference = body.info.nextPage
                }
                callback.onResult(body.info.data,body.info.prevPage,body.info.nextPage)
            }

        }
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, Diseases>) {
        val map = handleKey(params.key)

        GlobalScope.launch(Dispatchers.IO){
            val resp = DiseasesService.getDiseasesService().getDiseases(map["page"]!!).await()
            if(resp.isSuccessful) with(resp){
                val body = resp.body()!!
                body.info.data.forEach {
                    it.nextReference = body.info.nextPage
                }
                callback.onResult(body.info.data,body.info.nextPage)
            }

        }
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, Diseases>) {
        val map = handleKey(params.key)

        GlobalScope.launch(Dispatchers.IO){
            val resp = DiseasesService.getDiseasesService().getDiseases(map["page"]!!).await()
            if(resp.isSuccessful) with(resp){
                val body = resp.body()!!
                body.info.data.forEach {
                    it.nextReference = body.info.nextPage
                }
                callback.onResult(body.info.data,body.info.prevPage)
            }

        }
    }

    private fun handleKey(key: String): MutableMap<String, String> {
        val (_, queryPart) = key.split("?")
        val queries = queryPart.split("&".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val map = mutableMapOf<String, String>()
        for (query in queries) {
            val (k, v) = query.split("=".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            map[k] = v
        }
        return map
    }
}