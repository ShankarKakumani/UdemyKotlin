package com.shankar.udemykotlin.fitness_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.shankar.udemykotlin.R
import com.shankar.udemykotlin.databinding.ActivityFitnessBinding

class FitnessActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFitnessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFitnessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = this.findNavController(R.id.my_nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }


    override fun onSupportNavigateUp(): Boolean {
        
        val navController = this.findNavController(R.id.my_nav_host_fragment)
        return navController.navigateUp()
    }
}