package com.shankar.udemykotlin.book_store

import android.content.Context
import android.media.Image
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView

class TopListAdapter(private var topList : MutableList<Int>, private var context : Context) : BaseAdapter() {
    override fun getCount(): Int {
        return topList.size
    }

    override fun getItem(position: Int): Any? = null

    override fun getItemId(position: Int): Long = 0L

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val imageView : ImageView
        if(convertView == null) {
            imageView = ImageView(context)
            imageView.apply {
                layoutParams = ViewGroup.LayoutParams(250, 400)
                scaleType = ImageView.ScaleType.CENTER_CROP
                setPadding(8, 8, 8, 8)
            }

        }
        else {
            imageView = convertView as ImageView
        }
        imageView.setImageResource(topList[position])
        return imageView
    }
}