package com.vmisu.databases.roomDatabase

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import com.vmisu.databases.R


class NewCoursActivity : AppCompatActivity() {

    private lateinit var editCoursView: EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_cours)
        editCoursView = findViewById(R.id.edit_word)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editCoursView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val word = editCoursView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, word)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.vmisu.databases.roomDatabase.REPLY"
    }
}

