package com.aldiavliar.sanmoriapps.data.helper

import androidx.recyclerview.widget.DiffUtil
import com.aldiavliar.sanmoriapps.data.Entity.SiagaEntity

class SiagaDiffCallback(private val mOldSiagaList: List<SiagaEntity>, private val mNewSiagaList: List<SiagaEntity>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldSiagaList.size
    }
    override fun getNewListSize(): Int {
        return mNewSiagaList.size
    }
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldSiagaList[oldItemPosition].id == mNewSiagaList[newItemPosition].id
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = mOldSiagaList[oldItemPosition]
        val newEmployee = mNewSiagaList[newItemPosition]
        return oldEmployee.kategori == newEmployee.kategori && oldEmployee.point == newEmployee.point
                && oldEmployee.desk_point == newEmployee.desk_point
                && oldEmployee.cara_pengujian == newEmployee.cara_pengujian
                && oldEmployee.status == newEmployee.status
    }
}