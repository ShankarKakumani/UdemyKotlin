package com.shankar.udemykotlin.animation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.shankar.udemykotlin.R

class AnimationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)


    }

    fun constraintSetFirstExample(view: View) {
        startActivity(Intent(this@AnimationActivity, FirstConstraintSet::class.java))
    }

    fun constraintSetSecondExample(view: View) {
        startActivity(Intent(this@AnimationActivity, SecondConstraintSet::class.java))
    }

    fun placeHolderExample(view: View) {
        startActivity(Intent(this@AnimationActivity, PlaceHolderExample::class.java))
    }

    fun circlePositioning(view: View) {
        startActivity(Intent(this@AnimationActivity, CircularPositioning::class.java))
    }
}