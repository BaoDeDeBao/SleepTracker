package com.example.sleeptracker

import androidx.lifecycle.LiveData

class SleepRepository(private val sleepDao: SleepDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val sleepList: LiveData<List<Sleep>> = sleepDao.getSleep()

    suspend fun insert(sleep: Sleep) {
        sleepDao.insertSleep(sleep)
    }
}