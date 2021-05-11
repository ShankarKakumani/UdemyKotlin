package com.shankar.udemykotlin.animation

import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.shankar.udemykotlin.R
import com.shankar.udemykotlin.databinding.ActivityPlaceHolderBinding

class PlaceHolderExample : AppCompatActivity() {

    private lateinit var binding : ActivityPlaceHolderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlaceHolderBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun answerNAction(view : View){
        setPlaceHolder(R.id.answerN)

    }
    fun answerOAction(view : View){
        setPlaceHolder(R.id.answerO)
    }
    fun answerPAction(view : View){
        setPlaceHolder(R.id.answerP)
    }
    fun answerQAction(view : View){
        setPlaceHolder(R.id.answerQ)
    }

    private fun setPlaceHolder(id : Int){
        val transition = ChangeBounds()
        transition.interpolator = OvershootInterpolator()
        transition.duration = 400
        TransitionManager.beginDelayedTransition(binding.constraintLayoutPlaceHolder, transition)
        binding.placeHolder.setContentId(id)

    }
}
