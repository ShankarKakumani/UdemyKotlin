package com.shankar.udemykotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.SeekBar
import com.shankar.udemykotlin.databinding.ActivityWidgetPracticeBinding

class WidgetPractice : AppCompatActivity() {

    private lateinit var binding: ActivityWidgetPracticeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWidgetPracticeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.ageTextView.text = String.format(resources.getString(R.string.str_age), 0)

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.ageTextView.text =
                    String.format(resources.getString(R.string.str_age), progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })


        ArrayAdapter.createFromResource(
            this,
            R.array.list_of_units,
            android.R.layout.simple_list_item_1
        ).also { arrayAdapter ->
            binding.spinner.adapter = arrayAdapter
        }

    }
}