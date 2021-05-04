package com.shankar.udemykotlin.fitness_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.shankar.udemykotlin.R
import com.shankar.udemykotlin.databinding.FragmentWellDoneBinding


class WellDoneFragment : Fragment() {

    private lateinit var binding: FragmentWellDoneBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentWellDoneBinding.inflate(layoutInflater)


        binding.nextWorkoutButton.apply {

            setOnClickListener {

                Navigation.findNavController(it).navigate(R.id.action_wellDoneFragment_to_exerciseFragment)
            }
        }




        return binding.root
    }


}