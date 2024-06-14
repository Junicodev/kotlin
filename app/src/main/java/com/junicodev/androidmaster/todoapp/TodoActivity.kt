package com.junicodev.androidmaster.todoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.junicodev.androidmaster.R

class TodoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)
    }
}