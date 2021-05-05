package com.shankar.udemykotlin.dairy_app

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shankar.udemykotlin.R
import com.shankar.udemykotlin.dairy_app.data.DatabaseManager.DiaryEntry.ID
import com.shankar.udemykotlin.dairy_app.data.DatabaseManager.DiaryEntry.TABLE_NAME
import com.shankar.udemykotlin.dairy_app.data.Diary
import com.shankar.udemykotlin.dairy_app.data.DiaryDBHelper

class DiaryAdapter(private var diaryList: MutableList<Diary>) :
    RecyclerView.Adapter<DiaryAdapter.DiaryViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup, position: Int
    ): DiaryViewHolder {


        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val shouldAttachToParentImmediately = false

        val view =
            inflater.inflate(R.layout.recycler_diary_item, parent, shouldAttachToParentImmediately)

        view.findViewById<ImageButton>(R.id.delete_diary).apply {

            setOnClickListener {
                val mDBHelper = DiaryDBHelper(view.context)
                val db = mDBHelper.writableDatabase
                val selection = "$ID = ?"
                val selectionArgs = arrayOf("${(diaryList[position].id)}")

                db.delete(TABLE_NAME, selection, selectionArgs)

                diaryList.removeAt(position)
                notifyDataSetChanged()
            }

        }
        return DiaryViewHolder(view)
    }

    override fun onBindViewHolder(holder: DiaryViewHolder, position: Int) {

        val item = diaryList[position]
        holder.bindDiary(item)
    }

    override fun getItemCount(): Int {
        return diaryList.size
    }

    class DiaryViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        private var view: View = v
        private lateinit var diary: Diary

        private var date: TextView
        private var title: TextView

        init {
            view = v

            date = v.findViewById(R.id.date_recycler_item)
            title = v.findViewById(R.id.title_recycler_view)
            v.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

            val context = itemView.context
            val intent = Intent(context, NewDiaryActivity::class.java)
            intent.putExtra("IDofRow", diary.id)
            context.startActivity(intent)

        }


        fun bindDiary(diary: Diary) {

            this.diary = diary

            date.text = diary.date
            title.text = diary.title
        }
    }
}