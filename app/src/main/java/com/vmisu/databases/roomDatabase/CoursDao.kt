package com.vmisu.databases.roomDatabase



import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface CoursDao {

    @Query("SELECT * from cours_table ORDER BY cours ASC")
    fun getAlphabetizedWords(): LiveData<List<Cours>>

    @Insert
    fun insert(cours: Cours)

    @Query("DELETE FROM cours_table")
    fun deleteAll()
}
