package com.aldiavliar.sanmoriapps.ui.main.sku.penggalang.ViewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.aldiavliar.sanmoriapps.data.Entity.PenggalangEntity
import com.aldiavliar.sanmoriapps.data.repository.PenggalangRepository

class TambahPenggalangViewModel(application: Application) : ViewModel() {
    private val mPenggalangRepository: PenggalangRepository = PenggalangRepository(application)
    fun insert(penggalangEntity: PenggalangEntity) {
        mPenggalangRepository.insert(penggalangEntity)
    }
    fun update(pengggalangEntity: PenggalangEntity) {
        mPenggalangRepository.update(pengggalangEntity)
    }
    fun delete(pengggalangEntity: PenggalangEntity) {
        mPenggalangRepository.delete(pengggalangEntity)
    }
}