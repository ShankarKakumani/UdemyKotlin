package com.shankar.udemykotlin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.shankar.udemykotlin.databinding.ActivityViewBindingBinding

class ViewBindingActivity : AppCompatActivity() {


    lateinit var binding: ActivityViewBindingBinding


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityViewBindingBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.clickButton.apply {
//            setOnClickListener{
//
//            }
//        }

//        var msms : String = binding.clickButton.run {
//
//            return@run "skjs"
//        }


        binding.clickButton.setOnClickListener {



            Toast.makeText(this, "Button Clicked !", Toast.LENGTH_SHORT).show()
        }


    }
}