package com.junicodev.androidmaster.todoapp

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.junicodev.androidmaster.R

class CategoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val tvCategoryName: TextView = view.findViewById(R.id.tvCategoryName)
    private val divider: View = view.findViewById(R.id.divider)
    private val vcTaskCategory: CardView = view.findViewById(R.id.vcTaskCategory)

    fun render(taskCategory: TaskCategory, onItemSelected: (Int) -> Unit) {

        itemView.setOnClickListener { onItemSelected(layoutPosition) }

        val color =
            if (taskCategory.isSelected)
                R.color.todo_background_card
            else
                R.color.todo_background_disabled

        vcTaskCategory.setCardBackgroundColor(ContextCompat.getColor(vcTaskCategory.context, color))

        when (taskCategory) {
            TaskCategory.Business -> {
                tvCategoryName.text = tvCategoryName.context.getString(R.string.business)
                divider.setBackgroundColor(
                    ContextCompat.getColor(
                        divider.context,
                        R.color.todo_business_category
                    )
                )
            }

            TaskCategory.Other -> {
                tvCategoryName.text = tvCategoryName.context.getString(R.string.other)
                divider.setBackgroundColor(
                    ContextCompat.getColor(
                        divider.context,
                        R.color.todo_other_category
                    )
                )
            }

            TaskCategory.Personal -> {
                tvCategoryName.text = tvCategoryName.context.getString(R.string.personal)
                divider.setBackgroundColor(
                    ContextCompat.getColor(
                        divider.context,
                        R.color.todo_personal_category
                    )
                )
            }
        }
    }
}