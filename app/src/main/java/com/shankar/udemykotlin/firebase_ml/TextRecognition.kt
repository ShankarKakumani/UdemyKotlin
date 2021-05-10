package com.shankar.udemykotlin.firebase_ml

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import androidx.core.content.FileProvider
import com.shankar.udemykotlin.R
import com.shankar.udemykotlin.databinding.ActivityTextRecognitionBinding
import com.shankar.udemykotlin.databinding.ImageTextRecognitionBinding
import java.io.File
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class TextRecognition : AppCompatActivity() {

    companion object {
        const val TAKE_PICTURE = 1
    }
    var currentPhotoPath: String? = null

    private lateinit var binding : ActivityTextRecognitionBinding
    private lateinit var imageViewBinding : ImageTextRecognitionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTextRecognitionBinding.inflate(layoutInflater)
        imageViewBinding = binding.imageLayout
        setContentView(binding.root)
    }


    fun cameraTextRecognition(view: View) {
        dispatchTakePictureIntent()
    }

    private fun dispatchTakePictureIntent() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.resolveActivity(packageManager)

        val photoFile: File? = try {
            createImageFile()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }


        val photoUri: Uri = FileProvider
            .getUriForFile(this, "com.shankar.udemykotlin.fileprovider",
            photoFile!!)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
        startActivityForResult(intent, TAKE_PICTURE)

    }

    private fun createImageFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_hhmmss").format(Date())
        val storageDirectory: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("image_$timeStamp", ".jpg", storageDirectory).apply {
            currentPhotoPath = this.absolutePath
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == TAKE_PICTURE)
        {
            if(resultCode == RESULT_OK) {
                val options = BitmapFactory.Options()
                options.inPreferredConfig = Bitmap.Config.ARGB_8888
                val bitmap = BitmapFactory.decodeFile(currentPhotoPath, options)

                val ei = ExifInterface(currentPhotoPath!!)

                val orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED)

                var rotatedBitmap : Bitmap? = null
                rotatedBitmap = when(orientation) {
                    ExifInterface.ORIENTATION_ROTATE_90 -> rotatedImage(bitmap, 90F)
                    ExifInterface.ORIENTATION_ROTATE_180 -> rotatedImage(bitmap, 180F)
                    ExifInterface.ORIENTATION_ROTATE_270 -> rotatedImage(bitmap, 270F)
                    else -> bitmap
                }

                imageViewBinding.imageTextRecognition.setImageBitmap(rotatedBitmap)

            }
        }
    }

    private fun rotatedImage(bitmap: Bitmap, angle: Float): Bitmap {

        val matrix = Matrix()
        matrix.postRotate(angle)

        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }
}