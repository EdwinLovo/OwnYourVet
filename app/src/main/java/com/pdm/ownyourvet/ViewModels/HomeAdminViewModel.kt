package com.pdm.ownyourvet.ViewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.pdm.ownyourvet.Network.UserService
import com.pdm.ownyourvet.Repositories.UserRepo
import com.pdm.ownyourvet.Room.Entities.User
import com.pdm.ownyourvet.Room.RoomDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeAdminViewModel(private val app: Application) : AndroidViewModel(app) {
    private val userRepo: UserRepo

    init {
        val userDao = RoomDB.getInstance(app).userDao()
        userRepo = UserRepo(userDao)
    }

    fun retreiveUsers() = viewModelScope.launch(Dispatchers.IO) {

    }

    fun sendUser(user: User) = viewModelScope.launch(Dispatchers.IO) {
        val api = UserService.getUserService()
        val usrResp = api.getUserById(user.id).await()
        if (!usrResp.isSuccessful) with(usrResp) {

            if (this.code() == 404) {
                val resp = api.saveUser(user.id, user.email, user.userType, user.names, user.direction).await()
                if (resp.isSuccessful) {
                    Log.d("REQUESTS", "user ${user.email} saved into api")
                    userRepo.insertUser(user)
                } else with(resp) {
                    if (this.code() == 404)
                        Log.e("REQUESTS", this.message())
                }
            }
        }

    }
}
