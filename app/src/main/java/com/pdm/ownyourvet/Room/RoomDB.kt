package com.pdm.ownyourvet.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pdm.ownyourvet.Room.Dao.DiseasesDao
import com.pdm.ownyourvet.Room.Dao.SpecieDao
import com.pdm.ownyourvet.Room.Entities.Diseases
import com.pdm.ownyourvet.Room.Entities.Species

@Database(entities = [Diseases::class,Species::class], version = 6, exportSchema = false)
abstract class RoomDB:RoomDatabase() {

    abstract fun diseasesDao(): DiseasesDao
    abstract fun speciesDao(): SpecieDao

    companion object {
        @Volatile
        private var INSTANCE: RoomDB? = null

        fun getInstance(
            context: Context
        ): RoomDB {

            if (INSTANCE != null) {
                return INSTANCE!!
            } else {
                synchronized(this) {
                    INSTANCE = Room
                        .databaseBuilder(context, RoomDB::class.java, "Vet_DataBase")
                        .fallbackToDestructiveMigration()
                        .build()
                    return INSTANCE!!
                }
            }
        }

    }

}