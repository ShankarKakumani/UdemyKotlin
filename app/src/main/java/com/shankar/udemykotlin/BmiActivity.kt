package com.shankar.udemykotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.shankar.udemykotlin.databinding.ActivityBmiBinding

class BmiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBmiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    fun calculateBmi() {

        if (binding.weightEditText.text.isNotEmpty() && binding.heightEditText.text.isNotEmpty()) {

            bmiFunctionality()
        } else {
            Toast.makeText(this, "Weight or height is Empty", Toast.LENGTH_SHORT).show()
        }

    }

    private fun bmiFunctionality() {

        val weight = binding.weightEditText.text.toString().toFloat()
        val height = binding.heightEditText.text.toString().toFloat()

        val bmi: Float = weight / (height * height)

        binding.bmiTextView.text = bmi.toString()


        val image1 = when (bmi.toInt()) {

            in 0..19 -> R.drawable.ic_underweight
            in 20..24 -> R.drawable.ic_healthy
            in 25..30 -> R.drawable.ic_overweight
            in 31..100 -> R.drawable.ic_obesity
            else -> R.drawable.ic_obesity
        }

        binding.bmiImage.setImageResource(image1)
    }
}