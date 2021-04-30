package com.shankar.udemykotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class CalculatorActivity : AppCompatActivity() {


    private lateinit var editText: EditText
    private var number1: Float = 0.0f
    private var isPlus: Boolean = false
    private var isMinus: Boolean = false
    private var isDivide: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        editText = findViewById(R.id.number_edit_text)
    }

    fun operationFunction(view: View) {


        when (view.id) {

            R.id.button_one -> {
                numberClicked(1)
            }

            R.id.button_two -> {
                numberClicked(2)
            }

            R.id.button_three -> {
                numberClicked(3)
            }

            R.id.button_four -> {
                numberClicked(4)
            }

            R.id.button_five -> {
                numberClicked(5)
            }

            R.id.button_six -> {
                numberClicked(6)
            }

            R.id.button_seven -> {
                numberClicked(7)
            }

            R.id.button_eight -> {
                numberClicked(8)
            }

            R.id.button_nine -> {
                numberClicked(9)
            }

            R.id.button_zero -> {
                numberClicked(0)
            }

            R.id.button_dot -> {
                dotClicked()
            }

            R.id.button_clear -> {
                editText.setText("")
            }

            R.id.button_plus -> {
                isPlus = true
                operatorClicked()

            }

            R.id.button_minus -> {
                isMinus = true
                operatorClicked()
            }

            R.id.button_divide -> {
                isDivide = true
                operatorClicked()

            }

            R.id.button_equal -> {
                isEqualClicked()
            }

        }
    }


    private fun numberClicked(numberClicked: Int) {
        val number = editText.text.toString() + numberClicked.toString()
        editText.setText(number)
    }

    private fun dotClicked() {
        val number = editText.text.toString() + "."
        editText.setText(number)
    }

    private fun operatorClicked()
    {
        number1 = editText.text.toString().toFloat()
        editText.setText("")
    }

    private fun isEqualClicked()
    {
        when {
            isPlus -> {
                doPlus()
            }
            isMinus -> {
                doMinus()
            }
            isDivide -> {
                doDivide()
            }
        }

    }


    private fun doPlus() {
        val number2 = editText.text.toString().toFloat()
        val result = number1 + number2
        editText.setText(result.toString())
        isPlus = false
    }

    private fun doMinus() {
        val number2 = editText.text.toString().toFloat()
        val result = number1 - number2
        editText.setText(result.toString())
        isMinus = false
    }

    private fun doDivide() {
        val number2 = editText.text.toString().toFloat()
        val result = number1 / number2
        editText.setText(result.toString())
        isDivide = false
    }
}