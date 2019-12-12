package com.example.sleeptracker

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SleepDao {
    @Insert
    suspend fun insertSleep(sleep: Sleep) //suspend - Separate two tracks

    @Query("SELECT * FROM sleep")
    suspend fun getSleep() : LiveData<List<Sleep>>

    @Query("SELECT * FROM sleep WHERE id = :sleep_id")
    suspend fun getASleep(sleep_id: Int)

    @Update
    suspend fun updateSleep(sleep: Sleep)

    @Delete
    suspend fun deleteSleep(sleep: Sleep)

    @Query("DELETE FROM sleep WHERE id = :sleep_id")
    suspend fun deleteASleep(sleep_id: Int)
}