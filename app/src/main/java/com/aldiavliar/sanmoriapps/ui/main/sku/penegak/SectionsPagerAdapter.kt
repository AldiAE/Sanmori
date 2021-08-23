package com.aldiavliar.sanmoriapps.ui.main.sku.penegak

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = PenegakBantaraFragment()
            1 -> fragment = PenegakLaksanaFragment()
        }
        return fragment as Fragment
    }
}