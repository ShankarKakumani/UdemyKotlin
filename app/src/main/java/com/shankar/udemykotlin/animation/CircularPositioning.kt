package com.shankar.udemykotlin.animation

import android.animation.ValueAnimator
import android.os.Bundle
import android.transition.TransitionManager
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.shankar.udemykotlin.R
import com.shankar.udemykotlin.databinding.ActivityCircularPositioningBinding
import java.util.concurrent.TimeUnit

class CircularPositioning : AppCompatActivity() {

    private lateinit var binding: ActivityCircularPositioningBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCircularPositioningBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val moonAnimator = animateMoon(binding.moonImage, TimeUnit.SECONDS.toMillis(2))

        updateConstraint(R.layout.activity_circular_positioning)

        moonAnimator.start()

    }

    private fun updateConstraint(@LayoutRes id : Int){
        val newConstraintSet = ConstraintSet()
        newConstraintSet.clone(this, id)
        newConstraintSet.applyTo(binding.rootView)
        TransitionManager.beginDelayedTransition(binding.rootView)
    }

    private fun animateMoon(moon : ImageView?, orbitDuration : Long):ValueAnimator{

        val anim = ValueAnimator.ofInt(0,359)
        anim.addUpdateListener { valueAnimator ->
            val value = valueAnimator.animatedValue as Int
            val layoutParameters = moon?.layoutParams as ConstraintLayout.LayoutParams
            layoutParameters.circleAngle = value.toFloat()
            moon.layoutParams = layoutParameters

            anim.duration = orbitDuration
            anim.interpolator = LinearInterpolator()
            anim.repeatMode = ValueAnimator.RESTART
            anim.repeatCount = ValueAnimator.INFINITE
        }
        return anim
    }
}
