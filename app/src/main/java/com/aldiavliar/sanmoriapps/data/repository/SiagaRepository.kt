package com.aldiavliar.sanmoriapps.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.aldiavliar.sanmoriapps.data.Dao.SiagaDao
import com.aldiavliar.sanmoriapps.data.Database.SanmoriRoomDB
import com.aldiavliar.sanmoriapps.data.Entity.SiagaEntity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class SiagaRepository(application: Application) {
    private val mSiagaDao: SiagaDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = SanmoriRoomDB.getDatabase(application)
        mSiagaDao = db.siagaDao()
    }

    fun getSiagaMula(): LiveData<List<SiagaEntity>> = mSiagaDao.getSiagaMula()
    fun getSiagaBantu(): LiveData<List<SiagaEntity>> = mSiagaDao.getSiagaBantu()
    fun getSiagaTata(): LiveData<List<SiagaEntity>> = mSiagaDao.getSiagaTata()
    fun getAllSiaga(): LiveData<List<SiagaEntity>> = mSiagaDao.getAllSiaga()
    fun insert(siagaEntity: SiagaEntity) {
        executorService.execute { mSiagaDao.insert(siagaEntity) }
    }

    fun delete(siagaEntity: SiagaEntity) {
        executorService.execute { mSiagaDao.delete(siagaEntity) }
    }

    fun update(siagaEntity: SiagaEntity) {
        executorService.execute { mSiagaDao.update(siagaEntity) }
    }

}

