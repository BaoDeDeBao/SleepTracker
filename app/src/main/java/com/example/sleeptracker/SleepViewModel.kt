package com.example.sleeptracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class SleepViewModel (application: Application): AndroidViewModel(application) {

    private val sleepRepository: SleepRepository

    val sleepList: LiveData<List<Sleep>>

    init {
        val sleepDao = SleepDatabase.getDatabase(application).sleepDao()
        sleepRepository = SleepRepository(sleepDao)
        sleepList = sleepRepository.sleepList
    }

}