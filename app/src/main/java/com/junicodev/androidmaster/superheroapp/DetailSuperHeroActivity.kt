package com.junicodev.androidmaster.superheroapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.junicodev.androidmaster.R

class DetailSuperHeroActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_super_hero)


    }
}