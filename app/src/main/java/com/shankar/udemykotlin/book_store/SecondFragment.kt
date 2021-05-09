package com.shankar.udemykotlin.book_store

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shankar.udemykotlin.R
import com.shankar.udemykotlin.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding
    private var childrenList: MutableList<Int> = mutableListOf(
        R.drawable.children_one, R.drawable.children_two,
        R.drawable.children_three, R.drawable.children_four,
        R.drawable.children_five, R.drawable.children_six,
        R.drawable.children_seven, R.drawable.children_eight,
        R.drawable.children_nine, R.drawable.children_ten,
        R.drawable.children_one, R.drawable.children_two,
        R.drawable.children_three, R.drawable.children_four,
        R.drawable.children_five, R.drawable.children_six,
        R.drawable.children_seven, R.drawable.children_eight,
        R.drawable.children_nine, R.drawable.children_ten
    )

    private lateinit var recyclerView: RecyclerView
    private lateinit var childrenAdapter: RecyclerViewAdapter
    private lateinit var gridLayoutManager: GridLayoutManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSecondBinding.inflate(layoutInflater)


        recyclerView = binding.recyclerView
        childrenAdapter = RecyclerViewAdapter(childrenList)
        gridLayoutManager = GridLayoutManager(context, 3)

        recyclerView.apply {
            layoutManager = gridLayoutManager
            adapter = childrenAdapter
        }

        return binding.root
    }

}