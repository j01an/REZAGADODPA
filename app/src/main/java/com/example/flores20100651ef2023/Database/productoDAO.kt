package com.example.flores20100651ef2023.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface productoDAO {
    @Insert
    fun insert(productEntity: productEntity)
    @Update
    fun update(productEntity: productEntity)
    @Delete
    fun delete(productEntity: productEntity)
    @Query("SELECT * FROM productos ORDER BY descripcion")
    fun getCustomerOrderByLastName(): LiveData<List<productEntity>>
}