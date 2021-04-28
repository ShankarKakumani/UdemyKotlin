package com.shankar.udemykotlin.number_list

import android.content.Context
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.shankar.udemykotlin.R

class CustomAdapter(private var arrayList: ArrayList<Data>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val context = parent?.context
        var rowView: View? = convertView
        var mediaPlayer: MediaPlayer? = null
        val item = arrayList[position]

        val inflater: LayoutInflater =
            context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        if (rowView == null) {
            rowView = inflater.inflate(R.layout.item_list, parent, false)
        }

        val numberTextView = rowView?.findViewById<TextView>(R.id.number_text_view)
        val audioImageView = rowView?.findViewById<ImageView>(R.id.audio_image_view)



        numberTextView?.text = item.num
        audioImageView?.setOnClickListener {

            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(
                    context, context.resources.getIdentifier(
                        item.audioFileName,
                        "raw",
                        context.packageName
                    )
                )

            }

            mediaPlayer?.start()
        }

        return rowView!!
    }

    override fun getItem(position: Int) = arrayList[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getCount() = arrayList.size
}