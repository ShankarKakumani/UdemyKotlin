package com.shankar.udemykotlin.firebase_ml

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.shankar.udemykotlin.R

class MachineLearning : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_machine_learning)

    }


    fun textRecognition(view: View) {
        startActivity(Intent(this, TextRecognition::class.java))
    }

    fun faceDetection(view: View) {
    }

    fun barcodeScanner(view: View) {
    }

    fun imageLabeling(view: View) {
    }
}