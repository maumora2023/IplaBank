package com.example.iplabank.data

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Solicitud::class], version = 1, exportSchema = false)
@TypeConverters(LocalDateConverter::class)
abstract class BaseDatos : RoomDatabase() {

    abstract fun solicitarDao(): SolicitudDao

    companion object {
        @Volatile
        private var INSTANCE: BaseDatos? = null

        fun getInstance(context: Context): BaseDatos {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BaseDatos::class.java,
                    "BaseDatos"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}