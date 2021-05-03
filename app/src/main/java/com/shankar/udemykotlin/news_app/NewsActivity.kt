package com.shankar.udemykotlin.news_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.shankar.udemykotlin.databinding.ActivityNewsBinding
import org.json.JSONObject

class NewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsBinding
    private var pageNumber = 1
    private var list = mutableListOf<NewsData>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loadMoreButton.apply {
            setOnClickListener {
                pageNumber += 1
                getNews()
            }
        }

        binding.searchButton.setOnClickListener {
            list = mutableListOf()
            getNews()
        }
    }

    private fun getUrl(): String {
        val word = binding.searchEditText.text
        val apiKey = "12e7a07f-7cfc-4a66-8a0c-820fa7d071d4"
        val pageSize = 10

//        https://content.guardianapis.com/football?page=1&page-size=10&api-key=12e7a07f-7cfc-4a66-8a0c-820fa7d071d4

        return "https://content.guardianapis.com/$word?page=$pageNumber&page-size=$pageSize&api-key=$apiKey"

    }

    private fun extractJSON(response: String) {

        val jsonObject = JSONObject(response)
        val jsonResponseBody = jsonObject.getJSONObject("response")
        val results = jsonResponseBody.getJSONArray("results")


        for (i in 0..9) {

            val item = results.getJSONObject(i)

            val webTitle = item.getString("webTitle")
            val webUrl = item.getString("webUrl")
            val data = NewsData(webTitle, webUrl)

            list.add(data)
        }


        val newsAdapter = NewsAdapter(list)
        binding.listView.adapter = newsAdapter

    }

    private fun getNews() {

        val url = getUrl()

        val queue = Volley.newRequestQueue(this)

        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                try {
                    extractJSON(response)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            },
            { error ->
                Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
            })

        queue.add(stringRequest)
    }
}