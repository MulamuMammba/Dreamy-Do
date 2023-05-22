package com.example.dreamydo.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import com.example.dreamydo.R
import com.example.dreamydo.database.DatabaseHelper

class TaskView : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.task_view)

        val title: TextView = findViewById(R.id.taskName)
        val notes: EditText = findViewById(R.id.notes)
        val complete: ToggleButton = findViewById(R.id.complete)

        val id = intent.getIntExtra("task_id", 2)
        val db = DatabaseHelper(this)
        val task = db.getTaskById(id)

        if (task != null) {
            title.text = task.taskName
            notes.setText(task.taskNotes)
            complete.isChecked = !task.isCompleted
        }

        complete.setOnClickListener {
            Toast.makeText(this, "The button is ${complete.isChecked}", Toast.LENGTH_SHORT).show()
        }
    }

    @Deprecated("Deprecated in Java", ReplaceWith("finish()"))
    override fun onBackPressed() {
        finish()
    }
}