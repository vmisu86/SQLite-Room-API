package com.vmisu.databases.roomDatabase


import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.IO
import kotlinx.coroutines.experimental.launch


@Database(entities = [Cours::class], version = 2)
abstract class CoursRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): CoursDao

    companion object {
        @Volatile
        private var INSTANCE: CoursRoomDatabase? = null

        fun getDatabase(
                context: Context,
                scope: CoroutineScope
        ): CoursRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        CoursRoomDatabase::class.java,
                        "word_database"
                )
                        .fallbackToDestructiveMigration()
                        .addCallback(
                            WordDatabaseCallback(
                                scope
                            )
                        )
                        .build()
                INSTANCE = instance
                instance
            }
        }

        private class WordDatabaseCallback(
                private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.wordDao())
                    }
                }
            }
        }


        fun populateDatabase(coursDao: CoursDao) {
            coursDao.deleteAll()
            var cours = Cours("Android ABC")
            coursDao.insert(cours)
            cours = Cours("LP-DAM!")
            coursDao.insert(cours)
        }
    }

}
