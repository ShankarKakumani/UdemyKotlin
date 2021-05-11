package com.shankar.udemykotlin.firebase_ml

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.shankar.udemykotlin.databinding.ActivityTextRecognitionBinding
import com.shankar.udemykotlin.databinding.ImageTextRecognitionBinding
import com.shankar.udemykotlin.databinding.TextRecognitionBottomSheetBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

import com.google.mlkit.vision.text.TextRecognition

class TextRecognition : AppCompatActivity() {

    companion object {
        private const val TAKE_PICTURE = 1
        private const val SELECT_PICTURE = 2
    }

    var currentPhotoPath: String? = null

    var bottomSheetBehavior : BottomSheetBehavior<*>? = null

    private lateinit var binding: ActivityTextRecognitionBinding
    private lateinit var imageViewBinding: ImageTextRecognitionBinding
    private lateinit var textBottomSheetBinding: TextRecognitionBottomSheetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTextRecognitionBinding.inflate(layoutInflater)
        imageViewBinding = binding.imageLayout
        textBottomSheetBinding = binding.bottomSheetLayout

        bottomSheetBehavior = BottomSheetBehavior.from(textBottomSheetBinding.bottomSheet)
        setContentView(binding.root)
    }


    fun galleryTextRecognition(view: View) {
        val selectPicture = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(selectPicture, SELECT_PICTURE)
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
            .getUriForFile(
                this, "com.shankar.udemykotlin.fileprovider",
                photoFile!!
            )
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

         if (resultCode == RESULT_OK) {

             bottomSheetBehavior?.state = BottomSheetBehavior.STATE_COLLAPSED

             if (requestCode == TAKE_PICTURE) {
                 val options = BitmapFactory.Options()
                 options.inPreferredConfig = Bitmap.Config.ARGB_8888
                 val bitmap = BitmapFactory.decodeFile(currentPhotoPath, options)

                 val ei = ExifInterface(currentPhotoPath!!)

                 val orientation = ei.getAttributeInt(
                     ExifInterface.TAG_ORIENTATION,
                     ExifInterface.ORIENTATION_UNDEFINED
                 )

                 var rotatedBitmap: Bitmap? = null
                 rotatedBitmap = when (orientation) {
                     ExifInterface.ORIENTATION_ROTATE_90 -> rotatedImage(bitmap, 90F)
                     ExifInterface.ORIENTATION_ROTATE_180 -> rotatedImage(bitmap, 180F)
                     ExifInterface.ORIENTATION_ROTATE_270 -> rotatedImage(bitmap, 270F)
                     else -> bitmap
                 }

                 imageViewBinding.imageTextRecognition.setImageBitmap(rotatedBitmap)
                 runTextRecognition(rotatedBitmap!!)

             }

        }
        else if (requestCode == SELECT_PICTURE) {

            if(resultCode == RESULT_OK)
            {
                if(data != null) {
                    val selectedPicture = data.data
                    val selectedPictureBitmap =
                        BitmapFactory.decodeStream(contentResolver.openInputStream(selectedPicture!!))
                    imageViewBinding.imageTextRecognition.setImageBitmap(selectedPictureBitmap)

                    runTextRecognition(selectedPictureBitmap)

                }
            }

        }
    }

    private fun runTextRecognition(bitmap: Bitmap) {
        val image = InputImage.fromBitmap(bitmap, 0)
        val recognizer = TextRecognition.getClient()


        recognizer.process(image).addOnSuccessListener {
            processTextRecognitionResult(it)

        }.addOnFailureListener{
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()

        }
    }

    private fun processTextRecognitionResult(result: Text) {

        val blocks = result.textBlocks
        if(blocks.size == 0) {
            Toast.makeText(this, "No text Recognized", Toast.LENGTH_SHORT).show()
            return
        }
        var blockText = ""
        for(block in blocks ) {

//            for(line in block.lines) {
//                for(element in line.elements) {
//                }
//            }
            blockText += block.text

        }
        bottomSheetBehavior?.state = BottomSheetBehavior.STATE_HALF_EXPANDED

        textBottomSheetBinding.recognizedText.text = blockText

    }

    private fun rotatedImage(bitmap: Bitmap, angle: Float): Bitmap {

        val matrix = Matrix()
        matrix.postRotate(angle)

        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }
}