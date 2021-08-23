package com.aldiavliar.sanmoriapps.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.aldiavliar.sanmoriapps.data.Dao.PenegakDao
import com.aldiavliar.sanmoriapps.data.Database.SanmoriRoomDB
import com.aldiavliar.sanmoriapps.data.Entity.PenegakEntity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class PenegakRepository(application: Application) {
    private val mPenegakDao: PenegakDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = SanmoriRoomDB.getDatabase(application)
        mPenegakDao = db.penegakDao()
    }

    fun getPenegakBantara(): LiveData<List<PenegakEntity>> = mPenegakDao.getPenegakBantara()
    fun getPenegakLaksana(): LiveData<List<PenegakEntity>> = mPenegakDao.getPenegakLaksana()
    fun getAllPenegak(): LiveData<List<PenegakEntity>> = mPenegakDao.getAllPenegak()
    fun insert(penegakEntity: PenegakEntity) {
        executorService.execute { mPenegakDao.insert(penegakEntity) }
    }

    fun delete(penegakEntity: PenegakEntity) {
        executorService.execute { mPenegakDao.delete(penegakEntity) }
    }

    fun update(penegakEntity: PenegakEntity) {
        executorService.execute { mPenegakDao.update(penegakEntity) }
    }

}

