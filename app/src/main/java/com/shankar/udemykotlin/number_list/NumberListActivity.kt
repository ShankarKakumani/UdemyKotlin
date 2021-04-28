package com.shankar.udemykotlin.number_list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shankar.udemykotlin.databinding.ActivityNumberListBinding

class NumberListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNumberListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNumberListBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        val array = arrayOf(
//            "1",
//            "2",
//            "3",
//            "4",
//            "5",
//            "6",
//            "7",
//            "8",
//            "9",
//            "10",
//            "11",
//            "12",
//            "13",
//            "14",
//            "15",
//            "16",
//            "17",
//            "18",
//            "19",
//            "20"
//        )


//        val adapter = ArrayAdapter<String> (this, android.R.layout.simple_list_item_1, array)
//        binding.listView.adapter = adapter


        val arrayList: ArrayList<Data> = ArrayList()

        arrayList.add(Data("0", "audio_0"))
        arrayList.add(Data("1", "audio_1"))
        arrayList.add(Data("2", "audio_2"))
        arrayList.add(Data("3", "audio_3"))
        arrayList.add(Data("4", "audio_4"))
        arrayList.add(Data("5", "audio_5"))
        arrayList.add(Data("6", "audio_6"))
        arrayList.add(Data("7", "audio_7"))
        arrayList.add(Data("8", "audio_8"))
        arrayList.add(Data("9", "audio_9"))
        arrayList.add(Data("10", "audio_10"))


        val customAdapter = CustomAdapter(arrayList)
        binding.listView.adapter = customAdapter

    }
}