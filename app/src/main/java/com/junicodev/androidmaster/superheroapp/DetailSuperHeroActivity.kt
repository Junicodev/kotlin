package com.junicodev.androidmaster.superheroapp

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.junicodev.androidmaster.databinding.ActivityDetailSuperHeroBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.roundToInt

class DetailSuperHeroActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var binding: ActivityDetailSuperHeroBinding
    private lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSuperHeroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRetrofit()
        val id: String = intent.getStringExtra(EXTRA_ID).orEmpty()
        getSuperHeroInformation(id)
    }

    private fun getSuperHeroInformation(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val superHeroResponse: Response<SuperHeroItemResponse> =
                retrofit.create(ApiService::class.java).getSuperHero(id)
            if (superHeroResponse.body() != null)
                runOnUiThread { createUI(superHeroResponse.body()!!) }
        }
    }

    private fun createUI(superHero: SuperHeroItemResponse) {
        Picasso.get().load(superHero.image.url).into(binding.ivSuperHero)
        binding.tvSuperHeroName.text = superHero.name
        binding.tvRealName.text = superHero.biography.fullName
        binding.tvPublisher.text = superHero.biography.publisher
        prepareStats(superHero.powerStats)
    }

    private fun prepareStats(powerStats: PowerStatsResponse) {
        updateHeight(binding.vCombat, powerStats.combat)
        updateHeight(binding.vSpeed, powerStats.speed)
        updateHeight(binding.vDurability, powerStats.durability)
        updateHeight(binding.vPower, powerStats.power)
        updateHeight(binding.vIntelligence, powerStats.intelligence)
        updateHeight(binding.vStrength, powerStats.strength)
    }

    private fun updateHeight(view: View, stat: String) {
        val params = view.layoutParams
        params.height = pixelToDp(stat.toFloat())
        view.layoutParams = params
    }

    private fun pixelToDp(px: Float) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, resources.displayMetrics).roundToInt()

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
}