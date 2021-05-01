package com.shankar.udemykotlin.dictionary_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.shankar.udemykotlin.databinding.ActivityDictionaryBinding
import org.json.JSONArray

class DictionaryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDictionaryBinding
    private val key = "DEFINITION"

    private val apiKey = "3d60c674-8f9b-4a06-8760-d485f8e85384"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDictionaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val queue = Volley.newRequestQueue(this)

        binding.findButton.setOnClickListener {

            val stringRequest = StringRequest(Request.Method.GET, getUrl(),
                { response ->
                    try {
                        extractDefinitionFromJson(response)
                    } catch (e: Exception) {
                        e.printStackTrace()
                        Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
                    }
                },
                { error ->
                    Toast.makeText(this, "${error.message}", Toast.LENGTH_SHORT).show()
                }
            )

            queue.add(stringRequest)
        }

    }

    private fun getUrl(): String {

        return "https://www.dictionaryapi.com/api/v3/references/learners/json/${binding.wordEditText.text}?key=$apiKey"
    }

    private fun extractDefinitionFromJson(response: String) {

        val jsonArray = JSONArray(response)
        val firstIndex = jsonArray.getJSONObject(0)
        val getShortDef = firstIndex.getJSONArray("shortdef")
        val firstShortDef = getShortDef.get(0)

        val intent = Intent(this, DefinitionActivity::class.java)
        intent.putExtra(key, firstShortDef.toString())
        startActivity(intent)
    }
}