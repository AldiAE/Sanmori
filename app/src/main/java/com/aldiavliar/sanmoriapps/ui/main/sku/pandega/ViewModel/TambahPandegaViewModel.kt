package com.aldiavliar.sanmoriapps.ui.main.sku.pandega.ViewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.aldiavliar.sanmoriapps.data.Entity.PandegaEntity
import com.aldiavliar.sanmoriapps.data.repository.PandegaRepository

class TambahPandegaViewModel(application: Application) : ViewModel() {
    private val mPandegaRepository: PandegaRepository = PandegaRepository(application)
    fun insert(pandegaEntity: PandegaEntity) {
        mPandegaRepository.insert(pandegaEntity)
    }
    fun update(pandegaEntity: PandegaEntity) {
        mPandegaRepository.update(pandegaEntity)
    }
    fun delete(pandegaEntity: PandegaEntity) {
        mPandegaRepository.delete(pandegaEntity)
    }
}