package com.example.iplabank.data

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class LocalDateConverter {
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

    @TypeConverter
    fun toTimestamp(date: Date): String {
        return dateFormat.format(date)
    }

    @TypeConverter
    fun toDateTime(timestamp: String): Date {
        return dateFormat.parse(timestamp) ?: Date()
    }
}