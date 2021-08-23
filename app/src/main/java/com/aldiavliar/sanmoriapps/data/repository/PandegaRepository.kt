package com.aldiavliar.sanmoriapps.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.aldiavliar.sanmoriapps.data.Dao.PandegaDao
import com.aldiavliar.sanmoriapps.data.Database.SanmoriRoomDB
import com.aldiavliar.sanmoriapps.data.Entity.PandegaEntity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class PandegaRepository(application: Application) {
    private val mPandegaDao: PandegaDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = SanmoriRoomDB.getDatabase(application)
        mPandegaDao = db.pandegaDao()
    }

    fun getAllPandega(): LiveData<List<PandegaEntity>> = mPandegaDao.getAllPandega()
    fun getPandega(): LiveData<List<PandegaEntity>> = mPandegaDao.getPandega()
    fun insert(pandegaEntity: PandegaEntity) {
        executorService.execute { mPandegaDao.insert(pandegaEntity) }
    }

    fun delete(pandegaEntity: PandegaEntity) {
        executorService.execute { mPandegaDao.delete(pandegaEntity) }
    }

    fun update(pandegaEntity: PandegaEntity) {
        executorService.execute { mPandegaDao.update(pandegaEntity) }
    }

}

