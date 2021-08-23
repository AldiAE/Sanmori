package com.aldiavliar.sanmoriapps.data.helper

import androidx.recyclerview.widget.DiffUtil
import com.aldiavliar.sanmoriapps.data.Entity.HurufEntity

class HurufDiffCallback(private val mOldHurufList: List<HurufEntity>, private val mNewHurufList: List<HurufEntity>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldHurufList.size
    }

    override fun getNewListSize(): Int {
        return mNewHurufList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldHurufList[oldItemPosition].id == mNewHurufList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = mOldHurufList[oldItemPosition]
        val newEmployee = mNewHurufList[newItemPosition]
        return oldEmployee.huruf == newEmployee.huruf && oldEmployee.sandi == newEmployee.sandi
                && oldEmployee.sound == newEmployee.sound
    }
}