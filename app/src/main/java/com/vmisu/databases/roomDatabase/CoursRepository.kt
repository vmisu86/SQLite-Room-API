package com.vmisu.databases.roomDatabase


import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread


class CoursRepository(private val coursDao: CoursDao) {

    val allCours: LiveData<List<Cours>> = coursDao.getAlphabetizedWords()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(cours: Cours) {
        coursDao.insert(cours)
    }
}
