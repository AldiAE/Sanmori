package com.aldiavliar.sanmoriapps.ui.main.sku.penegak.ViewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.aldiavliar.sanmoriapps.data.Entity.PenegakEntity
import com.aldiavliar.sanmoriapps.data.repository.PenegakRepository

class TambahPenegakViewModel(application: Application) : ViewModel() {
    private val mPenegakRepository: PenegakRepository = PenegakRepository(application)
    fun insert(penegakEntity: PenegakEntity) {
        mPenegakRepository.insert(penegakEntity)
    }
    fun update(penegakEntity: PenegakEntity) {
        mPenegakRepository.update(penegakEntity)
    }
    fun delete(penegakEntity: PenegakEntity) {
        mPenegakRepository.delete(penegakEntity)
    }
}