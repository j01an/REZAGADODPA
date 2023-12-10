package com.example.flores20100651ef2023.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [productEntity::class], version = 1 )
abstract class productoDatabase: RoomDatabase() {
    abstract fun productoDAO(): productoDAO

    companion object {
        private const val DATABASE_NAME = "sales"

        @Volatile
        private var INSTANCE: productoDatabase? = null
        fun getInstance(context: Context): productoDatabase? {
            INSTANCE ?: synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    productoDatabase::class.java,
                    DATABASE_NAME
                ).build()
            }
            return INSTANCE
        }
    }
}