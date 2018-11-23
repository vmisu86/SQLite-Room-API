package com.vmisu.databases

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.vmisu.database.ankoDatabase.CourseDb
import com.example.vmisu.database.ankoDatabase.CourseDbHelper
import com.example.vmisu.database.ankoDatabase.MobileCourse
import org.jetbrains.anko.*

class Anko : AppCompatActivity(), AnkoLogger {

    var list = listOf<MobileCourse>()
    private lateinit var listView: ListView
    private var defaultQuotes:ArrayList<String> = ArrayList<String>()
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anko)

        supportActionBar!!.title = "Back"
        supportActionBar!!.setDefaultDisplayHomeAsUpEnabled(true)


        listView = findViewById(R.id.listview1)

        defaultQuotes.add("SQLite - Anko - ListView")



        val courseDb by lazy {
            CourseDb(
                CourseDbHelper(
                    applicationContext
                )
            )
        }

        doAsync {
            val course1 = MobileCourse("ABC Android", 120)
            courseDb.saveCourse(course1)
        }

        doAsync {
            val list = courseDb.requestCourse()
        }

        doAsync {
            list = courseDb.requestCourse()
            uiThread {
                showList()

            }
        }
        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, defaultQuotes)
    }
   fun showList() {
       defaultQuotes.add("NB COURS : ${list.size}")
        for (c in list)
            defaultQuotes.add("Voici un cours ${c.title}")
    }
}

