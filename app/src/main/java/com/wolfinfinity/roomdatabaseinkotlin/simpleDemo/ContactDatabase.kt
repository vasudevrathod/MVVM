package com.wolfinfinity.roomdatabasekotlin.simpleDemo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Contact::class], version = 2)
@TypeConverters(Convertores::class) // TODO Date convertor
abstract class ContactDatabase: RoomDatabase() {
    abstract fun contactDao() : ContactDao

    companion object {

        private val migration_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE contact ADD COLUMN isActive INTEGER NOT NULL DEFAULT(0)")
            }

        }

        @Volatile
        private var INSTANCE : ContactDatabase? = null

        fun getDatabase(context: Context): ContactDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext
                        , ContactDatabase::class.java
                        , "ContactDB")
                        .addMigrations(migration_1_2) // TODO Update Version
                        .build()
                }
            }
            return INSTANCE!!
        }
    }

}