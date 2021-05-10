package com.shankar.udemykotlin.book_store

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.shankar.udemykotlin.R
import com.shankar.udemykotlin.databinding.ActivityProfileBinding
import de.hdodenhof.circleimageview.CircleImageView
import java.io.IOException

class ProfileActivity : AppCompatActivity() {

    companion object {
        private const val SELECT_IMAGE = 1
        const val SHARED_PREF = "mySharedPref"
        const val FIRST_NAME = "first_name"
        const val LAST_NAME = "last_name"
    }

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: ActivityProfileBinding
    private lateinit var profileImage: CircleImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.include))
        profileImage = binding.circleImageView
        val actionBar: ActionBar? = supportActionBar

        sharedPreferences = this.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE) ?: return
        actionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        profileImage.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_IMAGE)
        }

        binding.save.apply {
            setOnClickListener {
                with(sharedPreferences.edit()) {
                    putString(FIRST_NAME, binding.firstName.text.toString())
                    putString(LAST_NAME, binding.lastName.text.toString())
                    apply()
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {

        onBackPressed()
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SELECT_IMAGE) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    try {
                        val selectedImage = data.data
                        val yourSelectedImage =
                            BitmapFactory.decodeStream(contentResolver.openInputStream(selectedImage!!))
                        profileImage.setImageBitmap(yourSelectedImage)
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }
}