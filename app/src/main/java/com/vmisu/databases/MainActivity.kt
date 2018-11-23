package com.vmisu.databases

/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
