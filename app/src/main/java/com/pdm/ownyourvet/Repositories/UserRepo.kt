package com.pdm.ownyourvet.Repositories

import androidx.annotation.WorkerThread
import com.pdm.ownyourvet.Room.Dao.UserDao
import com.pdm.ownyourvet.Room.Entities.User

class UserRepo(private val userDao: UserDao) {

    @WorkerThread
    suspend fun insertUsers(users: List<User>) = userDao.insertUsers(users)

    @WorkerThread
    suspend fun insertUser(user:User) = userDao.insertUser(user)

    fun getAllUsers() = userDao.getAllUsers()


    fun getUserByType(type:String) = userDao.getUsersByType(type)

}