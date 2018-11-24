package com.vmisu.databases.roomDatabase

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.IO
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.Main
import kotlinx.coroutines.experimental.launch
import kotlin.coroutines.experimental.CoroutineContext



class CoursViewModel(application: Application) : AndroidViewModel(application) {

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    private val repository: CoursRepository
    val allWords: LiveData<List<Cours>>

    init {
        val wordsDao = CoursRoomDatabase.getDatabase(application, scope).wordDao()
        repository = CoursRepository(wordsDao)
        allWords = repository.allCours
    }


    fun insert(cours: Cours) = scope.launch(Dispatchers.IO) {
        repository.insert(cours)
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}
