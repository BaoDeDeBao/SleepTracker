package com.example.sleeptracker

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.time.Instant

@Database(entities = [Sleep::class], version = 1)
abstract class SleepDatabase: RoomDatabase() {
    //Create an instance of the DAO
    abstract fun sleepDao() : SleepDao

    companion object {
        //Create an instance of the Room database
        //Singleton prevents multiple instances of the database
        @Volatile
        private var INSTANCE : SleepDatabase? = null

        fun getDatabase(context: Context) : SleepDatabase {
            val tempDB = INSTANCE
            if (tempDB!= null) {
                return tempDB
            }

            //Create an instance of the Database
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    SleepDatabase::class.java,
                    "sleep_db"
                ).build()

                INSTANCE = instance

                return instance
            }
        }
    }
}