package com.aldiavliar.sanmoriapps.data.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aldiavliar.sanmoriapps.data.Dao.*
import com.aldiavliar.sanmoriapps.data.Entity.*

@Database(entities = [SiagaEntity::class, PenggalangEntity::class,
    PenegakEntity::class, PandegaEntity::class,
                     HurufEntity::class, ATBEntity::class], version = 6)
abstract class SanmoriRoomDB : RoomDatabase() {
    abstract fun siagaDao(): SiagaDao
    abstract fun penggalangDao(): PenggalangDao
    abstract fun penegakDao(): PenegakDao
    abstract fun pandegaDao(): PandegaDao
    abstract fun hurufDao(): HurufDao
    abstract fun atbDao(): ATBDao

    companion object {
        @Volatile
        private var INSTANCE: SanmoriRoomDB? = null

        @JvmStatic
        fun getDatabase(context: Context): SanmoriRoomDB {
            if (INSTANCE == null) {
                synchronized(SanmoriRoomDB::class.java) {

                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        SanmoriRoomDB::class.java, "sanmori_database")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE as SanmoriRoomDB
        }
    }
}