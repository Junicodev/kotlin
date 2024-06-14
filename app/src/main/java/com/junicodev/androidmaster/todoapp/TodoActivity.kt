package com.junicodev.androidmaster.todoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.junicodev.androidmaster.R

class TodoActivity : AppCompatActivity() {

    private lateinit var rvCategories:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)
        initComponent()
        initUI()
    }

    private fun initComponent() {
        rvCategories = findViewById(R.id.rvCategories)
    }

    private fun initUI() {

    }
}