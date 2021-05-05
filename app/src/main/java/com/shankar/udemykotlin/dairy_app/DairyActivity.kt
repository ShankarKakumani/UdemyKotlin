package com.shankar.udemykotlin.dairy_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shankar.udemykotlin.R
import com.shankar.udemykotlin.databinding.ActivityDairyBinding

class DairyActivity : AppCompatActivity() {

    private var diaryList : ArrayList<Diary> = ArrayList()
    private lateinit var binding: ActivityDairyBinding

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DiaryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDairyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        diaryList.add(Diary("2021", "First Diary", "My First Diary"))
        diaryList.add(Diary("2020", "Second Diary", "My Second Diary"))
        diaryList.add(Diary("2019", "Third Diary", "My Third Diary"))


        linearLayoutManager = LinearLayoutManager(this)
        recyclerView = binding.recyclerView

        recyclerView.layoutManager = linearLayoutManager

        adapter = DiaryAdapter(diaryList)
        recyclerView.adapter = adapter


        binding.floatingActionButton.apply {

            setOnClickListener {
                createNewDiary()
            }
        }



    }

    private fun createNewDiary() {

        val intent = Intent(this, NewDiaryActivity::class.java)
        startActivity(intent)

    }
}