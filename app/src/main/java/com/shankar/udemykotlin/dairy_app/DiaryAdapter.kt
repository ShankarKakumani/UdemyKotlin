package com.shankar.udemykotlin.dairy_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shankar.udemykotlin.R

class DiaryAdapter(private var diaryList: MutableList<Diary>) : RecyclerView.Adapter<DiaryAdapter.DiaryViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): DiaryViewHolder {


        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val shouldAttachToParentImmediately = false

        val view = inflater.inflate(R.layout.recycler_diary_item, parent, shouldAttachToParentImmediately)

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

        private var date : TextView
        private var title : TextView

        init {
            view = v

            date = v.findViewById(R.id.date_recycler_item)
            title = v.findViewById(R.id.title_recycler_view)
            v.setOnClickListener(this)
        }

        override fun onClick(v: View?) {


        }


        fun bindDiary(diary : Diary) {

            this.diary = diary

            date.text = diary.date
            title.text = diary.title
        }
    }
}