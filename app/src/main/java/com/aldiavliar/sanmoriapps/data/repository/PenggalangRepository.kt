package com.aldiavliar.sanmoriapps.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.aldiavliar.sanmoriapps.data.Dao.PenggalangDao
import com.aldiavliar.sanmoriapps.data.Database.SanmoriRoomDB
import com.aldiavliar.sanmoriapps.data.Entity.PenggalangEntity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class PenggalangRepository(application: Application) {
    private val mPenggalangDao: PenggalangDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = SanmoriRoomDB.getDatabase(application)
        mPenggalangDao = db.penggalangDao()
    }

    fun getPenggalangRakit(): LiveData<List<PenggalangEntity>> = mPenggalangDao.getPenggalangRakit()
    fun getPenggalangRamu(): LiveData<List<PenggalangEntity>> = mPenggalangDao.getPenggalangRamu()
    fun getPenggalangTerap(): LiveData<List<PenggalangEntity>> = mPenggalangDao.getPengglangTerap()
    fun getAllPenggalang(): LiveData<List<PenggalangEntity>> = mPenggalangDao.getAllPenggalang()
    fun insert(penggalangEntity: PenggalangEntity) {
        executorService.execute { mPenggalangDao.insert(penggalangEntity) }
    }

    fun delete(penggalangEntity: PenggalangEntity) {
        executorService.execute { mPenggalangDao.delete(penggalangEntity) }
    }

    fun update(penggalangEntity: PenggalangEntity) {
        executorService.execute { mPenggalangDao.update(penggalangEntity) }
    }

}

