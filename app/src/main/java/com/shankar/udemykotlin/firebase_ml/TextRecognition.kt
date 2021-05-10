package com.shankar.udemykotlin.firebase_ml

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import com.shankar.udemykotlin.R
import java.io.File
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class TextRecognition : AppCompatActivity() {

    var currentPhotoPath : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_recognition)
    }


    fun cameraTextRecognition(view : View) {
        dispatchTakePictureIntent()
    }

    private fun dispatchTakePictureIntent() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.resolveActivity(packageManager)

        val photoFile : File? = try {
            createImageFile()
        }catch (e : Exception)
        {
            e.printStackTrace()
            null
        }


    }

    private fun createImageFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_hhmmss").format(Date())
        val storageDirectory : File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("image_$timeStamp", ".jpg", storageDirectory).apply {
            currentPhotoPath = this.absolutePath
        }
    }
}