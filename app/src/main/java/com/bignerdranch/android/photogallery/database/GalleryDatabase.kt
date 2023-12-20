package com.bignerdranch.android.photogallery.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Item::class], version = 1)
abstract class GalleryDatabase: RoomDatabase() {
    abstract fun galleryDao(): GalleryDao
}