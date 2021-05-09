package com.shankar.udemykotlin.dairy_app

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shankar.udemykotlin.dairy_app.data.DatabaseManager.DiaryEntry.COLUMN_DATE
import com.shankar.udemykotlin.dairy_app.data.DatabaseManager.DiaryEntry.COLUMN_DIARY
import com.shankar.udemykotlin.dairy_app.data.DatabaseManager.DiaryEntry.COLUMN_TITLE
import com.shankar.udemykotlin.dairy_app.data.DatabaseManager.DiaryEntry.ID
import com.shankar.udemykotlin.dairy_app.data.DatabaseManager.DiaryEntry.TABLE_NAME
import com.shankar.udemykotlin.dairy_app.data.Diary
import com.shankar.udemykotlin.dairy_app.data.DiaryDBHelper
import com.shankar.udemykotlin.databinding.ActivityDairyBinding
import java.io.File

class DairyActivity : AppCompatActivity() {

    private lateinit var mDBHelper: DiaryDBHelper
    private var diaryList : ArrayList<Diary> = ArrayList()
    private lateinit var binding: ActivityDairyBinding

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DiaryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityDairyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        linearLayoutManager = LinearLayoutManager(this)
        recyclerView = binding.recyclerView

        recyclerView.layoutManager = linearLayoutManager

        adapter = DiaryAdapter(diaryList)
        recyclerView.adapter = adapter


        mDBHelper = DiaryDBHelper(this)
        displayDataInfo()
//        diaryList.add(Diary("2021", "First Diary", "My First Diary"))
//        diaryList.add(Diary("2020", "Second Diary", "My Second Diary"))
//        diaryList.add(Diary("2019", "Third Diary", "My Third Diary"))




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

    private fun displayDataInfo() {

        val db = mDBHelper.readableDatabase

        val projection = arrayOf(ID, COLUMN_DATE, COLUMN_TITLE, COLUMN_DIARY )

        val cursor : Cursor = db.query(TABLE_NAME, projection, null, null, null, null, null)

        val idColumnIndex = cursor.getColumnIndexOrThrow(ID)
        val dateColumnIndex = cursor.getColumnIndexOrThrow(COLUMN_DATE)
        val titleColumnIndex = cursor.getColumnIndexOrThrow(COLUMN_TITLE)
        val dairyColumnIndex = cursor.getColumnIndexOrThrow(COLUMN_DIARY)

        while (cursor.moveToNext()) {

            val currentId = cursor.getInt(idColumnIndex)
            val currentDate = cursor.getString(dateColumnIndex)
            val currentTitle = cursor.getString(titleColumnIndex)
            val currentDiary = cursor.getString(dairyColumnIndex)

            diaryList.add(Diary(currentId, currentDate, currentTitle, currentDiary))
        }

        cursor.close()
        adapter.notifyDataSetChanged()
    }

    override fun onStart() {
        super.onStart()
        diaryList.clear()
        displayDataInfo()

    }
}