package com.aldiavliar.sanmoriapps.ui.main.sku.siaga.ViewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.aldiavliar.sanmoriapps.data.Entity.SiagaEntity
import com.aldiavliar.sanmoriapps.data.repository.SiagaRepository

class SiagaBantuViewModel(application: Application) : ViewModel() {
    private val mSiagaRepository: SiagaRepository = SiagaRepository(application)
    fun getAllSiaga(): LiveData<List<SiagaEntity>> = mSiagaRepository.getAllSiaga()
    fun getSiagaBantu(): LiveData<List<SiagaEntity>> = mSiagaRepository.getSiagaBantu()
}