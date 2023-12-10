package com.example.flores20100651ef2023.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("productos")
data class productEntity(
    @ColumnInfo("descripcion") var descripcion: String?,
    @ColumnInfo("cantidad") var cantidad: String?,
    @ColumnInfo("precio") var precio: String?
){
    @PrimaryKey(true)
    @ColumnInfo("producto_id") var productoId: Int=0
}

