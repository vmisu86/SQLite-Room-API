package com.vmisu.databases


import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button


import org.jetbrains.anko.find

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val roomBtn = find<Button>(R.id.room_btn)
        val sqliteBtn = find<Button>(R.id.sqlite_btn)
        val apiBtn = find<Button>(R.id.api_btn)

        sqliteBtn.setOnClickListener {
            val intent = Intent(this,Anko::class.java)
            startActivity(intent)
        }
        roomBtn.setOnClickListener {
            val intent = Intent(this,RoomDatabase::class.java)
            startActivity(intent)
        }
        apiBtn.setOnClickListener {
            val intent = Intent(this,ApiDatabase::class.java)
            startActivity(intent)
        }
    }
}
