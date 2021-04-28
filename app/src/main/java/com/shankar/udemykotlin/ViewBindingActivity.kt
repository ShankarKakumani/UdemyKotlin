package com.shankar.udemykotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.shankar.udemykotlin.databinding.ActivityCalculatorBinding.inflate
import com.shankar.udemykotlin.databinding.ActivityMainBinding
import com.shankar.udemykotlin.databinding.ActivityViewBindingBinding

class ViewBindingActivity : AppCompatActivity() {


    lateinit var binding : ActivityViewBindingBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewBindingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.clickButton.setOnClickListener {
            Toast.makeText(this,"Button Clicked !",Toast.LENGTH_SHORT).show()
        }



        
    }
}