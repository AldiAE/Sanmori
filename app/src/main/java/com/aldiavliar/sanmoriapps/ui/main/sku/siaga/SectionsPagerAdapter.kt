package com.aldiavliar.sanmoriapps.ui.main.sku.siaga

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
            0 -> fragment = SiagaMulaFragment()
            1 -> fragment = SiagaBantuFragment()
            2 -> fragment = SiagaTataFragment()
        }
        return fragment as Fragment
    }

}