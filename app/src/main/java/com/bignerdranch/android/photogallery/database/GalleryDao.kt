package com.bignerdranch.android.photogallery.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.bignerdranch.android.photogallery.Item
import kotlinx.coroutines.flow.Flow
@Dao

    interface GalleryDao {
        @Query("SELECT * FROM gallery")
        fun getphotos(): LiveData<List<Item>>

        @Query("SELECT * FROM gallery WHERE url=(:url)")
        fun getphoto(url: String): Item?
        //аннотация @Query указывает, что getphotos() и getphoto(url: String):
        //предназначены для извлечения информации
        //из базы данных, а не вставки, обновления или удаления элементов из базы данных.

        @Insert
        fun addphoto(item: Item)

        @Query("DELETE FROM gallery")
        fun deletephotos()
    }
