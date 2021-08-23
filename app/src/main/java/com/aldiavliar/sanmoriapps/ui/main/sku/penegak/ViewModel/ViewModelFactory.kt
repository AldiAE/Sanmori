package com.aldiavliar.sanmoriapps.ui.main.sku.penegak.ViewModel

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
        if (modelClass.isAssignableFrom(PenegakBantaraViewModel::class.java)) {
            return PenegakBantaraViewModel(mApplication) as T
        }else if (modelClass.isAssignableFrom(TambahPenegakViewModel::class.java)) {
            return TambahPenegakViewModel(mApplication) as T
        }else if (modelClass.isAssignableFrom(PenegakLaksanaViewModel::class.java)) {
            return PenegakLaksanaViewModel(mApplication) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}