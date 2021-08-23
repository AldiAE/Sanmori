package com.aldiavliar.sanmoriapps.ui.main.sku.penegak.ViewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.aldiavliar.sanmoriapps.data.Entity.PenegakEntity
import com.aldiavliar.sanmoriapps.data.repository.PenegakRepository

class PenegakLaksanaViewModel(application: Application) : ViewModel() {
    private val mPenegakepository: PenegakRepository = PenegakRepository(application)
    fun getAllPenegak(): LiveData<List<PenegakEntity>> = mPenegakepository.getAllPenegak()
    fun getPenegakLaksana(): LiveData<List<PenegakEntity>> = mPenegakepository.getPenegakLaksana()
}