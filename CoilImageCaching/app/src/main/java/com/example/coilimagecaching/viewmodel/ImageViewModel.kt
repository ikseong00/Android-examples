package com.example.coilimagecaching.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coilimagecaching.repository.ImageRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ImageViewModel : ViewModel() {
    private val repository = ImageRepository()

    private val _images = MutableStateFlow<List<String>>(emptyList())
    val images = _images.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    init {
        getImages()
    }

    private fun getImages() {
        viewModelScope.launch {
            Log.d("ImageViewModel4", "Images: 2${_images.value}")
            repository.getImages().fold(
                onSuccess = { response ->
//                    _images.value = response.response.homeImgs.map { it.homeImgUrl }
                    val newList = response.response.homeImgs.map { it.homeImgUrl }
                    _images.value = newList
                    Log.d("ImageViewModel3", "Images: 2${_images.value}")
                },
                onFailure = { exception ->
                    _error.value = exception.message
                    Log.d("ImageViewModel5", "Images: 2${exception.message}")
                }
            )
        }
    }
}