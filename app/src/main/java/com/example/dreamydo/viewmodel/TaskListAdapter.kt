package com.example.dreamydo.viewmodel

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dreamydo.R
import com.example.dreamydo.model.Task
import com.example.dreamydo.ui.TaskView

class TaskListAdapter(private var taskList: List<Task>) :
    RecyclerView.Adapter<TaskListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val taskName: TextView = view.findViewById(R.id.taskTitle)
        val taskDescription: TextView = view.findViewById(R.id.taskDescription)
        val taskDueDate: TextView = view.findViewById(R.id.taskDueDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = taskList[position]

        holder.taskName.text = task.taskName
        holder.taskDescription.text = task.taskNotes
        holder.taskDueDate.text = task.dueDate

        // Set onClick listener for the current task item
        holder.itemView.setOnClickListener {
            onTaskItemClick(holder.itemView.context, task)
        }
    }

    private fun onTaskItemClick(context: Context, task: Task) {
        val intent = Intent(context, TaskView::class.java)

        // Pass task id to TaskView activity
        intent.putExtra("task_id", task.taskId)
        context.startActivity(intent)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

}