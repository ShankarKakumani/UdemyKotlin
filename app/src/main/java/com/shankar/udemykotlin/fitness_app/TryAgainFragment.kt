package com.shankar.udemykotlin.fitness_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.shankar.udemykotlin.R
import com.shankar.udemykotlin.databinding.FragmentTryAgainBinding


class TryAgainFragment : Fragment() {

    private lateinit var binding: FragmentTryAgainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentTryAgainBinding.inflate(layoutInflater)


        binding.tryAgainButton.setOnClickListener(

            Navigation.createNavigateOnClickListener(R.id.action_tryAgainFragment_to_exerciseFragment)
        )

        return binding.root
    }


}