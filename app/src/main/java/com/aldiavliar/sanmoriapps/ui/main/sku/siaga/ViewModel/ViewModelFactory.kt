package com.aldiavliar.sanmoriapps.ui.main.sku.siaga.ViewModel

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
        if (modelClass.isAssignableFrom(SiagaBantuViewModel::class.java)) {
            return SiagaBantuViewModel(mApplication) as T
        }else if (modelClass.isAssignableFrom(TambahSiagaViewModel::class.java)) {
            return TambahSiagaViewModel(mApplication) as T
        }else if (modelClass.isAssignableFrom(SiagaTataViewModel::class.java)) {
            return SiagaTataViewModel(mApplication) as T
        }else if (modelClass.isAssignableFrom(SiagaMulaViewModel::class.java)) {
            return SiagaMulaViewModel(mApplication) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}