package com.aldiavliar.sanmoriapps.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.aldiavliar.sanmoriapps.data.Dao.ATBDao
import com.aldiavliar.sanmoriapps.data.Database.SanmoriRoomDB
import com.aldiavliar.sanmoriapps.data.Entity.ATBEntity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ATBRepository(application: Application) {
    private val mAtbDao: ATBDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = SanmoriRoomDB.getDatabase(application)
        mAtbDao = db.atbDao()
    }

    fun getAllAtb(): LiveData<List<ATBEntity>> = mAtbDao.getAllAtb()
    fun insert(atbEntity: ATBEntity) {
        executorService.execute { mAtbDao.insert(atbEntity) }
    }

    fun delete(atbEntity: ATBEntity) {
        executorService.execute { mAtbDao.delete(atbEntity) }
    }

    fun update(atbEntity: ATBEntity) {
        executorService.execute { mAtbDao.update(atbEntity) }
    }
}