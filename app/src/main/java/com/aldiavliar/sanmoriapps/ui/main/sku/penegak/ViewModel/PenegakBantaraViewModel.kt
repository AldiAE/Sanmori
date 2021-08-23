package com.aldiavliar.sanmoriapps.ui.main.sku.penegak.ViewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.aldiavliar.sanmoriapps.data.Entity.PenegakEntity
import com.aldiavliar.sanmoriapps.data.repository.PenegakRepository

class PenegakBantaraViewModel(application: Application) : ViewModel() {
    private val mPenegakRepository: PenegakRepository = PenegakRepository(application)
    fun getAllPenegak(): LiveData<List<PenegakEntity>> = mPenegakRepository.getAllPenegak()
    fun getPenegakBantara(): LiveData<List<PenegakEntity>> = mPenegakRepository.getPenegakBantara()
}