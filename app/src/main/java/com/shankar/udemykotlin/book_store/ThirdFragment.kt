package com.shankar.udemykotlin.book_store

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.shankar.udemykotlin.R
import com.shankar.udemykotlin.databinding.FragmentThirdBinding

class ThirdFragment : Fragment() {

    private lateinit var binding: FragmentThirdBinding
    private var fictionList: MutableList<Int> = mutableListOf(
        R.drawable.fiction_one, R.drawable.fiction_two,
        R.drawable.fiction_three, R.drawable.fiction_four,
        R.drawable.fiction_five, R.drawable.fiction_six,
        R.drawable.fiction_seven, R.drawable.fiction_eight,
        R.drawable.fiction_nine, R.drawable.fiction_ten,
        R.drawable.fiction_one, R.drawable.fiction_two,
        R.drawable.fiction_three, R.drawable.fiction_four,
        R.drawable.fiction_five, R.drawable.fiction_six,
        R.drawable.fiction_seven, R.drawable.fiction_eight,
        R.drawable.fiction_nine, R.drawable.fiction_ten
    )

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var staggeredGridLayoutManager: StaggeredGridLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentThirdBinding.inflate(layoutInflater)

        recyclerView = binding.recyclerView
        recyclerViewAdapter = RecyclerViewAdapter(fictionList)
        staggeredGridLayoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.apply {
            layoutManager = staggeredGridLayoutManager
            adapter = recyclerViewAdapter
        }

        return binding.root
    }

}