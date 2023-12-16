package com.bignerdranch.android.photogallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PhotoGalleryViewModel : ViewModel() {
    val galleryItemLiveData: LiveData<List<GalleryItem>>
    private val flickrFetchr = FlickrFetchr()
    private val mutableSearchTerm = MutableLiveData<String>()
    init {
        mutableSearchTerm.value = "planets"
        galleryItemLiveData =
            FlickrFetchr(). searchPhotos("planets")
    }
    fun fetchPhotos(query: String = "") {
        mutableSearchTerm.value = query
    }
}