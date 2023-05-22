package com.example.dreamydo.model

data class Task(
    val taskId: Int,
    val taskName: String,
    val taskNotes: String?,
    val dueDate: String?,
    val priority: Int = 2,
    var isCompleted: Boolean,
    val categoryId: Int
)
/*
When it comes to priority it's a three stage system
1 - low priority
2 - Normal priority (Default)
3 - High priority
 */