package com.pdm.ownyourvet.Room.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pdm.ownyourvet.Room.Entities.User

@Dao
interface UserDao {
    @Query("select * from users")
    fun getAllUsers(): LiveData<List<User>>

    @Query("select * from users where userType = :type")
    fun getUsersByType(type:String): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<User>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user:User)
}