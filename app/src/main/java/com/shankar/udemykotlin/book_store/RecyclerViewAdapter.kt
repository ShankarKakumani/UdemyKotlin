package com.shankar.udemykotlin.book_store

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.shankar.udemykotlin.R

class RecyclerViewAdapter(private var list : MutableList<Int>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ViewHolder {
        val context = viewGroup.context
        val inflater = LayoutInflater.from(context)
        val shouldAttachToParentImmediately = false

        val view = inflater.inflate(R.layout.recycler_view_item, viewGroup, shouldAttachToParentImmediately)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v){

        private var view: View = v
        private var imageView : ImageView = view.findViewById(R.id.image_view)

        fun bind(image: Int) {
            imageView.setImageResource(image)

        }
    }
}