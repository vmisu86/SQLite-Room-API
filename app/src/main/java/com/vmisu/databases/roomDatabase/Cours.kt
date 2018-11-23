package com.vmisu.databases.roomDatabase


import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "cours_table")
data class Cours(@PrimaryKey @ColumnInfo(name = "cours") val cours: String)
