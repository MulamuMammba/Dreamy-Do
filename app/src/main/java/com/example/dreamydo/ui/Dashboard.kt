package com.example.dreamydo.ui

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
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
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TaskListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard)

        taskList = getTasks()

        // Initialize the adapter
        adapter = TaskListAdapter(taskList)
        setupUI()
    }

    private fun setupUI() {
        greetings()
        tasksList()
        swipe()
    }

    private fun tasksList() {
        taskList = getTasks()
        recyclerView = findViewById(R.id.taskListRecyclerView)
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

    private fun swipe() {

        // Attach swipe helper for swipe actions
        val swipeHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val task = taskList[position]

                if (direction == ItemTouchHelper.RIGHT) {
                    val alertDialog = AlertDialog.Builder(viewHolder.itemView.context)
                        .setTitle("Delete Task")
                        .setMessage("Are you sure you want to delete this task?")
                        .setIcon(R.drawable.ic_delete) // Set dialog icon
                        .setPositiveButton("Yes") { _, _ ->

                            // Delete the task from the database
                            val dbHelper = DatabaseHelper(this@Dashboard)
                            dbHelper.deleteTask(task.taskId)
                            // Remove the item from the list and notify the adapter
                            tasksList()
                        }
                        .setNegativeButton("No") { _, _ ->
                            tasksList()
                        }
                        .create()

                    alertDialog.show()
                }
            }

        })
        swipeHelper.attachToRecyclerView(recyclerView)
    }

    override fun onResume() {
        tasksList()
        super.onResume()
    }

}