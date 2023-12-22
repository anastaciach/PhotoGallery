package com.bignerdranch.android.photogallery.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.bignerdranch.android.photogallery.GalleryItem

@Dao

interface GalleryDao {
    @Query("SELECT * FROM gallery")
    fun getphotos(): LiveData<List<Item>>
    @Query("SELECT * FROM gallery WHERE url=(:photoUrl)")
    fun getphotoByUrl(url: String): Item?

    @Insert
    fun addphoto(item:Item)

    @Query("DELETE FROM gallery")
    fun deletephotos(): LiveData<List<GalleryItem>>


}