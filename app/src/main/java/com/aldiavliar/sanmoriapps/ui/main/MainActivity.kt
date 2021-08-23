package com.aldiavliar.sanmoriapps.ui.main


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.aldiavliar.sanmoriapps.R
import com.aldiavliar.sanmoriapps.sharedpref.MyPreferenceFragment
import com.aldiavliar.sanmoriapps.ui.fragment.DashboardFragment
import com.aldiavliar.sanmoriapps.ui.fragment.InfoFragment
import com.aldiavliar.sanmoriapps.ui.fragment.SettingFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_NAME = "extra_name"
    }

    lateinit var dashboardFragment: DashboardFragment
    lateinit var settingFragment: SettingFragment
    lateinit var infoFragment: InfoFragment
    lateinit var myPreferenceFragment: MyPreferenceFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomnav = findViewById<BottomNavigationView>(R.id.BottomNavMenu)

        dashboardFragment = DashboardFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameLayout,dashboardFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

        bottomnav.setOnNavigationItemSelectedListener { item ->

            when(item.itemId){
                R.id.home -> {
                    dashboardFragment = DashboardFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout,dashboardFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }

                R.id.setting -> {
                    settingFragment = SettingFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout,settingFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }

                R.id.info -> {
                    infoFragment = InfoFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout,infoFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }

                R.id.setting_holder -> {
                    myPreferenceFragment = MyPreferenceFragment()
                    supportFragmentManager.beginTransaction().add(R.id.setting_holder, MyPreferenceFragment()).commit()
                }
            }

            true
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}

