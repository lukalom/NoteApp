package com.example.noteapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.content.Intent
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var addNewButton: Button
    private lateinit var adapter: NoteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView= findViewById(R.id.notes_recyclerview)
        addNewButton = findViewById(R.id.addButton)
        val sharedPreference =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        val fetch = sharedPreference.getStringSet("List", null) ?: setOf()
        adapter = NoteAdapter(fetch.toList(), object : NoteOnClickListener{
            override fun onLongClick(position: Int) {
                val editor = sharedPreference.edit()
                editor.clear()
                fetch.toMutableList().removeAt(position)
                editor.putStringSet("List", fetch.toSet())
                editor.commit()
                adapter.notifyItemRemoved(position)
            }
        })
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        addNewButton.setOnClickListener {
            val i = Intent(this, CreateNoteActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}