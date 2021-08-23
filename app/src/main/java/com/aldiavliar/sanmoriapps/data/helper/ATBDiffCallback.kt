package com.aldiavliar.sanmoriapps.data.helper

import androidx.recyclerview.widget.DiffUtil
import com.aldiavliar.sanmoriapps.data.Entity.ATBEntity

class ATBDiffCallback(private val mOldAtbList: List<ATBEntity>, private val mNewAtbList: List<ATBEntity>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldAtbList.size
    }

    override fun getNewListSize(): Int {
        return mNewAtbList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldAtbList[oldItemPosition].id == mNewAtbList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = mOldAtbList[oldItemPosition]
        val newEmployee = mNewAtbList[newItemPosition]
        return oldEmployee.atb == newEmployee.atb && oldEmployee.sandi == newEmployee.sandi
                && oldEmployee.sound == newEmployee.sound
    }
}