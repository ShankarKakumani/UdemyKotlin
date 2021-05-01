package com.shankar.udemykotlin.dictionary_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shankar.udemykotlin.R
import com.shankar.udemykotlin.databinding.ActivityDefinitionBinding

class DefinitionActivity : AppCompatActivity() {

    private val key = "DEFINITION"
    private lateinit var binding: ActivityDefinitionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDefinitionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.definitionTextView.text = intent.getStringExtra(key)

        binding.backImageView.setOnClickListener {
            finish()
        }
    }
}