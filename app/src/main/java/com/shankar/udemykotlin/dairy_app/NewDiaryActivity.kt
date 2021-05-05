package com.shankar.udemykotlin.dairy_app

import android.content.ContentValues
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.shankar.udemykotlin.R
import com.shankar.udemykotlin.dairy_app.data.DatabaseManager.DiaryEntry.COLUMN_DATE
import com.shankar.udemykotlin.dairy_app.data.DatabaseManager.DiaryEntry.COLUMN_DIARY
import com.shankar.udemykotlin.dairy_app.data.DatabaseManager.DiaryEntry.COLUMN_TITLE
import com.shankar.udemykotlin.dairy_app.data.DatabaseManager.DiaryEntry.ID
import com.shankar.udemykotlin.dairy_app.data.DatabaseManager.DiaryEntry.TABLE_NAME
import com.shankar.udemykotlin.dairy_app.data.DiaryDBHelper
import com.shankar.udemykotlin.databinding.ActivityNewDiaryBinding
import java.text.SimpleDateFormat
import java.util.*

class NewDiaryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewDiaryBinding
    private var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewDiaryBinding.inflate(layoutInflater)

        setContentView(binding.root)

        id = intent.getIntExtra("IDofRow", 0)
        Log.d("TAG_DAIRY", "$id")

        if( id != 0) {
            readDiary(id)
        }

        val currentDate =  SimpleDateFormat("EEE, d MMM yyyy")
        binding.currentDateDiary.text = currentDate.format(Date())


    }

    private fun readDiary(id: Int) {

        val mDBHelper = DiaryDBHelper(this)
        val db = mDBHelper.readableDatabase

        val projection= arrayOf(COLUMN_DATE, COLUMN_TITLE, COLUMN_DIARY)

        val selection = "$ID = ?"
        val selectionArgs = arrayOf("$id")

        val cursor : Cursor = db.query(
            TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        val dateColumnIndex = cursor.getColumnIndexOrThrow(COLUMN_DATE)
        val titleColumnIndex = cursor.getColumnIndexOrThrow(COLUMN_TITLE)
        val diaryColumnIndex = cursor.getColumnIndexOrThrow(COLUMN_DIARY)


        while (cursor.moveToNext()) {

            val currentDate = cursor.getString(dateColumnIndex)
            val currentTitle = cursor.getString(titleColumnIndex)
            val currentDiary = cursor.getString(diaryColumnIndex)

            binding.currentDateDiary.text = currentDate
            binding.titleDiary.setText(currentTitle)
            binding.dairyText.setText(currentDiary)
        }

        cursor.close()
    }

    private fun updateDiary() {

        val mDbHelper = DiaryDBHelper(this)

        val db = mDbHelper.writableDatabase

        val values = ContentValues().apply {
            put(COLUMN_TITLE, binding.titleDiary.text.toString())
            put(COLUMN_DIARY, binding.dairyText.text.toString())
        }

        db.update(TABLE_NAME, values, "$ID = $id", null)
    }

    private fun insertDiary() {

        val dateString = binding.currentDateDiary.text.toString()
        val titleString = binding.titleDiary.text.toString().trim {it <= ' '}
        val dairyString = binding.dairyText.text.toString().trim { it <= ' ' }

        val mDbHelper = DiaryDBHelper(this)

        val db = mDbHelper.writableDatabase

        val values = ContentValues().apply {

            put(COLUMN_DATE, dateString)
            put(COLUMN_TITLE, titleString)
            put(COLUMN_DIARY, dairyString)
        }

        val rowId = db.insert(TABLE_NAME, null, values)

        if (rowId.equals(-1)) {
            Toast.makeText(this, "Problem in Inserting new Diary",Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Diary has been inserted $rowId",Toast.LENGTH_SHORT).show()

        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.diary_menu, menu)


        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.save_diary -> {

                if(id == 0) {
                    insertDiary()
                } else {
                    updateDiary()
                }
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

}