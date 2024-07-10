package com.junicodev.androidmaster.superheroapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.junicodev.androidmaster.databinding.ItemSuperheroBinding

class SuperHeroViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemSuperheroBinding.bind(view)
    fun bind(superHeroItemResponse: SuperHeroItemResponse) {
        binding.tvSuperHeroName.text = superHeroItemResponse.name
    }
}