package com.aldiavliar.sanmoriapps.data.helper

import androidx.recyclerview.widget.DiffUtil
import com.aldiavliar.sanmoriapps.data.Entity.PenggalangEntity

class PenggalangDiffCallback(private val mOldPenggalangList: List<PenggalangEntity>, private val mNewPenggalangList: List<PenggalangEntity>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldPenggalangList.size
    }
    override fun getNewListSize(): Int {
        return mNewPenggalangList.size
    }
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldPenggalangList[oldItemPosition].id == mNewPenggalangList[newItemPosition].id
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = mOldPenggalangList[oldItemPosition]
        val newEmployee = mNewPenggalangList[newItemPosition]
        return oldEmployee.kategori == newEmployee.kategori && oldEmployee.point == newEmployee.point
                && oldEmployee.desk_point == newEmployee.desk_point
                && oldEmployee.cara_pengujian == newEmployee.cara_pengujian
                && oldEmployee.status == newEmployee.status
    }
}