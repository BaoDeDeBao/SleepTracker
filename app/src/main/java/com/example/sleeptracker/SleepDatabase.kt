package com.example.sleeptracker

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
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

    private class SleepDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.sleepDao())
                }
            }
        }

        suspend fun populateDatabase(sleepDao: SleepDao) {

            // Add sample words.
            var sleep = Sleep(1, System.currentTimeMillis(), System.currentTimeMillis(), 3)
            sleepDao.insertSleep(sleep)
            sleep = Sleep(2, System.currentTimeMillis(), System.currentTimeMillis(), 2)
            sleepDao.insertSleep(sleep)

            // TODO: Add your own words!
        }
    }
}