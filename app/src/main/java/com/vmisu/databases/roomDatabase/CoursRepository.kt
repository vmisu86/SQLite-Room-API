package com.vmisu.databases.roomDatabase


import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread


class CoursRepository(private val coursDao: CoursDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allCours: LiveData<List<Cours>> = coursDao.getAlphabetizedWords()

    // You must call this on a non-UI thread or your app will crash. So we're making this a
    // suspend function so the caller methods know this.
    // Like this, Room ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(cours: Cours) {
        coursDao.insert(cours)
    }
}
