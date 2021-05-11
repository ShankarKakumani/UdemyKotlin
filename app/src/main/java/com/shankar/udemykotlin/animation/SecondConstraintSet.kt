package com.shankar.udemykotlin.animation

import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.util.Log
import android.view.View
import android.view.animation.AnticipateOvershootInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.shankar.udemykotlin.R
import com.shankar.udemykotlin.databinding.ActivitySecondConstraintSetBinding

class SecondConstraintSet : AppCompatActivity() {

    companion object {
        private const val TAG = "ConstraintSetTwo"
    }

    private var showBigImage = false

    private var rootLayout : ConstraintLayout? = null

    private val constraintSetNormal = ConstraintSet()

    private val constraintSetBig = ConstraintSet()

    private lateinit var binding : ActivitySecondConstraintSetBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivitySecondConstraintSetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rootLayout = findViewById(R.id.second_constraint_set)

        constraintSetNormal.clone(rootLayout!!)

        constraintSetBig.load(this@SecondConstraintSet, R.layout.activity_second_constraint_set_end)
    }


    fun toggledMode(view : View){

        val transition = ChangeBounds()

        transition.interpolator = AnticipateOvershootInterpolator(1.0f)
        transition.duration = 1200

        TransitionManager.beginDelayedTransition(rootLayout!!, transition)

        showBigImage = !showBigImage

        applyConfig()
    }

    private fun applyConfig(){
        if (showBigImage){
            Log.d(TAG,"big")
            constraintSetBig.applyTo(rootLayout!!)
        }else{
            Log.d(TAG,"small")
            constraintSetNormal.applyTo(rootLayout!!)
        }
    }
}
