package com.aldiavliar.sanmoriapps.ui.main.sku.penggalang

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = PenggalangRamuFragment()
            1 -> fragment = PenggalangRakitFragment()
            2 -> fragment = PenggalangTerapFragment()
        }
        return fragment as Fragment
    }

}