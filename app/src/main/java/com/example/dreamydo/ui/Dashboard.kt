package com.example.dreamydo.ui

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dreamydo.R
import com.example.dreamydo.database.DatabaseHelper
import com.example.dreamydo.model.Task
import com.example.dreamydo.utils.AddTask
import com.example.dreamydo.utils.Time
import com.example.dreamydo.viewmodel.TaskListAdapter
import com.google.android.material.chip.Chip

class Dashboard : AppCompatActivity() {

    private lateinit var taskList: List<Task>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard)

        taskList = getTasks()
        setupUI()
    }

    private fun setupUI() {
        greetings()
        tasksList()
    }

    private fun tasksList() {
        taskList = getTasks()
        val recyclerView: RecyclerView = findViewById(R.id.taskListRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = TaskListAdapter(taskList)
    }

    private fun greetings() {
        val greeting: TextView = findViewById(R.id.greeting)
        val date: TextView = findViewById(R.id.date)
        val addTask: Chip = findViewById(R.id.addTaskBtn)

        greeting.text = Time.timeKeeping()
        date.text = Time.date()
        addTask.setOnClickListener { startActivity(Intent(this, AddTask::class.java)) }
    }

    private fun getTasks(): List<Task> {
        val dbHelper = DatabaseHelper(this)
        val tasks = dbHelper.getAllTasks()
        dbHelper.close()
        return tasks
    }

    override fun onResume() {
        tasksList()
        super.onResume()
    }

}