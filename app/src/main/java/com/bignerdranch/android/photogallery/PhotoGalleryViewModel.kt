package com.bignerdranch.android.photogallery

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.bignerdranch.android.photogallery.FlickrFetchr
import com.bignerdranch.android.photogallery.database.GalleryRepository
import com.bignerdranch.android.photogallery.database.Item

class PhotoGalleryViewModel (private val app: Application) : AndroidViewModel(app) {
    private val galleryRepository = GalleryRepository.get()
    val galleryItemLiveData: LiveData<List<GalleryItem>>
    private val flickrFetchr = FlickrFetchr()
    private val mutableSearchTerm = MutableLiveData<String>()
    val searchTerm: String
        get() = mutableSearchTerm.value ?: ""


    init {
        mutableSearchTerm.value = QueryPreferences.getStoredQuery(app)
        //galleryItemLiveData = Transformations.switchMap(mutableSearchTerm) { searchTerm ->
        galleryItemLiveData = mutableSearchTerm.switchMap { searchTerm ->
            if (searchTerm.isBlank()) {
                flickrFetchr.fetchPhotos()
            } else {
                flickrFetchr.searchPhotos(searchTerm)
            }
        }
    }
    fun fetchPhotos(query: String = "") {
        QueryPreferences.setStoredQuery(app, query)
        mutableSearchTerm.value = query
    }
    fun showDatabaseGallery(){
        galleryRepository.getPhotos()
    }
    fun deletephotos(){
        galleryRepository.deleteAllPhotos()
    }
    fun addPhotoToDatabase(photo: Item) {
        galleryRepository.addPhoto(photo)
    }
}