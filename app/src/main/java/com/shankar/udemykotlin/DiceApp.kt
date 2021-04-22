package com.shankar.udemykotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class DiceApp : AppCompatActivity() {

    private lateinit var diceOneImg : ImageView
    private lateinit var diceTwoImg : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton = findViewById<Button>(R.id.roll_dice_button)
        diceOneImg = findViewById(R.id.dice_image_one)
        diceTwoImg = findViewById(R.id.dice_image_two)

        rollButton.setOnClickListener {
         rollDice()
        }


    }

    private fun rollDice()
    {

        val randomNumber1 = (1..6).random()

        val image1 =  when(randomNumber1) {
            1 -> R.drawable.dice1
            2 -> R.drawable.dice2
            3 -> R.drawable.dice3
            4 -> R.drawable.dice4
            5 -> R.drawable.dice5
            6 -> R.drawable.dice6
            else -> R.drawable.dice_empty
        }

        diceOneImg.setImageResource(image1)

        val randomNumber2 = (1..6).random()

        val image2 =  when(randomNumber2) {
            1 -> R.drawable.dice1
            2 -> R.drawable.dice2
            3 -> R.drawable.dice3
            4 -> R.drawable.dice4
            5 -> R.drawable.dice5
            6 -> R.drawable.dice6
            else -> R.drawable.dice_empty
        }

        diceTwoImg.setImageResource(image2)
    }
}