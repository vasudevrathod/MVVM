package com.wolfinfinity.roomdatabasekotlin.simpleDemo

import androidx.room.TypeConverter
import java.util.*

class Convertores {

    @TypeConverter
    fun fromDateToLong(value: Date): Long {
        return value.time
    }

    @TypeConverter
    fun fromLongToDate(value: Long) : Date {
        return Date(value)
    }
}