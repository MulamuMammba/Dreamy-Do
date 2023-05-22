package com.example.dreamydo.utils

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dreamydo.R
import com.example.dreamydo.database.DatabaseHelper
import com.example.dreamydo.model.Task
import com.google.android.material.chip.Chip

class AddTask : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_task)

        setUp()
    }

    private fun setUp() {
        val db = DatabaseHelper(this)
        val add: Chip = findViewById(R.id.addBtn)
        val cancel: Chip = findViewById(R.id.cancelBtn)
        val name: EditText = findViewById(R.id.taskNameInput)
        val notes: EditText = findViewById(R.id.taskNotesInput)

        cancel.setOnClickListener { finish() }
        add.setOnClickListener {
            val taskName = name.text?.toString()?.trim()
            val taskNotes = notes.text?.toString()?.trim()

            if (taskName.isNullOrEmpty()) {
                // Show an error message or toast to inform the user that the task name cannot be empty
                Toast.makeText(this, "Task name cannot be empty", Toast.LENGTH_SHORT).show()
            } else {
                // Add the task to the database or perform necessary operations
                // For example, you can create a new Task object and pass it to a database helper method to add it to the database
                val task: Task = Task(0, taskName, taskNotes, "", 2, false, 0)
                val valid = db.addTask(task)
                if (valid > 0) {
                    Toast.makeText(this, "$taskName added successfully", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}