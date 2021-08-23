package com.aldiavliar.sanmoriapps.ui.main.sku.penggalang.ViewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory private constructor(private val mApplication: Application) : ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null
        @JvmStatic
        fun getInstance(application: Application): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(application)
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PenggalangRamuViewModel::class.java)) {
            return PenggalangRamuViewModel(mApplication) as T
        }else if (modelClass.isAssignableFrom(TambahPenggalangViewModel::class.java)) {
            return TambahPenggalangViewModel(mApplication) as T
        }else if (modelClass.isAssignableFrom(PenggalangTerapViewModel::class.java)) {
            return PenggalangTerapViewModel(mApplication) as T
        }else if (modelClass.isAssignableFrom(PenggalangRakitViewModel::class.java)) {
            return PenggalangRakitViewModel(mApplication) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}