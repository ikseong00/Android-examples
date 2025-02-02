package com.ikseong.cameraxphotopickerexample

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ikseong.cameraxphotopickerexample.databinding.ActivityPhotoPickerBinding

class PhotoPickerActivity : AppCompatActivity() {

    private val binding by lazy { ActivityPhotoPickerBinding.inflate(layoutInflater) }
    private lateinit var pickMedia: ActivityResultLauncher<PickVisualMediaRequest>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setPicker()

        binding.btnSelectSingleImage.setOnClickListener {
            pickSingleImage()
        }

        binding.btnSelectVideo.setOnClickListener {
            pickSingleVideo()
        }

    }

    private fun setPicker() {
        pickMedia =
            registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
                // Callback is invoked after the user selects a media item or closes the
                // photo picker.
                if (uri != null) {
                    Log.d("PhotoPicker", "Selected URI: $uri")
                    binding.ivPickedImage.setImageURI(uri)
                } else {
                    Log.d("PhotoPicker", "No media selected")
                }
            }
    }

    private fun pickSingleImage() {
        // Launch the photo picker and let the user choose only images.
        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private fun pickSingleVideo() {
        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.VideoOnly))
    }
}