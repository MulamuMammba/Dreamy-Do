package com.example.dreamydo.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.dreamydo.R
import com.example.dreamydo.utils.Time

class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard)

        val greeting: TextView = findViewById(R.id.greeting)
        val date: TextView = findViewById(R.id.date)

        greeting.text = Time.timeKeeping()
        date.text = Time.date()

    }

}