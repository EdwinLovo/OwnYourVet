package com.pdm.ownyourvet.Repositories

import androidx.annotation.WorkerThread
import com.pdm.ownyourvet.Room.Dao.UserDao
import com.pdm.ownyourvet.Room.Entities.User

class UserRepo(private val userDao: UserDao) {

    @WorkerThread
    suspend fun insertUser(user: User) = userDao.insertUser(user)

    fun getAllUsers() = userDao.getAllUsers()
}