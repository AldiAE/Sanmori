package com.aldiavliar.sanmoriapps.data.helper

import androidx.recyclerview.widget.DiffUtil
import com.aldiavliar.sanmoriapps.data.Entity.PenegakEntity

class PenegakDiffCallback(private val mOldPenegakList: List<PenegakEntity>, private val mNewPenegakList: List<PenegakEntity>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldPenegakList.size
    }
    override fun getNewListSize(): Int {
        return mNewPenegakList.size
    }
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldPenegakList[oldItemPosition].id == mNewPenegakList[newItemPosition].id
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = mOldPenegakList[oldItemPosition]
        val newEmployee = mNewPenegakList[newItemPosition]
        return oldEmployee.kategori == newEmployee.kategori && oldEmployee.point == newEmployee.point
                && oldEmployee.desk_point == newEmployee.desk_point
                && oldEmployee.cara_pengujian == newEmployee.cara_pengujian
                && oldEmployee.status == newEmployee.status
    }
}