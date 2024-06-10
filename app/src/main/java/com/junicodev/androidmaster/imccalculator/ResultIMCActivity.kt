package com.junicodev.androidmaster.imccalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.junicodev.androidmaster.R
import com.junicodev.androidmaster.imccalculator.ImcCalculatorActivity.Companion.IMC_KEY

class ResultIMCActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_imcactivity)
        val result:String = intent.extras?.getString(IMC_KEY) ?: "-1.0"
    }
}