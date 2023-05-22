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
import com.example.dreamydo.model.Task

class TaskView : AppCompatActivity() {

    private lateinit var task: Task
    private val db = DatabaseHelper(this)
    private var completion: Boolean = false

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.task_view)

        val title: TextView = findViewById(R.id.taskName)
        val notes: EditText = findViewById(R.id.notes)
        val complete: ToggleButton = findViewById(R.id.complete)

        val id = intent.getIntExtra("task_id", 2)
        task = db.getTaskById(id)!!

        title.text = task.taskName
        notes.setText(task.taskNotes)
        complete.isChecked = !task.isCompleted

        complete.setOnClickListener {
            completion = complete.isChecked
            var message = "The task is "

            if (completion) {
                message += "complete🥳"
            } else {
                message += "still to be done😢"
            }
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    @Deprecated("Deprecated in Java", ReplaceWith("finish()"))
    override fun onBackPressed() {
        updateDB()
        finish()
    }

    private fun updateDB() {
        //Update Completion Status
        task.isCompleted = completion

        //Update Notes
        val notes: EditText = findViewById(R.id.notes)
        task.taskNotes = notes.text?.toString()?.trim()

        //Update the database
        db.updateTask(task)
    }
}