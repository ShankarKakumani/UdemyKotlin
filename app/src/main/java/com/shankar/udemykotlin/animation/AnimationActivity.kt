package com.shankar.udemykotlin.animation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.shankar.udemykotlin.R

class AnimationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)


    }

    fun constraintSetFirstExample(view : View){}
    fun constraintSetSecondExample(view: View){}
    fun placeHolderExample(view: View){}
    fun circlePositioning(view: View){}
}