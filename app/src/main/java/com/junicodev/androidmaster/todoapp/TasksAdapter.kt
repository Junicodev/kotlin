package com.junicodev.androidmaster.todoapp

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TasksAdapter(private val tasks: List<Task>) : RecyclerView.Adapter<TasksViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount() = tasks.size

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        holder.render(tasks[position])
    }

}