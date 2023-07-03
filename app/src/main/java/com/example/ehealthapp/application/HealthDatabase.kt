package com.example.ehealthapp.application

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ehealthapp.dao.HealthDao
import com.example.ehealthapp.model.Health

@Database(entities = [Health:: class], version = 1, exportSchema = false)
abstract class HealthDatabase: RoomDatabase() {
    abstract fun healthDao(): HealthDao

    companion object{
        private var INSTANCE: HealthDatabase? = null

        fun getDatabase(context: Context): HealthDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HealthDatabase::class.java,
                    "health_database"
                )
                    .allowMainThreadQueries()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}