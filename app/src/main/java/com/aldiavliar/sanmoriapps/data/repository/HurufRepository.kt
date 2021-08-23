package com.aldiavliar.sanmoriapps.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.aldiavliar.sanmoriapps.data.Dao.HurufDao
import com.aldiavliar.sanmoriapps.data.Database.SanmoriRoomDB
import com.aldiavliar.sanmoriapps.data.Entity.HurufEntity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class HurufRepository(application: Application) {
    private val mHurufDao: HurufDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = SanmoriRoomDB.getDatabase(application)
        mHurufDao = db.hurufDao()
    }

    fun getAllHuruf(): LiveData<List<HurufEntity>> = mHurufDao.getAllHuruf()
    fun insert(hurufEntity: HurufEntity) {
        executorService.execute { mHurufDao.insert(hurufEntity) }
    }

    fun delete(hurufEntity: HurufEntity) {
        executorService.execute { mHurufDao.delete(hurufEntity) }
    }

    fun update(hurufEntity: HurufEntity) {
        executorService.execute { mHurufDao.update(hurufEntity) }
    }
}