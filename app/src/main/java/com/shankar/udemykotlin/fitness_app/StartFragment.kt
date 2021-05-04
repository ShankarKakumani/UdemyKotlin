package com.shankar.udemykotlin.fitness_app

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.shankar.udemykotlin.R
import com.shankar.udemykotlin.databinding.FragmentStartBinding


class StartFragment : Fragment() {


    private lateinit var binding: FragmentStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentStartBinding.inflate(layoutInflater)

        binding.goButton.apply {

//            setOnClickListener {
//                Navigation.findNavController(it).navigate(R.id.action_startFragment_to_exerciseFragment2)
//            }

            setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_startFragment_to_exerciseFragment2)
            )
        }

        setHasOptionsMenu(true)

        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.overflow_menu, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController()) ||
        super.onOptionsItemSelected(item)

    }
}