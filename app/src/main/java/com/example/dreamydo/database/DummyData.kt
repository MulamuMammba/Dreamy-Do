package com.example.dreamydo.database

import com.example.dreamydo.model.Task

fun getDummyTasks(): List<Task> {
    return listOf(
        Task(1, "Attend Math Lecture", "Lecture at 10:00 AM", "2023-05-22", 2, false, 1),
        Task(2, "Study for History Exam", "Chapter 3 and 4", "2023-05-23", 2, true, 1),
        Task(3, "Work Shift at Cafe", "4:00 PM to 8:00 PM", "2023-05-24", 3, false, 2),
        Task(4, "Buy Anniversary Gift", "Plan something special", "2023-06-05", 2, true, 1),
        Task(5, "Submit English Essay", "Word limit: 1500", "2023-06-10", 2, false, 1),
        Task(6, "Date Night", "Restaurant reservation at 7:00 PM", "2023-06-15", 2, true, 1),
        Task(7, "Complete Math Assignment", "Due by midnight", "2023-06-20", 3, false, 1),
        Task(
            8,
            "Part-time Job Interview",
            "Prepare for interview questions",
            "2023-06-25",
            2,
            true,
            3
        ),
        Task(9, "Work Shift at Cafe", "12:00 PM to 4:00 PM", "2023-07-01", 3, false, 2),
        Task(10, "Buy Groceries", "Make a shopping list", "2023-07-05", 2, true, 1)
    )
}