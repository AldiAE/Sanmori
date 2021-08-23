package com.aldiavliar.sanmoriapps.ui.main.sku.penegak

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.aldiavliar.sanmoriapps.R
import com.aldiavliar.sanmoriapps.ui.main.sku.penegak.addUpdate.TambahPenegakActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class SkuPenegakActivity : AppCompatActivity() {

    private lateinit var back : ImageButton

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.penegak_bantara,
            R.string.penegak_laksana
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sku_penegak)

        back =  findViewById(R.id.onPressedBack)
        back.setOnClickListener {
            super.onBackPressed()
        }

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        var fabAdd = findViewById(R.id.fab_add) as FloatingActionButton
        fabAdd.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View) {
                val intent = Intent(this@SkuPenegakActivity, TambahPenegakActivity::class.java)
                startActivityForResult(intent, TambahPenegakActivity.REQUEST_ADD)
            }
        })
    }
}