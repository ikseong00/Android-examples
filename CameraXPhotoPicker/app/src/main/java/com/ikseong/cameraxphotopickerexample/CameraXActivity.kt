package com.ikseong.cameraxphotopickerexample

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ikseong.cameraxphotopickerexample.databinding.ActivityCameraxBinding

class CameraXActivity : AppCompatActivity() {
    private val binding by lazy { ActivityCameraxBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}