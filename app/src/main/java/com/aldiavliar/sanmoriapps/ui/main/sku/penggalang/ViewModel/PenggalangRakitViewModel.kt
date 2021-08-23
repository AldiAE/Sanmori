package com.aldiavliar.sanmoriapps.ui.main.sku.penggalang.ViewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.aldiavliar.sanmoriapps.data.Entity.PenggalangEntity
import com.aldiavliar.sanmoriapps.data.repository.PenggalangRepository

class PenggalangRakitViewModel(application: Application) : ViewModel() {
    private val mPenggalangRepository: PenggalangRepository = PenggalangRepository(application)
    fun getAllPenggalang(): LiveData<List<PenggalangEntity>> = mPenggalangRepository.getAllPenggalang()
    fun getPenggalangRakit(): LiveData<List<PenggalangEntity>> = mPenggalangRepository.getPenggalangRakit()
}