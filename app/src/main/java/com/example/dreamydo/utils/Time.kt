package com.example.dreamydo.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class Time {

    companion object {
        fun timeKeeping(): String {
            //get current time
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)

            //Check if it's morning, afternoon or evening
            var message = "Good"

            message = when (hour) {
                in 6..12 -> {
                    "$message \nMorning"
                }

                in 12..18 -> {
                    "$message \nAfternoon"
                }

                else -> {
                    "$message \nEvening"
                }
            }

            return message
        }

        fun date(): String {
            //get current date
            val calendar = Calendar.getInstance()
            val dateFormat = SimpleDateFormat("EEEE, d MMMM yyyy", Locale.getDefault())
            val date = dateFormat.format(calendar.time)

            return date
        }
    }
}