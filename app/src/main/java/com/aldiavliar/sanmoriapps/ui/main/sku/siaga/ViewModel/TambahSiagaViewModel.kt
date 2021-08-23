package com.aldiavliar.sanmoriapps.ui.main.sku.siaga.ViewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.aldiavliar.sanmoriapps.data.Entity.SiagaEntity
import com.aldiavliar.sanmoriapps.data.repository.SiagaRepository

class TambahSiagaViewModel(application: Application) : ViewModel() {
    private val mSiagaRepository: SiagaRepository = SiagaRepository(application)
    fun insert(siagaEntity: SiagaEntity) {
        mSiagaRepository.insert(siagaEntity)
    }
    fun update(siagaEntity: SiagaEntity) {
        mSiagaRepository.update(siagaEntity)
    }
    fun delete(siagaEntity: SiagaEntity) {
        mSiagaRepository.delete(siagaEntity)
    }
}