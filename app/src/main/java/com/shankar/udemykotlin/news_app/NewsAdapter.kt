package com.shankar.udemykotlin.news_app

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.shankar.udemykotlin.R

class NewsAdapter(private val listOfNews : MutableList<NewsData>) : BaseAdapter(){
    override fun getCount(): Int {
        return listOfNews.size
    }

    override fun getItem(position: Int): Any {
        return listOfNews[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val context = parent?.context
        var rowView = convertView

        val inflater : LayoutInflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        if(rowView == null) {
            rowView = inflater.inflate(R.layout.list_item_news, parent, false)
        }

        val item = listOfNews[position]

        val title = rowView?.findViewById<TextView>(R.id.title_text_view)

        title?.apply {
            text = item.webTitle
            setOnClickListener {

                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.webUrl))
                context.startActivity(intent)
            }
        }

        return rowView!!
    }
}