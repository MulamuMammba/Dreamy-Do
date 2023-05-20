package com.example.dreamydo.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.dreamydo.model.Task

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "dreamy.db"
        private const val DATABASE_VERSION = 1

        // Table names
        private const val TABLE_CATEGORIES = "categories"
        private const val TABLE_TASKS = "tasks"

        // Common column names
        private const val KEY_ID = "id"

        // Categories table column names
        private const val KEY_CATEGORY_NAME = "category_name"

        // Tasks table column names
        private const val KEY_TASK_NAME = "task_name"
        private const val KEY_TASK_NOTES = "task_notes"
        private const val KEY_DUE_DATE = "due_date"
        private const val KEY_PRIORITY = "priority"
        private const val KEY_IS_COMPLETED = "is_completed"
        private const val KEY_CATEGORY_ID = "category_id"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createCategoriesTable =
            "CREATE TABLE $TABLE_CATEGORIES($KEY_ID INTEGER PRIMARY KEY, $KEY_CATEGORY_NAME TEXT)"
        val createTasksTable =
            "CREATE TABLE $TABLE_TASKS($KEY_ID INTEGER PRIMARY KEY, $KEY_TASK_NAME TEXT, $KEY_TASK_NOTES TEXT, $KEY_DUE_DATE TEXT, $KEY_PRIORITY INTEGER, $KEY_IS_COMPLETED INTEGER, $KEY_CATEGORY_ID INTEGER)"

        db.execSQL(createCategoriesTable)
        db.execSQL(createTasksTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Drop older tables if they exist
        db.execSQL("DROP TABLE IF EXISTS $TABLE_CATEGORIES")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_TASKS")

        // Create new tables
        onCreate(db)
    }

    // Add methods here for database operations such as inserting, updating, querying, and deleting data.
    // You can include methods to manage categories and tasks.

    // Example method to add a category
    fun addCategory(categoryName: String): Long {
        val values = ContentValues()
        values.put(KEY_CATEGORY_NAME, categoryName)

        val db = writableDatabase
        val categoryId = db.insert(TABLE_CATEGORIES, null, values)
        db.close()

        return categoryId
    }

    // Example method to add a task
    fun addTask(task: Task): Long {
        val values = ContentValues()
        values.put(KEY_TASK_NAME, task.taskName)
        values.put(KEY_TASK_NOTES, task.taskNotes)
        values.put(KEY_DUE_DATE, task.dueDate)
        values.put(KEY_PRIORITY, task.priority)
        values.put(KEY_IS_COMPLETED, if (task.isCompleted) 1 else 0)
        values.put(KEY_CATEGORY_ID, task.categoryId)

        val db = writableDatabase
        val taskId = db.insert(TABLE_TASKS, null, values)
        db.close()

        return taskId
    }

    // Example method to get all tasks
    @SuppressLint("Range")
    fun getAllTasks(): List<Task> {
        val tasks = mutableListOf<Task>()
        val query = "SELECT * FROM $TABLE_TASKS"

        val db = readableDatabase
        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val taskId = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                val taskName = cursor.getString(cursor.getColumnIndex(KEY_TASK_NAME))
                val taskNotes = cursor.getString(cursor.getColumnIndex(KEY_TASK_NOTES))
                val dueDate = cursor.getString(cursor.getColumnIndex(KEY_DUE_DATE))
                val priority = cursor.getInt(cursor.getColumnIndex(KEY_PRIORITY))
                val isCompleted = cursor.getInt(cursor.getColumnIndex(KEY_IS_COMPLETED)) == 1
                val categoryId = cursor.getInt(cursor.getColumnIndex(KEY_CATEGORY_ID))

                val task =
                    Task(taskId, taskName, taskNotes, dueDate, priority, isCompleted, categoryId)
                tasks.add(task)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return tasks
    }

    // Add more methods as per your requirements for updating, deleting, or querying data from the database.
}
