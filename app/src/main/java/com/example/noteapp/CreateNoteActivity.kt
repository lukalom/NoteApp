package com.example.noteapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class CreateNoteActivity : AppCompatActivity() {
    private lateinit var inputedNote: EditText
    private lateinit var  addButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_note)
        inputedNote= findViewById(R.id.input_note_edit_text)
        addButton= findViewById(R.id.add_note_button)
        saveNewSet()
    }

    @SuppressLint("CommitPrefEdits", "MutatingSharedPrefs")
    private fun saveNewSet(){
        addButton.setOnClickListener {
            val sharedPreference =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
            val editor = sharedPreference.edit()
            editor.clear()
            val fetch = sharedPreference.getStringSet("List", null) ?: mutableSetOf()
            fetch.add(inputedNote.text.toString())
            editor.putStringSet("List", fetch)
            editor.commit()
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}