package com.aldiavliar.sanmoriapps.ui.main.sku.pandega.ViewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.aldiavliar.sanmoriapps.data.Entity.PandegaEntity
import com.aldiavliar.sanmoriapps.data.repository.PandegaRepository

class SkuPandegaViewModel(application: Application) : ViewModel() {
    private val mPandegaRepository: PandegaRepository = PandegaRepository(application)
    fun getAllPandega(): LiveData<List<PandegaEntity>> = mPandegaRepository.getAllPandega()
    fun getPandega(): LiveData<List<PandegaEntity>> = mPandegaRepository.getPandega()
}