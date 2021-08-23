package com.aldiavliar.sanmoriapps.data.helper

import androidx.recyclerview.widget.DiffUtil
import com.aldiavliar.sanmoriapps.data.Entity.PandegaEntity

class PandegaDiffCallback(private val mOldPandegaList: List<PandegaEntity>, private val mNewPandegaList: List<PandegaEntity>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldPandegaList.size
    }
    override fun getNewListSize(): Int {
        return mNewPandegaList.size
    }
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldPandegaList[oldItemPosition].id == mNewPandegaList[newItemPosition].id
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = mOldPandegaList[oldItemPosition]
        val newEmployee = mNewPandegaList[newItemPosition]
        return oldEmployee.kategori == newEmployee.kategori && oldEmployee.point == newEmployee.point
                && oldEmployee.desk_point == newEmployee.desk_point
                && oldEmployee.cara_pengujian == newEmployee.cara_pengujian
                && oldEmployee.status == newEmployee.status
    }
}