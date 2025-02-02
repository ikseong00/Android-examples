package com.ikseong.cameraxphotopickerexample

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.ikseong.cameraxphotopickerexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(viewBinding.root)

        viewBinding.btnCameraX.setOnClickListener {
            val intent = Intent(this, CameraXActivity::class.java)
            startActivity(intent)
        }

        viewBinding.btnPhotoPicker.setOnClickListener {
            val intent = Intent(this, PhotoPickerActivity::class.java)
            startActivity(intent)
        }

    }
}