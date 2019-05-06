package com.galleryimagepicker

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Selecting a picture from the gallery.
 * @author Aliaksandr Shapkin
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        galleryPickButton.setOnClickListener {
            pickImage();
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GALLERY_REQUEST && resultCode == Activity.RESULT_OK){
                val galleryImageUri = data?.data
                galleryImageView.setImageURI(galleryImageUri)
        }
    }

    private fun pickImage(){
        val intent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        intent.resolveActivity(packageManager)?.let {
            startActivityForResult(
                intent, GALLERY_REQUEST
            )
        }
    }
    private companion object {
        private const val GALLERY_REQUEST : Int = 1
    }
}
